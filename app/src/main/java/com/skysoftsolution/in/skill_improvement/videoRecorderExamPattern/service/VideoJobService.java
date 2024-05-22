package com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.service;

import static com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.Video_Recorder_Activity.Beep;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.Video_Recorder_Activity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.Video_Recorder_Activity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class VideoJobService extends JobService {
    private static final String TAG = VideoJobService.class.getSimpleName();
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private static Camera mServiceCamera;
    private boolean mRecordingStatus;
    private MediaRecorder mMediaRecorder;

    String videoSavePathInDevice = null;
    private static final String VIDEO_RECORDER_FILE_EXT_MP4 = ".mp4";
    private static final String VIDEO_RECORDER_FOLDER = "VideoRecorder";
    private int currentFormat = 0;
    private String file_exts[] = {VIDEO_RECORDER_FILE_EXT_MP4};

    @Override
    public void onCreate() {
        super.onCreate();
        mRecordingStatus = false;
        mSurfaceView = Video_Recorder_Activity.mSurfaceView;
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(surfaceCallback);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyhhmm");
        final String format = simpleDateFormat.format(new Date());
        Log.e(TAG, "ts_: " + format);
        videoSavePathInDevice = getFilename();
    }

    @Override
    public int onStartCommand(Intent intent1, int flags1, int startId1) {
        super.onStartCommand(intent1, flags1, startId1);

        if (!mRecordingStatus) {
            try {
                startRecording();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "Job started");
        try {
            startRecording();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void onDestroy() {
        stopRecording();
        mRecordingStatus = false;

        super.onDestroy();
    }

    private SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // Surface created, start camera preview
            try {
                startRecording();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // Surface changed
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // Surface destroyed
        }
    };

    public void startRecording() throws IOException {

            if (mServiceCamera == null) {
                mServiceCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            }

            Toast.makeText(getBaseContext(), "Recording Started", Toast.LENGTH_SHORT).show();

            Camera.Parameters params = mServiceCamera.getParameters();
            mServiceCamera.setParameters(params);
            Camera.Parameters p = mServiceCamera.getParameters();
            final List<Camera.Size> listPreviewSize = p.getSupportedPreviewSizes();
            Camera.Size previewSize = listPreviewSize.get(0);
            p.setPreviewSize(previewSize.width, previewSize.height);
            mServiceCamera.setParameters(p);

            try {
                mServiceCamera.setPreviewDisplay(mSurfaceHolder);
                mServiceCamera.startPreview();
            } catch (IOException e) {
                Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                e.printStackTrace();
            }

            mServiceCamera.unlock();

            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setCamera(mServiceCamera);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            mMediaRecorder.setOutputFile(videoSavePathInDevice);
            mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

            // Set the orientation hint
            int rotation = 270;

            mMediaRecorder.setOrientationHint(rotation);

            mMediaRecorder.prepare();
            mMediaRecorder.start();

            mRecordingStatus = true;

            // Initialize face detection
            initializeFaceDetection();


    }

    private void initializeFaceDetection() {
        // High-accuracy landmark detection and face classification
        FaceDetectorOptions highAccuracyOpts =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();

        FaceDetector detector = FaceDetection.getClient(highAccuracyOpts);

        // Use a listener to receive face detection results
        mServiceCamera.setPreviewCallback((data, camera) -> {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            InputImage image = InputImage.fromByteArray(
                    data,
                    size.width,
                    size.height,
                    0, // rotation
                    InputImage.IMAGE_FORMAT_NV21
            );

            detector.process(image)
                    .addOnSuccessListener(
                            faces -> {
                                Beep();
                                Log.d("FACE", "Faces detected: " + faces.size());
                                for (Face face : faces) {
                                    Log.d("FACE", "Face bounds: " + face.getBoundingBox());
                                    // Here you can add your logic to handle detected faces
                                }
                            })
                    .addOnFailureListener(
                            e -> {
                                Beep();
                                // Task failed with an exception
                                Log.e("FACE", "Face detection failed: " + e.getMessage());
                                e.printStackTrace();
                            });
        });
    }

    public void stopRecording() {
        if (mMediaRecorder != null) {
            Toast.makeText(getBaseContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
            try {
                mServiceCamera.reconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                mMediaRecorder.stop();
            } catch (RuntimeException stopException) {
                // handle cleanup here
            }
            mMediaRecorder.reset();

            mMediaRecorder.release();

            mServiceCamera.release();
            mServiceCamera = null;

        }
    }

    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory() + File.separator
                + Environment.DIRECTORY_DCIM + File.separator + "SkillImprove";
        File file = new File(filepath, VIDEO_RECORDER_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat]);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}