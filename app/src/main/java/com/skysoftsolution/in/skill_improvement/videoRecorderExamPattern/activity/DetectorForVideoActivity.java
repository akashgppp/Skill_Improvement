package com.skysoftsolution.in.skill_improvement.videoRecorderExamPattern.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.camera2.CameraCharacteristics;
import android.media.AudioManager;
import android.media.ImageReader.OnImageAvailableListener;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Size;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.skysoftsolution.in.skill_improvement.Datetime.App;
import com.skysoftsolution.in.skill_improvement.Datetime.TrueTime;
import com.skysoftsolution.in.skill_improvement.Datetime.TrueTimeRx;
import com.skysoftsolution.in.skill_improvement.R;
import com.skysoftsolution.in.skill_improvement.dashBoard.DashBoardActivity;
import com.skysoftsolution.in.skill_improvement.dataSource.DataSource;
import com.skysoftsolution.in.skill_improvement.entity.A01_Teacher;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherProfile;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.A05_Student;
import com.skysoftsolution.in.skill_improvement.entity.A06_StudentDetails;
import com.skysoftsolution.in.skill_improvement.entity.A07_StudentProfile;
import com.skysoftsolution.in.skill_improvement.entity.A08_StudentProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.A09_StudentAttendance;
import com.skysoftsolution.in.skill_improvement.entity.A09_TeacherAttendance;
import com.skysoftsolution.in.skill_improvement.entity.NameEntity;
import com.skysoftsolution.in.skill_improvement.faceWork.CameraActivity;
import com.skysoftsolution.in.skill_improvement.faceWork.customview.OverlayView;
import com.skysoftsolution.in.skill_improvement.faceWork.env.BorderedText;
import com.skysoftsolution.in.skill_improvement.faceWork.env.ImageUtils;
import com.skysoftsolution.in.skill_improvement.faceWork.tflite.SimilarityClassifier;
import com.skysoftsolution.in.skill_improvement.faceWork.tflite.TFLiteObjectDetectionAPIModel;
import com.skysoftsolution.in.skill_improvement.faceWork.tracking.MultiBoxTracker;
import com.skysoftsolution.in.skill_improvement.sharedpreference.SharedPreferenceData;
import com.skysoftsolution.in.skill_improvement.utility.Imagehelpher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import butterknife.ButterKnife;

