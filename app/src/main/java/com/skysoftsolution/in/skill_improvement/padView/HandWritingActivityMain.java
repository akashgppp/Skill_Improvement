package com.skysoftsolution.in.skill_improvement.padView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skysoftsolution.in.skill_improvement.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HandWritingActivityMain extends AppCompatActivity {
    private WritingRecognizer mWritingRecognizer;
    private WritingView mWritingView;
    private TextView mVersion;
    private TextView mCandidates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hand_writing_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            copyResourceToStorage();
            //initialize();



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWritingRecognizer.destroy();
    }

    private void initialize() {
        try {
            mWritingRecognizer = new WritingRecognizer(getApplicationContext());
        }catch (Exception e){

        }
        mWritingView = (WritingView) findViewById(R.id.canvas);
        mWritingView.setRecognizer(mWritingRecognizer);
        Button clearButton = (Button) findViewById(R.id.clear);
        if (clearButton != null) {
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleClear();
                }
            });
        }

        Button recognizeButton = (Button) findViewById(R.id.recognize);
        if (recognizeButton != null) {
            recognizeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    handleRecognize();
                }
            });
        }

        RadioGroup languageGroup = (RadioGroup) findViewById(R.id.languageGroup);
        if (languageGroup != null) {
            languageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int id) {
                    handleLanguageChanged(id);
                }
            });
        }

        mVersion = (TextView) findViewById(R.id.version);
        mVersion.setText("Model : " + Build.MODEL + "\n"
                + "Version : " + mWritingRecognizer.getVersion() + "\n"
                + "Due date : " + mWritingRecognizer.getDueDate());
        mCandidates = (TextView) findViewById(R.id.candidates);
    }

    private void copyResourceToStorage() {
        String path = "hdb";
        String[] fileList = null;
        try {
            fileList = getAssets().list(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileList == null) {
            return;
        }

        for (String file : fileList) {
            try {
                String filePath = (path + "/" + file);
                try {
                    FileInputStream fis = openFileInput(file);
                    fis.close();
                } catch (FileNotFoundException e) {
                    InputStream is = getAssets().open(filePath);
                    final int bufferSize = is.available();
                    byte[] buffer = new byte[bufferSize];
                    int readSize = is.read(buffer);
                    is.close();
                    if (readSize > 0) {
                        FileOutputStream fos = openFileOutput(file, Activity.MODE_PRIVATE);
                        fos.write(buffer);
                        fos.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClear() {
        mCandidates.setVisibility(View.GONE);
        mCandidates.setText("");
        mWritingView.clear();
        mWritingRecognizer.clearInk();
    }

    private void handleRecognize() {
        mWritingView.clear();
        mCandidates.setText(mWritingRecognizer.recognize());
        mCandidates.setVisibility(View.VISIBLE);
        mWritingRecognizer.clearInk();
    }

    @SuppressLint("NonConstantResourceId")
    private void handleLanguageChanged(int id) {
        int language = DHWR.DLANG_KOREAN;
        int option = DHWR.DTYPE_KOREAN;
        if(id== R.id.koreanEnglish1){
            language = DHWR.DLANG_KOREAN;
            option = DHWR.DTYPE_KOREAN | DHWR.DTYPE_UPPERCASE | DHWR.DTYPE_LOWERCASE | DHWR.DTYPE_SIGN | DHWR.DTYPE_NUMERIC;
        }else if(id==R.id.chinese1){
            language = DHWR.DLANG_CHINA;
            option = DHWR.DTYPE_SIMP | DHWR.DTYPE_NUMERIC;
        }else if(id==R.id.japanese1){
            language = DHWR.DLANG_JAPANESE;
            option = DHWR.DTYPE_HIRAGANA | DHWR.DTYPE_KATAKANA | DHWR.DTYPE_KANJI | DHWR.DTYPE_NUMERIC;
        }
        mWritingRecognizer.setLanguage(language, option);
    }
}