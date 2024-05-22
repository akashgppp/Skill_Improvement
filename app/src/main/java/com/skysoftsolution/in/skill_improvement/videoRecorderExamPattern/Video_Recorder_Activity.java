package com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.media.AudioManager;
import android.media.ImageReader;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.skysoftsolution.in.skill_improvement.R;
import com.skysoftsolution.in.skill_improvement.dataSource.DataSource;
import com.skysoftsolution.in.skill_improvement.databinding.ActivityVideoRecorderBinding;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.A08_StudentProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.NameEntity;
import com.skysoftsolution.in.skill_improvement.faceWork.CameraActivity;
import com.skysoftsolution.in.skill_improvement.faceWork.DetectorActivity;
import com.skysoftsolution.in.skill_improvement.faceWork.customview.OverlayView;
import com.skysoftsolution.in.skill_improvement.faceWork.env.BorderedText;
import com.skysoftsolution.in.skill_improvement.faceWork.env.ImageUtils;
import com.skysoftsolution.in.skill_improvement.faceWork.tflite.SimilarityClassifier;
import com.skysoftsolution.in.skill_improvement.faceWork.tflite.TFLiteObjectDetectionAPIModel;
import com.skysoftsolution.in.skill_improvement.faceWork.tracking.MultiBoxTracker;
import com.skysoftsolution.in.skill_improvement.sharedpreference.SharedPreferenceData;
import com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.service.VideoJobService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class Video_Recorder_Activity extends  AppCompatActivity  {
    private ActivityVideoRecorderBinding binding;
    private static final String TAG = Video_Recorder_Activity.class.getSimpleName();
    public static SurfaceView mSurfaceView;
    public static SurfaceHolder mSurfaceHolder;
    public static Camera mCamera;
    public static void Beep() {
        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoRecorderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSurfaceView=binding.surfaceView;
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // todo--keep this methods exmpty
            }

            @Override
            public void surfaceChanged(SurfaceHolder myholder, int formatInt, int widths, int heights) {
                // todo--keep this methods exmpty
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // todo--keep this methods exmpty
            }
        });
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(new Intent(Video_Recorder_Activity.this, VideoJobService.class));


            }
        });
        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(Video_Recorder_Activity.this, VideoJobService.class));
            }
        });


    }

}