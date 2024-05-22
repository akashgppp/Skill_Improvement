package com.skysoftsolution.in.skill_improvement.padView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kyanogen.signatureview.SignatureView;
import com.mrudultora.colorpicker.ColorPickerPopUp;
import com.skysoftsolution.in.paymentintegration.dashboard.entity.ModuleForUse;
import com.skysoftsolution.in.skill_improvement.R;
import com.skysoftsolution.in.skill_improvement.databinding.ActivitySignaturePadViewBinding;

import org.xmlpull.v1.XmlPullParser;

import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SignaturePadView extends AppCompatActivity {
    private ActivitySignaturePadViewBinding mainBinding;
    private DashBoardViewModel viewModel;
    private int mDefaultColor;
    private static final int REQUEST_CODE = 1;
    boolean lockFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivitySignaturePadViewBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(DashBoardViewModel.class);

        viewModel.getUserList().observe(this, new Observer<List<ModuleForUse>>() {
            @Override
            public void onChanged(List<ModuleForUse> userList) {
                if (userList != null && !userList.isEmpty()) {
                    for (ModuleForUse use : userList) {
                        createCardViewForUser(use);
                    }
                }
            }
        });

        // Add sample users
        addSampleUsers();
    }

    private void createCardViewForUser(ModuleForUse use) {
        CardView cardView = new CardView(SignaturePadView.this);
        // Set layout parameters for the CardView
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(16, 16, 16, 16);  // Adjust margins as needed
        cardView.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(SignaturePadView.this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.orchid));
        // Set layout parameters for the LinearLayout
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(16, 16, 16, 16);  // Adjust margins as needed
        linearLayout.setLayoutParams(llParams);
        linearLayout.setWeightSum(2);
        LinearLayout.LayoutParams llParamsTv = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        TextView textView = new TextView(SignaturePadView.this);
        textView.setText(use.getTitle()); // Assuming getTitle() is a method in ModuleForUse
        textView.setPadding(16, 16, 16, 16);  // Adjust padding as needed
        textView.setLayoutParams(llParamsTv);
        linearLayout.addView(textView);
        try {
            XmlPullParser parser = getResources().getXml(R.xml.activity_signature_pad_view);
            AttributeSet attributes = Xml.asAttributeSet(parser);
            LinearLayout.LayoutParams llParamsSig = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    400, 1);
            SignatureView signaturePad = new SignatureView(SignaturePadView.this, attributes);
            signaturePad.setTag(String.valueOf(use.getId())); // Assuming getId() is a method in ModuleForUse
            signaturePad.setLayoutParams(llParamsSig);
            signaturePad.setPadding(16, 16, 16, 16);  // Adjust padding as needed
            linearLayout.addView(signaturePad);
            Log.e("Error111", signaturePad.getTag() + "");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error111", e.getMessage() + "");
        }
        LinearLayout linearLayoutFor = new LinearLayout(SignaturePadView.this);
        linearLayoutFor.setOrientation(LinearLayout.HORIZONTAL);
        // Set layout parameters for the LinearLayout
        LinearLayout.LayoutParams llParamsMulti = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 80);
        linearLayoutFor.setGravity(Gravity.CENTER);
        llParamsMulti.setMargins(16, 5, 16, 5);  // Adjust margins as needed
        linearLayoutFor.setLayoutParams(llParamsMulti);
        linearLayoutFor.setWeightSum(4);
        LinearLayout.LayoutParams llParamsTV = new LinearLayout.LayoutParams(30, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        llParamsTV.setMargins(5, 0, 5, 0);
        TextView textViewPlus = createControlButton("+",use.getId());
        TextView textViewMinus = createControlButton("-",use.getId());;
        ImageView imageView = new ImageView(SignaturePadView.this);
        // Assuming getTitle() is a method in ModuleForUse
        LinearLayout.LayoutParams llParamsImage = new LinearLayout.LayoutParams(50, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        llParamsImage.setMargins(0, 0, 20, 0);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.baseline_lock_open_24));
        imageView.setPadding(16, 16, 16, 16);  // Adjust padding as needed
        imageView.setLayoutParams(llParamsImage);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLock(imageView);
            }
        });
        imageView.setForegroundGravity(Gravity.CENTER);
        View view = new View(SignaturePadView.this);
        view.setLayoutParams(llParamsTV);
        linearLayoutFor.addView(imageView);
        linearLayoutFor.addView(view);
        linearLayoutFor.addView(textViewPlus);
        linearLayoutFor.addView(textViewMinus);
        textViewPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adjustSignatureViewHeight(textViewMinus.getTag().toString(),20);
            }
        });
        textViewMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adjustSignatureViewHeight(textViewMinus.getTag().toString(),-20);
            }
        });
        linearLayout.addView(linearLayoutFor);
        cardView.addView(linearLayout);
        mainBinding.llForQuestion.addView(cardView);

    }
    private void addSampleUsers() {
        viewModel.addModule(new ModuleForUse(1, "A", R.drawable.write_pad));
        viewModel.addModule(new ModuleForUse(2, "B", R.drawable.video_recorder));
        viewModel.addModule(new ModuleForUse(3, "A", R.drawable.write_pad));
        viewModel.addModule(new ModuleForUse(4, "B", R.drawable.video_recorder));
        viewModel.addModule(new ModuleForUse(5, "C", R.drawable.write_pad));
        viewModel.addModule(new ModuleForUse(6, "D", R.drawable.video_recorder));
    }

    private TextView createControlButton(String text, int id) {
        TextView button = new TextView(SignaturePadView.this);
        button.setText(text);
        button.setTag(String.valueOf(id));
        button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        button.setGravity(Gravity.RIGHT);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundColor(getResources().getColor(text.equals("+") ? R.color.green : R.color.red));
        button.setTextSize(18f);
        button.setTypeface(Typeface.DEFAULT_BOLD);
        button.setPadding(5, 5, 5, 5);
        button.setLayoutParams(new LinearLayout.LayoutParams(30, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        return button;
    }

    private void adjustSignatureViewHeight(String tag, int delta) {
        for (int i = 0; i < mainBinding.llForQuestion.getChildCount(); i++) {
            SignatureView signatureView = ((SignatureView) ((LinearLayout) ((CardView) (mainBinding.llForQuestion.getChildAt(i)))
                    .getChildAt(0)).getChildAt(1));
            if (signatureView != null && tag.equals(signatureView.getTag())) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) signatureView.getLayoutParams();
                int newHeight = layoutParams.height + delta;
                if (newHeight >= 400) {
                    layoutParams.height = newHeight;
                    signatureView.setLayoutParams(layoutParams);
                    signatureView.invalidate();
                }
                break;
            }
        }
    }

    private void toggleLock(ImageView imageView) {
        lockFlag = !lockFlag;
        imageView.setImageDrawable(getResources().getDrawable(
                lockFlag ? R.drawable.baseline_lock_open_24 : R.drawable.baseline_lock_outline_24));
        mainBinding.ScrollView.setScrollingEnabled(lockFlag);
    }
}
