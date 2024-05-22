package com.skysoftsolution.in.skill_improvement.attendance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skysoftsolution.in.skill_improvement.R;
import com.skysoftsolution.in.skill_improvement.databinding.ActivityDashBoardBinding;
import com.skysoftsolution.in.skill_improvement.databinding.ActivityMain2Binding;
import com.skysoftsolution.in.skill_improvement.faceWork.DetectorActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        isPermissionGranted();
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectorActivity.class);
                intent.putExtra("Name", "Akash");
                intent.putExtra("ProfileFor", "Student");
                intent.putExtra("StudentId","1");
                startActivity(intent);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectorActivity.class);
                intent.putExtra("Name", "Akash");
                intent.putExtra("ProfileFor", "Student_Update");
                intent.putExtra("StudentId","1");
                startActivity(intent);
            }
        });

    }
    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else {
            return true;
        }
    }
}