public class DetectorForVideoActivity extends ViCameraActivity implements OnImageAvailableListener, TextToSpeech.OnInitListener {
    private static final int TF_OD_API_INPUT_SIZE = 112;
    private static final boolean TF_OD_API_IS_QUANTIZED = false;
    private static final String TF_OD_API_MODEL_FILE = "mobile_face_net.tflite";
    private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/labelmap.txt";
    private static final DetectorForVideoActivity.DetectorMode MODE = DetectorForVideoActivity.DetectorMode.TF_OD_API;
    private static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;
    private static final boolean MAINTAIN_ASPECT = false;
    private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);
    private static final boolean SAVE_PREVIEW_BITMAP = false;
    private static final float TEXT_SIZE_DIP = 10;
    private OverlayView trackingOverlay;
    private Integer sensorOrientation;
    private SimilarityClassifier detector, detectorForCheck;
    private long lastProcessingTimeMs;
    private Bitmap rgbFrameBitmap = null, croppedBitmap = null, cropCopyBitmap = null, portraitBmp = null, faceBmp = null;
    private boolean computingDetection = false, addPending = false;
    private long timestamp = 0;
    private Matrix frameToCropTransform, cropToFrameTransform;
    private MultiBoxTracker tracker;
    private BorderedText borderedText;
    private FaceDetector faceDetector;
    private FloatingActionButton fabAdd, savephoto;
    private SharedPreferenceData sharedPreferenceData = new SharedPreferenceData();
    private LinearLayout ll_forImage;
    private float facedistancemain = Float.MAX_VALUE;
    private List<A01_TeacherProfilePhoto> a01_teacherProfilePhotoList = new ArrayList<>();
    private List<A08_StudentProfilePhoto> studentCreateProfile = new ArrayList<>();
    private String Name, ProfileFor, ClassID, SectionID, StudentSelfID, Attendance = "", fileName = "", SourcefilePath = "", StudentId, TeacherID,
            TeacherAttendance, StudentAttendance, AttendanceType;
    private ArrayList<A08_StudentProfilePhoto> a08_studentProfilePhotos = new ArrayList<>();
    private List<NameEntity> nameEntityList = new ArrayList<>();
    private int myvalue = 0;
    private TextView tv_ForTotalAttendanceCount, tv_for_hint;
    private RecyclerView RV_forTakenAttendancePerson;
    private ConstraintLayout bottomSheetLayout;
    private boolean profileCreation = false;
    private Animation animation;
    private TextToSpeech textToSpeech;
    int i = 0;
    public static void Beep() {

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
        }
        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(this, this);
        facedistancemain = 0.65f;
        FaceDetectorOptions options = new FaceDetectorOptions.Builder().setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build();
        FaceDetector detector = FaceDetection.getClient(options);
        faceDetector = detector;

    }

    @Override
    public void onPreviewSizeChosen(final Size size, final int rotation) {
        final float textSizePx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        borderedText = new BorderedText(textSizePx);
        borderedText.setTypeface(Typeface.MONOSPACE);
        tracker = new MultiBoxTracker(this);
        try {
            detector = TFLiteObjectDetectionAPIModel.create(getAssets(), TF_OD_API_MODEL_FILE, TF_OD_API_LABELS_FILE, TF_OD_API_INPUT_SIZE, TF_OD_API_IS_QUANTIZED);
        } catch (final IOException e) {
            e.printStackTrace();
            finish();
        }
        previewWidth = size.getWidth();
        previewHeight = size.getHeight();
        sensorOrientation = rotation - getScreenOrientation();
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);

        int targetW, targetH;
        if (sensorOrientation == 90 || sensorOrientation == 270) {
            targetH = previewWidth;
            targetW = previewHeight;
        } else {
            targetW = previewWidth;
            targetH = previewHeight;
        }

        int cropW = (int) (targetW / 2.0);
        int cropH = (int) (targetH / 2.0);
        croppedBitmap = Bitmap.createBitmap(cropW, cropH, Config.ARGB_8888);
        portraitBmp = Bitmap.createBitmap(targetW, targetH, Config.ARGB_8888);
        faceBmp = Bitmap.createBitmap(TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE, Config.ARGB_8888);
        frameToCropTransform = ImageUtils.getTransformationMatrix(previewWidth, previewHeight, cropW, cropH, sensorOrientation, MAINTAIN_ASPECT);
        cropToFrameTransform = new Matrix();
        frameToCropTransform.invert(cropToFrameTransform);
        trackingOverlay = (OverlayView) findViewById(R.id.tracking_overlay);
        trackingOverlay.addCallback(new OverlayView.DrawCallback() {
            @Override
            public void drawCallback(final Canvas canvas) {
                tracker.draw(canvas);
                if (isDebug()) {
                    tracker.drawDebug(canvas);
                }
            }
        });
        tracker.setFrameConfiguration(previewWidth, previewHeight, sensorOrientation);
    }

    private void onFacesDetected(long currTimestamp, List<Face> faces, boolean add) {
        cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
        final Canvas canvas = new Canvas(cropCopyBitmap);
        final Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        float minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
        switch (MODE) {
            case TF_OD_API:
                minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                break;
        }
        final List<SimilarityClassifier.Recognition> mappedRecognitions = new LinkedList<>();
        int sourceW = rgbFrameBitmap.getWidth();
        int sourceH = rgbFrameBitmap.getHeight();
        int targetW = portraitBmp.getWidth();
        int targetH = portraitBmp.getHeight();
        Matrix transform = createTransform(sourceW, sourceH, targetW, targetH, sensorOrientation);
        final Canvas cv = new Canvas(portraitBmp);
        cv.drawBitmap(rgbFrameBitmap, transform, null);
        final Canvas cvFace = new Canvas(faceBmp);

        try {
            if (faces != null && faces.size() > 1) {
                if (textToSpeech != null) {
                    if (i == 0) {
                        textToSpeech.speak("do you have someone", TextToSpeech.QUEUE_FLUSH, null, null);
                        i++;
                    }
                }
            } else {
                i=0;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DetectorForVideoActivity.this, "Keep Silence", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            for (Face face : faces) {
                final RectF boundingBox = new RectF(face.getBoundingBox());
                String LeftEye = "", RightEye = "";
                if (face.getLeftEyeOpenProbability() != null) {
                    String LeftEyeNew = face.getLeftEyeOpenProbability().toString().replace(".", "_");
                    LeftEye = LeftEyeNew.split("_")[1];
                }
                if (face.getRightEyeOpenProbability() != null) {
                    String RightEyeNew = face.getRightEyeOpenProbability().toString().replace(".", "_");
                    RightEye = RightEyeNew.split("_")[1];
                }
                if (LeftEye.startsWith("0")) {
                    profileCreation = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI if necessary
                        }
                    });
                    Log.d("LeftEye", LeftEye + "");
                }

                if (RightEye.startsWith("0")) {
                    profileCreation = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update UI if necessary
                        }
                    });
                    Log.d("RightEye", RightEye + "");
                }

                if (boundingBox != null) {
                    cropToFrameTransform.mapRect(boundingBox);
                    RectF faceBB = new RectF(boundingBox);
                    transform.mapRect(faceBB);
                    float sx = ((float) TF_OD_API_INPUT_SIZE) / faceBB.width();
                    float sy = ((float) TF_OD_API_INPUT_SIZE) / faceBB.height();
                    Matrix matrix = new Matrix();
                    matrix.postTranslate(-faceBB.left, -faceBB.top);
                    matrix.postScale(sx, sy);
                    cvFace.drawBitmap(portraitBmp, matrix, null);
                    String label = "";
                    Integer color = Color.YELLOW;
                    Object extra = null;
                    Bitmap crop = null;
                    if (add) {
                        crop = Bitmap.createBitmap(portraitBmp, (int) faceBB.left, (int) faceBB.top, (int) faceBB.width(), (int) faceBB.height());
                    }
                    final long startTime = SystemClock.uptimeMillis();
                    List<SimilarityClassifier.Recognition> resultsAux = detector.recognizeImage(faceBmp, add);
                    lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;
                    if (resultsAux.size() > 0) {
                        SimilarityClassifier.Recognition result = resultsAux.get(0);
                        float conf = result.getDistance();
                        if (conf < facedistancemain) {
                            // Add logic for recognized face
                        }
                    }
                    if (getCameraFacing() == CameraCharacteristics.LENS_FACING_FRONT) {
                        Matrix flip = new Matrix();
                        if (sensorOrientation == 90 || sensorOrientation == 270) {
                            flip.postScale(1, -1, previewWidth / 2.0f, previewHeight / 2.0f);
                        } else {
                            flip.postScale(-1, 1, previewWidth / 2.0f, previewHeight / 2.0f);
                        }
                        flip.mapRect(boundingBox);
                    }
                    final SimilarityClassifier.Recognition result = new SimilarityClassifier.Recognition("0", label, -1f, boundingBox);
                    result.setColor(color);
                    result.setLocation(boundingBox);
                    result.setExtra(extra);
                    result.setCrop(crop);
                    mappedRecognitions.add(result);
                }
            }

            updateResults(currTimestamp, mappedRecognitions);

        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void updateResults(long currTimestamp, final List<SimilarityClassifier.Recognition> mappedRecognitions) {
        tracker.trackResults(mappedRecognitions, currTimestamp);
        trackingOverlay.postInvalidate();
        computingDetection = false;
        if (mappedRecognitions.size() > 0) {
            SimilarityClassifier.Recognition rec = mappedRecognitions.get(0);
            if (rec.getExtra() != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }
    }

    @Override
    protected void processImage() {
        ++timestamp;
        final long currTimestamp = timestamp;
        trackingOverlay.postInvalidate();
        if (computingDetection) {
            readyForNextImage();
            return;
        }
        computingDetection = true;
        rgbFrameBitmap.setPixels(getRgbBytes(), 0, previewWidth, 0, 0, previewWidth, previewHeight);
        readyForNextImage();
        final Canvas canvas = new Canvas(croppedBitmap);
        canvas.drawBitmap(rgbFrameBitmap, frameToCropTransform, null);
        if (SAVE_PREVIEW_BITMAP) {
            ImageUtils.saveBitmap(croppedBitmap);
        }
        InputImage image = InputImage.fromBitmap(croppedBitmap, 0);
        faceDetector.process(image).addOnSuccessListener(new OnSuccessListener<List<Face>>() {
            @Override
            public void onSuccess(List<Face> faces) {
                if (faces.size() == 0) {
                    profileCreation = false;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        /*    tv_for_hint.setText(getResources().getString(R.string.eye_blink_please));
                            tv_for_hint.setAnimation(animation);*/
                        }
                    });
                    updateResults(currTimestamp, new LinkedList<>());
                    return;
                }
                runInBackground(new Runnable() {
                    @Override
                    public void run() {
                        onFacesDetected(currTimestamp, faces, addPending);
                        addPending = false;
                    }
                });
            }
        });
    }

    @Override
    protected void setNumThreads(final int numThreads) {
        runInBackground(() -> detector.setNumThreads(numThreads));
    }

    private Matrix createTransform(final int srcWidth, final int srcHeight, final int dstWidth, final int dstHeight, final int applyRotation) {
        Matrix matrix = new Matrix();
        if (applyRotation != 0) {
            if (applyRotation % 90 != 0) {

            }
            matrix.postTranslate(-srcWidth / 2.0f, -srcHeight / 2.0f);
            matrix.postRotate(applyRotation);
        }
        if (applyRotation != 0) {
            matrix.postTranslate(dstWidth / 2.0f, dstHeight / 2.0f);
        }
        return matrix;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TextToSpeech", "Language is not supported");
            } else {
                // Set the speech rate. 1.0 is the normal rate, lower values slow down the speech.
                textToSpeech.setSpeechRate(0.8f); // You can adjust this value to get the desired speed
            }
        } else {
            Log.e("TextToSpeech", "Initialization failed");
        }
    }

    private enum DetectorMode {
        TF_OD_API;
    }

    private String _formatDate(Date date, String pattern, TimeZone timeZone) {
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        format.setTimeZone(timeZone);
        return format.format(date);
    }

    private File WriteBitmap(Bitmap portraitBmp) {
        Bitmap photo1;
        photo1 = Imagehelpher.getRoundedCornerBitmap(portraitBmp, 10);
        FileOutputStream fos = null;
        ByteArrayOutputStream streamForCompression = new ByteArrayOutputStream();
        photo1.compress(Bitmap.CompressFormat.JPEG, 85, streamForCompression);
        byte[] bArrayForCompression = streamForCompression.toByteArray();
        File CompressedDir = getDir("ProfileImageKGBV", 0);
        // String deviceID = sharedPreferenceData.getDeviceId(DetectorActivity.this);
        Random random = new Random();
        int randamName = random.nextInt();
        fileName = randamName + "_" + System.currentTimeMillis() + ".jpg";
        File fileNew = new File(CompressedDir, fileName);
        SourcefilePath = fileNew.toString();
        try {
            fos = new FileOutputStream(fileNew);
            fos.write(bArrayForCompression);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNew;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tfe_od_camera_connection_fragment_tracking;
    }

    @Override
    protected Size getDesiredPreviewFrameSize() {
        return DESIRED_PREVIEW_SIZE;
    }

    private void GetDateTime() {
        ButterKnife.bind(DetectorForVideoActivity.this);
        if (!TrueTime.isInitialized()) {
            Toast.makeText(DetectorForVideoActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            new App();

            if (TrueTime.isInitialized()) {
                Date trueTime = TrueTimeRx.now();
                Date deviceTime = new Date();
                if (trueTime != null && deviceTime != null) {
                    String NtpServerTime = _formatDate(trueTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    String LocalTime = _formatDate(deviceTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    sharedPreferenceData.SetDateTime(DetectorForVideoActivity.this, NtpServerTime, LocalTime);
                }
            }

        } else {
            if (TrueTime.isInitialized()) {
                Date trueTime = TrueTimeRx.now();
                Date deviceTime = new Date();
                if (trueTime != null && deviceTime != null) {
                    String NtpServerTime = _formatDate(trueTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    String LocalTime = _formatDate(deviceTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    sharedPreferenceData.SetDateTime(DetectorForVideoActivity.this, NtpServerTime, LocalTime);
                }
            }
        }
    }

    private void onAddClick() {
        addPending = true;
    }

    @Override
    protected void setUseNNAPI(final boolean isChecked) {
        runInBackground(() -> detector.setUseNNAPI(isChecked));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

}