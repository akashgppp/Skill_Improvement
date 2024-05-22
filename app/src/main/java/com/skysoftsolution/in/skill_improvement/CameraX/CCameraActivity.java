package com.skysoftsolution.in.skill_improvement.CameraX;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import com.skysoftsolution.in.skill_improvement.R;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class CCameraActivity extends AppCompatActivity {
    PreviewView pv_view;
    ImageView img_click, img_cancel, img_cap_sucess, img_show;
    Camera camera;
    File fileOutPut;
    CameraSelector cameraSelector;
    ProcessCameraProvider cameraProvider;
    ImageAnalysis imageAnalysis;
    private ImageCapture imageCapture;

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_camera);
        View decorView = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOption);
        getWindow().setStatusBarColor(getResources().getColor(com.google.android.material.R.color.design_default_color_primary));
        getAllViewId();

        Preview preview = new Preview.Builder().build();
        ListenableFuture cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                cameraProvider = (ProcessCameraProvider) cameraProviderFuture.get();
                pv_view = findViewById(R.id.pv_view);
                cameraProvider.unbindAll();

                imageAnalysis = new ImageAnalysis.Builder().setTargetResolution(new Size(640, 480)).setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();

                imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).setTargetRotation(pv_view.getDisplay() == null ? 0 : pv_view.getDisplay().getRotation()).build();

                cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();

                camera = cameraProvider.bindToLifecycle(((LifecycleOwner) this), cameraSelector, preview, imageAnalysis, imageCapture);

                preview.setSurfaceProvider(pv_view.getSurfaceProvider());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(CCameraActivity.this));

        img_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imageCapture.takePicture(ContextCompat.getMainExecutor(CCameraActivity.this), new ImageCapture.OnImageCapturedCallback() {
                        @Override
                        public void onCaptureSuccess(@NonNull ImageProxy image) {
                            super.onCaptureSuccess(image);
                            img_cap_sucess.setVisibility(View.VISIBLE);
                            pv_view.setVisibility(View.INVISIBLE);
                            img_show.setVisibility(View.VISIBLE);
                            img_click.setVisibility(View.INVISIBLE);
                            try {
                                String imageFileName = "IMG_" + System.currentTimeMillis() + "";
                                File storegeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                                File file = new File(storegeDir, imageFileName + ".jpg");
                                fileOutPut = file;
                               CameraworkerThread cameraworkerThread = new CameraworkerThread(CCameraActivity.this, fileOutPut, img_show, image);
                                cameraworkerThread.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                            super.onError(exception);
                            img_cap_sucess.setVisibility(View.GONE);
                            exception.printStackTrace();
                        }
                    });

                } catch (Exception e) {

                }
            }
        });

        img_cap_sucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileOutPut != null) {
                    cameraProvider.unbindAll();
                    Intent intent = new Intent();
                    intent.putExtra("File", fileOutPut);
                    setResult(AppCompatActivity.RESULT_OK, intent);
                    finish();
                }
            }
        });

        img_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cameraProvider != null) cameraProvider.unbindAll();
                pv_view.setVisibility(View.VISIBLE);
                img_show.setVisibility(View.INVISIBLE);
                img_cap_sucess.setVisibility(View.GONE);
                finish();
            }
        });

    }

    private void getAllViewId() {
        pv_view = findViewById(R.id.pv_view);
        img_click = findViewById(R.id.img_click);
        img_cancel = findViewById(R.id.img_cancel);
        img_cap_sucess = findViewById(R.id.img_cap_sucess);
        img_show = findViewById(R.id.img_show);
    }

}