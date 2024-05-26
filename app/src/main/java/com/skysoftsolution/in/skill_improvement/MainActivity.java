package com.skysoftsolution.in.skill_improvement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skysoftsolution.in.skill_improvement.dashBoard.DashBoardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mIntent = new Intent(MainActivity.this, DashBoardActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mIntent);
                finish();
            }
        }, 1000);
    }
}