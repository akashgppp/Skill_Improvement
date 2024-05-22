package com.skysoftsolution.in.skill_improvement;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
        isPermissionGranted();
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
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

                    &&checkSelfPermission(android.Manifest.permission.FOREGROUND_SERVICE_MICROPHONE) == PackageManager.PERMISSION_GRANTED

                    &&
                    checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

                    && checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
            ) {
                return true;
            } else {
                ActivityCompat.requestPermissions(
                        MainActivity.this, new String[]{android.Manifest.permission.READ_PHONE_STATE,
                                android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.FOREGROUND_SERVICE_MICROPHONE,
                                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else {
            return true;
        }
    }
}