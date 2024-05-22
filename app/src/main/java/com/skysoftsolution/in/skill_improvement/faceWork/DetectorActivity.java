package com.skysoftsolution.in.skill_improvement.faceWork;
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
public class DetectorActivity extends CameraActivity implements OnImageAvailableListener {
    private static final int TF_OD_API_INPUT_SIZE = 112;
    private static final boolean TF_OD_API_IS_QUANTIZED = false;
    private static final String TF_OD_API_MODEL_FILE = "mobile_face_net.tflite";
    private static final String TF_OD_API_LABELS_FILE = "file:///android_asset/labelmap.txt";
    private static final DetectorMode MODE = DetectorMode.TF_OD_API;
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
            try {
                Name = bundle.getString("Name");
                StudentSelfID = bundle.getString("StudentSelfID", "");
                ProfileFor = bundle.getString("ProfileFor");
                StudentId = bundle.getString("StudentId");
                TeacherID = bundle.getString("TeacherID");
                TeacherAttendance = bundle.getString("TeacherAttendance");
                StudentAttendance = bundle.getString("StudentAttendance");
                AttendanceType = bundle.getString("Type");
                ClassID = bundle.getString("ClassID");
                SectionID = bundle.getString("SectionID");

                tv_for_hint = findViewById(R.id.tv_for_hint);

                if ((TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("TeacherAttendance")) ||
                        TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("Self")) {
                    tv_for_hint.setVisibility(View.VISIBLE);
                }

                animation = AnimationUtils.loadAnimation(DetectorActivity.this, R.anim.animation_rotate);
                tv_for_hint.setAnimation(animation);

                if (AttendanceType != null) {
                    if (AttendanceType.equalsIgnoreCase("M")) {
                        Attendance = "1";
                    } else if (AttendanceType.equalsIgnoreCase("E")) {
                        Attendance = "2";
                    }
                }

                if (StudentAttendance != null && StudentAttendance.equalsIgnoreCase("StudentAttendance")) {
                    if (!ClassID.equalsIgnoreCase("") && !SectionID.equalsIgnoreCase("")) {
                        DataSource dataSource = new DataSource(DetectorActivity.this);
                        dataSource.open();
                        a08_studentProfilePhotos = dataSource.getStudentForAttendance_Embeedings(SectionID, ClassID);
                        dataSource.close();
                    } else {
                        Toast.makeText(DetectorActivity.this, "Student Data Not Found", Toast.LENGTH_SHORT).show();
                    }
                } else if (StudentAttendance != null && StudentAttendance.equalsIgnoreCase("Self")) {
                    if (!ClassID.equalsIgnoreCase("") && !SectionID.equalsIgnoreCase("")) {
                /*        DataSource dataSource = new DataSource(DetectorActivity.this);
                        dataSource.open();
                        a08_studentProfilePhotos = dataSource.gettblA05studentdetailList_StudentID(SectionID, ClassID, StudentSelfID);
                        dataSource.close();*/
                    } else {
                        Toast.makeText(DetectorActivity.this, "Student Data Not Found", Toast.LENGTH_SHORT).show();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        facedistancemain = 0.65f;

        fabAdd = findViewById(R.id.fab_add);
        savephoto = findViewById(R.id.savephoto);
        ll_forImage = findViewById(R.id.ll_forImage);
        tv_ForTotalAttendanceCount = findViewById(R.id.tv_ForTotalAttendanceCount1);
        RV_forTakenAttendancePerson = findViewById(R.id.RV_forTakenAttendancePerson1);

        bottomSheetLayout = findViewById(R.id.bottom_sheet_layout);

/*        if (StudentId == null && TeacherID == null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.takeAttendance));
            fabAdd.setVisibility(View.GONE);
            savephoto.setVisibility(View.GONE);
            bottomSheetLayout.setVisibility(View.VISIBLE);
        } else {
            getSupportActionBar().setTitle("Photo Click ");
            fabAdd.setVisibility(View.VISIBLE);
            savephoto.setVisibility(View.VISIBLE);
            bottomSheetLayout.setVisibility(View.GONE);
        }*/

        savephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                String date1 = _formatDate(date, "dd-MM-yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                if (ProfileFor != null && ProfileFor.contains("Teacher")) {
                    if (a01_teacherProfilePhotoList != null && a01_teacherProfilePhotoList.size() > 2) {
                        DataSource datasource = new DataSource(DetectorActivity.this);
                        datasource.open();
                        A01_Teacher user = datasource.getA01_TeacherDetails();
                        A01_TeacherProfile a01_teacherProfile = new A01_TeacherProfile();
                        a01_teacherProfile.setTP_CreatedOn(date1);
                        a01_teacherProfile.setTP_TeacherID(TeacherID);
                        a01_teacherProfile.setTP_IsSync("false");
                        a01_teacherProfile.setTP_VerifiedBy("0");
                        a01_teacherProfile.setTP_IsVerified("0");

                        if (TeacherID != null) {
                            if (ProfileFor != null && ProfileFor.contains("Teacher_Update")) {
                                datasource.delete_tbl_A01_Teacherprofile_for_id(TeacherID);
                                datasource.delete_tbl_A01_TeacherProfilePhoto_for_id(TeacherID);
                            }
                        }
                        long b = datasource.insertA01_TeacherProfile(a01_teacherProfile);
                        for (int j = 0; j < a01_teacherProfilePhotoList.size(); j++) {
                            a01_teacherProfilePhotoList.get(j).setProfile_IsSync("false");
                            a01_teacherProfilePhotoList.get(j).setTPP_ProfileID(b + "");
                            long a = datasource.insertA01_TeacherProfilePhoto(a01_teacherProfilePhotoList.get(j));
                        }
                        datasource.close();
                        Toast.makeText(DetectorActivity.this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(DetectorActivity.this, DashBoardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();
                    } else {
                        Toast.makeText(DetectorActivity.this, getResources().getString(R.string.plzclickthreephoto_offace) + "", Toast.LENGTH_SHORT).show();
                    }
                } else if (ProfileFor != null && ProfileFor.contains("Student")) {
                    if (studentCreateProfile != null && studentCreateProfile.size() > 2) {
                        DataSource datasource = new DataSource(DetectorActivity.this);
                        datasource.open();

                        String SchoolCod = datasource.get_SchoolCode(StudentId);
                        A07_StudentProfile studentProfile = new A07_StudentProfile();
                        studentProfile.setSP_CreationDate(date1);
                        studentProfile.setSP_StudentID(StudentId);
                        studentProfile.setSP_IsSync("false");
                        studentProfile.setSP_ClassID(ClassID);
                        studentProfile.setSP_SectionID(SectionID);
                        studentProfile.setSP_SchoolID(SchoolCod);

                        if (StudentId != null) {
                            if (ProfileFor != null && ProfileFor.contains("Student_Update")) {
                                datasource.delete_tbl_A07_StudentProfile_for_id(StudentId);
                                datasource.delete_tbl_A08_StudentprofileProfilePhoto_for_id(StudentId);
                            }
                        }
                        Long l = datasource.insertA07_Studentprofile(studentProfile);
                        if (l > 0) {
                            for (int j = 0; j < studentCreateProfile.size(); j++) {
                                studentCreateProfile.get(j).setSP_IsSync("false");
                                studentCreateProfile.get(j).setSPP_ProfileID(l + "");
                                long a = datasource.insertA08_Studentprofile(studentCreateProfile.get(j));
                            }
                        }
                        Toast.makeText(DetectorActivity.this, getResources().getString(R.string.saved_successfully), Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(DetectorActivity.this, DashBoardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        finish();


                    } else {
                        Toast.makeText(DetectorActivity.this, getResources().getString(R.string.plzclickthreephoto_offace) + "", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ProfileFor != null && ProfileFor.contains("Teacher")) {
                    if (a01_teacherProfilePhotoList != null && a01_teacherProfilePhotoList.size() == 3) {
                        Toast.makeText(DetectorActivity.this, "आप 3 से अधिक फ़ोटो नहीं ले सकते हैं !!", Toast.LENGTH_SHORT).show();
                    } else {
                        onAddClick();
                    }
                } else if (ProfileFor != null && ProfileFor.contains("Student")) {
                    if (studentCreateProfile != null && studentCreateProfile.size() == 3) {
                        Toast.makeText(DetectorActivity.this, "आप 3 से अधिक फ़ोटो नहीं ले सकते हैं !!", Toast.LENGTH_SHORT).show();
                    } else {
                        onAddClick();
                    }
                }
            }
        });

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
            if (StudentAttendance != null && (StudentAttendance.equalsIgnoreCase("StudentAttendance") || StudentAttendance.equalsIgnoreCase("Self"))) {
                addstudent();
            } else if (TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("TeacherAttendance")) {
                addteacher();
            } else if (TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("Self")) {
                addteacherSelf();
            }
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

    private void addteacherSelf() {
        DataSource dataSource = new DataSource(DetectorActivity.this);
        dataSource.open();
        List<A01_TeacherProfilePhoto> trainerProfilePhotoList = dataSource.getTeacherProfileDatalist(TeacherID);
        if (trainerProfilePhotoList != null) {
            for (int a = 0; a < trainerProfilePhotoList.size(); a++) {
                RectF rectF = new RectF(trainerProfilePhotoList.get(a).getLeft(), trainerProfilePhotoList.get(a).getTop(), trainerProfilePhotoList.get(a).getRight(), trainerProfilePhotoList.get(a).getBottom());
                SimilarityClassifier.Recognition recognition = new SimilarityClassifier.Recognition(trainerProfilePhotoList.get(a).getTPP_PersonID(), trainerProfilePhotoList.get(a).getTitle(), facedistancemain, rectF);
                recognition.setColor(trainerProfilePhotoList.get(a).getColor());
                List<Float> arrPackageData = new ArrayList<>();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Float>>() {
                }.getType();

                arrPackageData = gson.fromJson(trainerProfilePhotoList.get(a).getTPP_Embeeding(), type);
                float[][] embeedings22 = new float[1][192];
                for (int j = 0; j < arrPackageData.size(); j++) {
                    embeedings22[0][j] = arrPackageData.get(j);
                }
                recognition.setExtra(embeedings22);
                detector.register(trainerProfilePhotoList.get(a).getTPP_TeacherID() + "_" + a, recognition);
            }
        } else {
            Toast.makeText(DetectorActivity.this, "Data Not found", Toast.LENGTH_SHORT).show();
        }
        dataSource.close();
    }

    private void addteacher() {
        DataSource dataSource = new DataSource(DetectorActivity.this);
        dataSource.open();
        List<A01_TeacherProfilePhoto> trainerProfilePhotoList = dataSource.getTeacherProfileDatalist("");
        if (trainerProfilePhotoList != null) {
            for (int a = 0; a < trainerProfilePhotoList.size(); a++) {
                RectF rectF = new RectF(trainerProfilePhotoList.get(a).getLeft(), trainerProfilePhotoList.get(a).getTop(), trainerProfilePhotoList.get(a).getRight(), trainerProfilePhotoList.get(a).getBottom());
                SimilarityClassifier.Recognition recognition = new SimilarityClassifier.Recognition(trainerProfilePhotoList.get(a).getTPP_PersonID(), trainerProfilePhotoList.get(a).getTitle(), facedistancemain, rectF);
                recognition.setColor(trainerProfilePhotoList.get(a).getColor());
                List<Float> arrPackageData = new ArrayList<>();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Float>>() {
                }.getType();

                arrPackageData = gson.fromJson(trainerProfilePhotoList.get(a).getTPP_Embeeding(), type);
                float[][] embeedings22 = new float[1][192];
                for (int j = 0; j < arrPackageData.size(); j++) {
                    embeedings22[0][j] = arrPackageData.get(j);
                }
                recognition.setExtra(embeedings22);
                detector.register(trainerProfilePhotoList.get(a).getTPP_TeacherID() + "_" + a, recognition);
            }
        } else {
            Toast.makeText(DetectorActivity.this, "Data Not found", Toast.LENGTH_SHORT).show();
        }
        dataSource.close();
    }

    public void addstudent() {
        if (a08_studentProfilePhotos != null && a08_studentProfilePhotos.size() > 0) {
            for (A08_StudentProfilePhoto a08_studentProfilePhoto : a08_studentProfilePhotos) {
                String studentid = a08_studentProfilePhoto.getSPP_StudentID();
                RectF rectF = new RectF(a08_studentProfilePhoto.getLeft(), a08_studentProfilePhoto.getTop(), a08_studentProfilePhoto.getRight(), a08_studentProfilePhoto.getBottom());
                SimilarityClassifier.Recognition recognition = new SimilarityClassifier.Recognition("", a08_studentProfilePhoto.getTitle(), 0f, rectF);
                recognition.setColor(a08_studentProfilePhoto.getColor());
                if (a08_studentProfilePhoto.getSPP_Embeeding() != null && !a08_studentProfilePhoto.getSPP_Embeeding().equalsIgnoreCase("")) {
                    List<Float> arrPackageData = new ArrayList<>();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Float>>() {
                    }.getType();
                    arrPackageData = gson.fromJson(a08_studentProfilePhoto.getSPP_Embeeding(), type);
                    float[][] embeedings22 = new float[1][192];
                    for (int j = 0; j < arrPackageData.size(); j++) {
                        embeedings22[0][j] = arrPackageData.get(j);
                    }
                    recognition.setExtra(embeedings22);
                    detector.register(studentid, recognition);
                }
            }
        } else {
            Toast.makeText(DetectorActivity.this, "Data Not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAddFaceDialog(SimilarityClassifier.Recognition rec) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.image_edit_dialog, null);
        ImageView ivFace = dialogLayout.findViewById(R.id.dlg_image);
        TextView tvTitle = dialogLayout.findViewById(R.id.dlg_title);
        EditText etName = dialogLayout.findViewById(R.id.dlg_input);
        tvTitle.setText("Add Face");
        ivFace.setImageBitmap(rec.getCrop());
        Bitmap profilepic = rec.getCrop();
        etName.setText(Name);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dlg, int i) {
                if (ll_forImage.getChildCount() < 3) {
                    String name = etName.getText().toString();
                    if (name.isEmpty()) {
                        return;
                    }
                    rec.setCrop(null);
                    final float[] embeedings = ((float[][]) rec.getExtra())[0];
                    float[] mValue = embeedings;
                    ArrayList<Float> floatArrayList = new ArrayList<>();
                    floatArrayList.clear();

                    for (int a = 0; a < mValue.length; a++) {
                        floatArrayList.add(mValue[a]);
                    }


                    if (ProfileFor != null && ProfileFor.contains("Teacher")) {
                        try {
                            if (a01_teacherProfilePhotoList != null && a01_teacherProfilePhotoList.size() > 0) {
                                setBitMapWithImageEmbeedingForTeacher(floatArrayList, profilepic);
                            } else {
                                setBitMapWithImageEmbeedingForTeacher(floatArrayList, profilepic);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (ProfileFor != null && ProfileFor.contains("Student")) {
                        try {
                            if (studentCreateProfile != null && studentCreateProfile.size() > 0) {
                                setBitMapWithImageEmbeedingForStudent(floatArrayList, profilepic);
                            } else {
                                setBitMapWithImageEmbeedingForStudent(floatArrayList, profilepic);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    Toast.makeText(DetectorActivity.this, getResources().getString(R.string.plzclickthreephoto_offace) + "", Toast.LENGTH_SHORT).show();
                }
                dlg.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.setView(dialogLayout);
        builder.show();

    }

    private void CheckDuplicatePhotoForStudent(List<A08_StudentProfilePhoto> a08_studentProfilePhotos, ArrayList<Float> floatArrayList, Bitmap profilepic) throws IOException {
        detectorForCheck = TFLiteObjectDetectionAPIModel.create(getAssets(), TF_OD_API_MODEL_FILE, TF_OD_API_LABELS_FILE, TF_OD_API_INPUT_SIZE, TF_OD_API_IS_QUANTIZED);

        for (A08_StudentProfilePhoto a08_StudentProfilePhoto : a08_studentProfilePhotos) {
            String teacher_ID = a08_StudentProfilePhoto.getSPP_StudentID();
            RectF rectF = new RectF(a08_StudentProfilePhoto.getLeft(), a08_StudentProfilePhoto.getTop(), a08_StudentProfilePhoto.getRight(), a08_StudentProfilePhoto.getBottom());
            SimilarityClassifier.Recognition recognition = new SimilarityClassifier.Recognition("", a08_StudentProfilePhoto.getTitle(), 0f, rectF);
            recognition.setColor(a08_StudentProfilePhoto.getColor());
            if (a08_StudentProfilePhoto.getSPP_Embeeding() != null && !a08_StudentProfilePhoto.getSPP_Embeeding().equalsIgnoreCase("")) {
                List<Float> arrPackageData = new ArrayList<>();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Float>>() {
                }.getType();
                arrPackageData = gson.fromJson(a08_StudentProfilePhoto.getSPP_Embeeding(), type);
                float[][] embeedings22 = new float[1][192];
                for (int j = 0; j < arrPackageData.size(); j++) {
                    embeedings22[0][j] = arrPackageData.get(j);
                }
                recognition.setExtra(embeedings22);
                detectorForCheck.register(teacher_ID, recognition);
                Bitmap bitmap = cropMthod(profilepic);
                List<SimilarityClassifier.Recognition> resultsAux = detectorForCheck.recognizeImage(bitmap, true);
                if (resultsAux.size() > 0) {
                    SimilarityClassifier.Recognition result = resultsAux.get(0);
                    float conf = result.getDistance();
                    if (conf < facedistancemain) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setBitMapWithImageEmbeedingForStudent(floatArrayList, bitmap);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setBitMapWithImageEmbeedingForStudent(floatArrayList, bitmap);
                            }
                        });
                    }
                }
            }
        }
    }

    private void setBitMapWithImageEmbeedingForStudent(ArrayList<Float> floatArrayList, Bitmap profilepic) {
        SharedPreferences sharedLocation = getSharedPreferences("AppLocation", Context.MODE_PRIVATE);
        String lattitude = sharedLocation.getString("latitude", "0.0");
        String longitude = sharedLocation.getString("longitude", "0.0");
        File file_path = WriteBitmap(profilepic);
        A08_StudentProfilePhoto a01_teacherProfilePhoto = new A08_StudentProfilePhoto();
        a01_teacherProfilePhoto.setSPP_Lattitude(lattitude);
        a01_teacherProfilePhoto.setSPP_Longitude(longitude);
        a01_teacherProfilePhoto.setSPP_FileName(file_path.getName());
        a01_teacherProfilePhoto.setSPP_StudentID(StudentId);
        a01_teacherProfilePhoto.setSPP_FilePath(file_path.getPath());
        a01_teacherProfilePhoto.setSPP_Embeeding(floatArrayList + "");

        studentCreateProfile.add(a01_teacherProfilePhoto);
        View textView = getTextView(file_path.getPath());
        RelativeLayout relativeLayout = getRelativeLayoutForInertingIntoLinearLayout(portraitBmp.getWidth(), portraitBmp.getHeight());
        relativeLayout.addView(getImageViewForRelativeLayoutForGallery(profilepic, 200, 200));
        relativeLayout.setVisibility(View.VISIBLE);
        View crossImage = getImageViewForRelativeLayoutCrossImage("Student");
        crossImage.setPadding(0, 0, 0, 0);
        relativeLayout.setPadding(0, 0, 0, 0);
        relativeLayout.addView(crossImage);
        relativeLayout.addView(textView);
        ll_forImage.setVisibility(View.VISIBLE);
        ll_forImage.addView(relativeLayout);
    }

    private void CheckDuplicatePhoto(List<A01_TeacherProfilePhoto> a01_teacherProfilePhotoList, ArrayList<Float> floatArrayList, Bitmap profilepic) throws IOException {
        detectorForCheck = TFLiteObjectDetectionAPIModel.create(getAssets(), TF_OD_API_MODEL_FILE, TF_OD_API_LABELS_FILE, TF_OD_API_INPUT_SIZE, TF_OD_API_IS_QUANTIZED);
        for (A01_TeacherProfilePhoto a01_teacherProfilePhoto : a01_teacherProfilePhotoList) {
            String teacher_ID = a01_teacherProfilePhoto.getTPP_TeacherID();
            RectF rectF = new RectF(a01_teacherProfilePhoto.getLeft(), a01_teacherProfilePhoto.getTop(), a01_teacherProfilePhoto.getRight(), a01_teacherProfilePhoto.getBottom());
            SimilarityClassifier.Recognition recognition = new SimilarityClassifier.Recognition("", a01_teacherProfilePhoto.getTitle(), 0f, rectF);
            recognition.setColor(a01_teacherProfilePhoto.getColor());
            if (a01_teacherProfilePhoto.getTPP_Embeeding() != null && !a01_teacherProfilePhoto.getTPP_Embeeding().equalsIgnoreCase("")) {
                List<Float> arrPackageData = new ArrayList<>();
                Gson gson = new Gson();
                Type type = new TypeToken<List<Float>>() {
                }.getType();
                arrPackageData = gson.fromJson(a01_teacherProfilePhoto.getTPP_Embeeding(), type);
                float[][] embeedings22 = new float[1][192];
                for (int j = 0; j < arrPackageData.size(); j++) {
                    embeedings22[0][j] = arrPackageData.get(j);
                }
                recognition.setExtra(embeedings22);
                detectorForCheck.register(teacher_ID, recognition);
                faceBmp = Bitmap.createBitmap(TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE, Config.ARGB_8888);
                Bitmap bitmap = cropMthod(profilepic);
                List<SimilarityClassifier.Recognition> resultsAux = detectorForCheck.recognizeImage(bitmap, true);
                if (resultsAux.size() > 0) {
                    SimilarityClassifier.Recognition result = resultsAux.get(0);
                    float conf = result.getDistance();
                    if (conf < facedistancemain) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setBitMapWithImageEmbeedingForTeacher(floatArrayList, profilepic);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setBitMapWithImageEmbeedingForTeacher(floatArrayList, profilepic);
                            }
                        });
                    }
                }
            }
        }
    }

    private void setBitMapWithImageEmbeedingForTeacher(ArrayList<Float> floatArrayList, Bitmap profilepic) {
        Date date = new Date();
        String date1 = _formatDate(date, "dd-MM-yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
        SharedPreferences sharedLocation = getSharedPreferences("AppLocation", Context.MODE_PRIVATE);
        String lattitude = sharedLocation.getString("latitude", "0.0");
        String longitude = sharedLocation.getString("longitude", "0.0");
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        File file_path = WriteBitmap(profilepic);
        A01_TeacherProfilePhoto a01_teacherProfilePhoto = new A01_TeacherProfilePhoto();
        a01_teacherProfilePhoto.setTPP_ClickedOn(date1);
        a01_teacherProfilePhoto.setTPP_Lattitude(lattitude);
        a01_teacherProfilePhoto.setTPP_Longitude(longitude);
        a01_teacherProfilePhoto.setTPP_FileName(file_path.getName());
        a01_teacherProfilePhoto.setTPP_FileFullPath(file_path.getPath());
        a01_teacherProfilePhoto.setTPP_Embeeding(floatArrayList + "");
        a01_teacherProfilePhoto.setTPP_TeacherID(TeacherID);
        a01_teacherProfilePhotoList.add(a01_teacherProfilePhoto);
        /*todo end*/
        View textView = getTextView(file_path.getPath());
        RelativeLayout relativeLayout = getRelativeLayoutForInertingIntoLinearLayout(portraitBmp.getWidth(), portraitBmp.getHeight());
        relativeLayout.addView(getImageViewForRelativeLayoutForGallery(profilepic, 200, 200));
        relativeLayout.setVisibility(View.VISIBLE);
        View crossImage = getImageViewForRelativeLayoutCrossImage("Teacher");
        crossImage.setPadding(0, 0, 0, 0);
        relativeLayout.setPadding(0, 0, 0, 0);
        relativeLayout.addView(crossImage);
        relativeLayout.addView(textView);
        ll_forImage.setVisibility(View.VISIBLE);
        ll_forImage.addView(relativeLayout);
    }

    private Bitmap cropMthod(Bitmap pic) {
        Bitmap croppedBitmap = Bitmap.createBitmap(pic, 0, 0, TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE);
        int originalWidth = pic.getWidth();
        int originalHeight = pic.getHeight();
        int left, top, right, bottom;
        if (originalWidth > originalHeight) {
            left = (originalWidth - originalHeight) / 2;
            top = 0;
            right = left + originalHeight;
            bottom = originalHeight;
        } else {
            left = 0;
            top = (originalHeight - originalWidth) / 2;
            right = originalWidth;
            bottom = top + originalWidth;
        }
        Bitmap croppedBitmap1 = Bitmap.createBitmap(pic, left, top, right - left, bottom - top);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(croppedBitmap1, TF_OD_API_INPUT_SIZE, TF_OD_API_INPUT_SIZE, true);
        return resizedBitmap;
    }

    private void onFacesDetected(long currTimestamp, List<Face> faces, boolean add) {
        cropCopyBitmap = Bitmap.createBitmap(croppedBitmap);
        final Canvas canvas = new Canvas(cropCopyBitmap);
        final Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(2.0f);
        float minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
        switch (MODE) {
            case TF_OD_API:
                minimumConfidence = MINIMUM_CONFIDENCE_TF_OD_API;
                break;
        }
        final List<SimilarityClassifier.Recognition> mappedRecognitions = new LinkedList<SimilarityClassifier.Recognition>();
        int sourceW = rgbFrameBitmap.getWidth();
        int sourceH = rgbFrameBitmap.getHeight();
        int targetW = portraitBmp.getWidth();
        int targetH = portraitBmp.getHeight();
        Matrix transform = createTransform(sourceW, sourceH, targetW, targetH, sensorOrientation);
        final Canvas cv = new Canvas(portraitBmp);
        cv.drawBitmap(rgbFrameBitmap, transform, null);
        final Canvas cvFace = new Canvas(faceBmp);
        try {
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
//                            tv_for_hint.setText("");

                        }
                    });
                    Log.d("LeftEye", LeftEye + "");
                }

                if (RightEye.startsWith("0")) {
                    profileCreation = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv_for_hint.setText("");

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
                        extra = result.getExtra();
                        Date date = new Date();
                        String currentDate = _formatDate(date, "ddMMyyyy", TimeZone.getTimeZone("Asia/Kolkata"));

                        if (StudentAttendance != null && (StudentAttendance.equalsIgnoreCase("StudentAttendance")
                                || StudentAttendance.equalsIgnoreCase("Self"))) {
                            float conf = result.getDistance();
                            if (conf < facedistancemain) {
                                label = result.getTitle();
                                String y = "";
                                String studentName = "", studentName1 = "";
                                if (label != null && !label.equalsIgnoreCase("")) {
                                    DataSource dataSource = new DataSource(DetectorActivity.this);
                                    dataSource.open();
                                    int yesno = dataSource.getStudentAlreadypresent(label, currentDate, Attendance);
                                    studentName = dataSource.getNameForStudentA5(label);
                                    studentName1 = dataSource.getNameForStudentA5(label);
                                    dataSource.close();
                                    if (yesno > 0) {
                                        y = "yes";
                                        studentName = studentName + " Already  Present";
                                    } else {
                                        saveStudentAttendance(label, AttendanceType, studentName1, currentDate, Attendance);
                                    }
                                }
                                if (y.equalsIgnoreCase("yes")) {
                                    label = studentName;
                                } else {
                                    label = studentName;
                                }
                                if (result.getId().equals("0")) {
                                    color = Color.GREEN;
                                } else {
                                    color = Color.RED;
                                }
                            }

                        } else if (TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("TeacherAttendance")) {

                            if (profileCreation) {
                                float conf = result.getDistance();
                                if (conf < facedistancemain) {
                                    label = result.getTitle().split("_")[0];
                                    String y = "";
                                    String name = "", name1 = "";
                                    if (label != null && !label.equalsIgnoreCase("")) {
                                        y = "yes";
                                        DataSource dataSource = new DataSource(DetectorActivity.this);
                                        dataSource.open();
                                        name = dataSource.gettbl_A01_TeacherList(label);
                                        name1 = name;
                                        int j = dataSource.tbl_A09Teacherattendencedata_already_presnt(label, currentDate, Attendance);
                                        dataSource.close();
                                        if (j > 0) {
                                            name = name + " Already Present";
                                        } else {
                                            saveTeacherAttendance(label, AttendanceType, name1, currentDate, Attendance);
                                        }
                                    }
                                    if (y.equalsIgnoreCase("yes")) {
                                        label = name + "Present";
                                    } else {
                                        label = name;
                                    }
                                    if (result.getId().equals("0")) {
                                        color = Color.GREEN;
                                    } else {
                                        color = Color.RED;
                                    }
                                }
                            }

                        } else if (TeacherAttendance != null && TeacherAttendance.equalsIgnoreCase("Self")) {

                            if (profileCreation) {
                                float conf = result.getDistance();
                                if (conf < facedistancemain) {
                                    label = result.getTitle().split("_")[0];
                                    String y = "";
                                    String name = "", name1 = "";

                                    if (label != null && !label.equalsIgnoreCase("")) {

                                        y = "yes";
                                        DataSource dataSource = new DataSource(DetectorActivity.this);
                                        dataSource.open();
                                        name = dataSource.gettbl_A01_TeacherList(label);
                                        name1 = name;
                                        int j = dataSource.tbl_A09Teacherattendencedata_already_presnt(label, currentDate, Attendance);
                                        dataSource.close();
                                        if (j > 0) {
                                            name = name + " Already Present";
                                        } else {
                                            Beep();
                                            saveTeacherAttendance(label, AttendanceType, name1, currentDate, Attendance);
                                        }
                                    }

                                    if (y.equalsIgnoreCase("yes")) {
                                        label = name + "Present";
                                    } else {
                                        label = name;
                                    }
                                    if (result.getId().equals("0")) {
                                        color = Color.GREEN;
                                    } else {
                                        color = Color.RED;
                                    }
                                }
                            }

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
                        showAddFaceDialog(rec);
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

    private enum DetectorMode {
        TF_OD_API;
    }

    private View getTextView(String filePath) {
        TextView textView = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(110, 140);
        layoutParams.setMargins(0, 0, 0, 0);
        textView.setText(filePath);
        textView.setVisibility(View.GONE);
        return textView;
    }

    private RelativeLayout getRelativeLayoutForInertingIntoLinearLayout(int imageWidth, int imageHeight) {
        RelativeLayout relativeLayout = new RelativeLayout(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return relativeLayout;
    }

    private View getImageViewForRelativeLayoutForGallery(final Bitmap image, int imageWidth, int imageHeight) {
        final ImageView imageView = new ImageView(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
        layoutParams.setMargins(10, 10, 0, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageBitmap(image);
        return imageView;
    }

    private View getImageViewForRelativeLayoutCrossImage(String forwhat) {
        final ImageView imageViewForCross = new ImageView(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        imageViewForCross.setLayoutParams(layoutParams);
        imageViewForCross.setImageResource(R.drawable.ic_cross);

        imageViewForCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewParent viewParent = imageViewForCross.getParent();
                RelativeLayout relativeLayout = (RelativeLayout) viewParent;
                TextView textView = (TextView) relativeLayout.getChildAt(2);
                String str = textView.getText().toString();
                if (str != null) {
                    File file = new File(str);
                    if (forwhat.equalsIgnoreCase("Teacher")) {
                        for (int i = 0; i < a01_teacherProfilePhotoList.size(); i++) {
                            if (a01_teacherProfilePhotoList.get(i).getTPP_FileFullPath().equalsIgnoreCase(file.toString())) {
                                Toast.makeText(DetectorActivity.this, "" + getResources().getString(R.string.remove_success), Toast.LENGTH_SHORT).show();
                                ll_forImage.removeViewAt(i);
                                a01_teacherProfilePhotoList.remove(i);
                            }
                        }
                    } else if (forwhat.equalsIgnoreCase("Student")) {
                        for (int i = 0; i < studentCreateProfile.size(); i++) {
                            if (studentCreateProfile.get(i).getSPP_FilePath().equalsIgnoreCase(file.toString())) {
                                Toast.makeText(DetectorActivity.this, "" + getResources().getString(R.string.remove_success), Toast.LENGTH_SHORT).show();
                                ll_forImage.removeViewAt(i);
                                studentCreateProfile.remove(i);
                            }
                        }
                    }
                }
            }
        });
        return imageViewForCross;
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
        ButterKnife.bind(DetectorActivity.this);
        if (!TrueTime.isInitialized()) {
            Toast.makeText(DetectorActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
            new App();

            if (TrueTime.isInitialized()) {
                Date trueTime = TrueTimeRx.now();
                Date deviceTime = new Date();
                if (trueTime != null && deviceTime != null) {
                    String NtpServerTime = _formatDate(trueTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    String LocalTime = _formatDate(deviceTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    sharedPreferenceData.SetDateTime(DetectorActivity.this, NtpServerTime, LocalTime);
                }
            }

        } else {
            if (TrueTime.isInitialized()) {
                Date trueTime = TrueTimeRx.now();
                Date deviceTime = new Date();
                if (trueTime != null && deviceTime != null) {
                    String NtpServerTime = _formatDate(trueTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    String LocalTime = _formatDate(deviceTime, "dd/MM/yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
                    sharedPreferenceData.SetDateTime(DetectorActivity.this, NtpServerTime, LocalTime);
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

    private void saveStudentAttendance(String studentId, String AttendanceType, String
            StudentNAme, String currentDate1, String Attendance) {
        DataSource dataSource = new DataSource(DetectorActivity.this);
        dataSource.open();
        A05_Student a05_student = dataSource.gettblA05studentdetailobj(studentId);
        A06_StudentDetails a06StudentDetails = dataSource.getStudentClassDetails(studentId);
        A01_Teacher trainee = dataSource.getA01_TeacherDetails();
       // M05_SchoolLocation m05_SchoolLocation = dataSource.getLatLong_SchoolLocation();
        GetDateTime();

        Date deviceTime = new Date();
        String LocalTime = _formatDate(deviceTime, "dd-MM-yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));
        Date date = new Date();
        String currentDate = _formatDate(date, "ddMMyyyy", TimeZone.getTimeZone("Asia/Kolkata"));

        SharedPreferences shared = getSharedPreferences("TrueTime", MODE_PRIVATE);
        String NtpServerTime = shared.getString("NtpServerTime", "");

        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Clock clock = null;
        String LocalAppId = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            clock = Clock.systemDefaultZone();
            long milliSeconds = clock.millis();
            String milliSecondsforLocalAppID = String.valueOf(milliSeconds);
            String persoinid = "";
            LocalAppId = (persoinid + milliSecondsforLocalAppID);
        }
/*        SharedPreferenceData sharedPreferenceData = new SharedPreferenceData();
        String gpsdate = sharedPreferenceData.getGPSDate(DetectorActivity.this);*/
        String versionName = info.versionName;
        A09_StudentAttendance a09_studentAttendance = new A09_StudentAttendance();
        a09_studentAttendance.setAtt_AttendanceDateTime(LocalTime);
        a09_studentAttendance.setAtt_StudentID(studentId);
        a09_studentAttendance.setAtt_AttendaceTypeID(Attendance);
/*        a09_studentAttendance.setAtt_AddedBy(trainee.getTeacher_ID() + "");
        a09_studentAttendance.setAtt_Lattitude(m05_SchoolLocation.getLocation_Lattitude());
        a09_studentAttendance.setAtt_Longitude(m05_SchoolLocation.getLocation_Longitude());
        a09_studentAttendance.setAtt_ClassID(a06StudentDetails.getSC_ClassID());
        a09_studentAttendance.setAtt_SchoolID(a06StudentDetails.getSC_SchoolID());
        a09_studentAttendance.setAtt_SectionID(a06StudentDetails.getSC_SectionID());
        a09_studentAttendance.setAtt_UniqueID(a05_student.getStudent_UniqueID());*/
        a09_studentAttendance.setIs_Sync("false");
        a09_studentAttendance.setAtt_AddedON(currentDate);
        a09_studentAttendance.setNtpServerTime(NtpServerTime);
        a09_studentAttendance.setAtt_PresentAbscent("P");
        a09_studentAttendance.setAtt_AppVersion(versionName);
        a09_studentAttendance.setAtt_LocalAppId(LocalAppId);
        a09_studentAttendance.setAtt_GPSTime("");

        int yesno = dataSource.getStudentAlreadypresent(studentId, currentDate1, Attendance);

        if (yesno == 0) {
            Beep();
            long isInserted = dataSource.insertA09_StudentAttendence(a09_studentAttendance);
            myvalue++;
        }

        if (StudentNAme != null && !StudentNAme.equalsIgnoreCase("")) {
            NameEntity nameEntity = new NameEntity();
            nameEntity.setName(StudentNAme);
            nameEntity.setValue((myvalue) + "");
            nameEntityList.add(0, nameEntity);
        }

        if (nameEntityList != null && nameEntityList.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_ForTotalAttendanceCount.setText(nameEntityList.size() + "");
         /*           AdapterforRunTimeTakenAttendance adapterforRunTimeTakenAttendance = new AdapterforRunTimeTakenAttendance(DetectorActivity.this, nameEntityList);
                    RV_forTakenAttendancePerson.setHasFixedSize(true);
                    RV_forTakenAttendancePerson.setLayoutManager(new LinearLayoutManager(DetectorActivity.this));
                    RV_forTakenAttendancePerson.setAdapter(adapterforRunTimeTakenAttendance);
                    adapterforRunTimeTakenAttendance.notifyDataSetChanged();*/
                }
            });
        }

        dataSource.close();

    }

    private void saveTeacherAttendance(String teacherid, String AttendanceType, String TeacherName, String currentDate1, String Attendance) {
        DataSource dataSource = new DataSource(DetectorActivity.this);
        dataSource.open();
        A01_Teacher a01Teacher = dataSource.getA01_TeacherDetails();
     //   M05_SchoolLocation m05_SchoolLocation = dataSource.getLatLong_SchoolLocation();
        GetDateTime();

        Date deviceTime = new Date();
        String LocalDateTime = _formatDate(deviceTime, "dd-MM-yyyy HH:mm:ss", TimeZone.getTimeZone("Asia/Kolkata"));

        String currentDate = _formatDate(deviceTime, "ddMMyyyy", TimeZone.getTimeZone("Asia/Kolkata"));

        SharedPreferences shared = getSharedPreferences("TrueTime", MODE_PRIVATE);
        String Ntp = shared.getString("NtpServerTime", "");

        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }

        String versionName = info.versionName;

        Clock clock = null;
        String LocalAppId = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            clock = Clock.systemDefaultZone();
            long milliSeconds = clock.millis();
            String milliSecondsforLocalAppID = String.valueOf(milliSeconds);
            String persoinid = String.valueOf(a01Teacher.getTeacher_ID() != null ? a01Teacher.getTeacher_ID() : "0");
            LocalAppId = (persoinid + milliSecondsforLocalAppID);
        }

        //SharedPreferenceData sharedPreferenceData = new SharedPreferenceData();
//        String gpsdate = sharedPreferenceData.getGPSDate(DetectorActivity.this);

        A09_TeacherAttendance a09_teacherAttendance = new A09_TeacherAttendance();
        a09_teacherAttendance.setAtt_AttendanceDateTime(LocalDateTime);
        a09_teacherAttendance.setAtt_TeacherID(teacherid);
        a09_teacherAttendance.setAtt_AddedBy(a01Teacher.getTeacher_ID());
        a09_teacherAttendance.setAtt_Lattitude("");
        a09_teacherAttendance.setAtt_Longitude("");
        a09_teacherAttendance.setIs_ssync("false");
        a09_teacherAttendance.setAtt_AttendaceTypeID(Attendance);
        a09_teacherAttendance.setAtt_PresentAbscent("P");
        a09_teacherAttendance.setAtt_SchoolID(a01Teacher.getTeacher_SchoolID());
        a09_teacherAttendance.setAtt_IsLate("");
        a09_teacherAttendance.setAtt_LateReasonID("");
        a09_teacherAttendance.setAtt_AddedON(currentDate);
        a09_teacherAttendance.setNTP_ServerTime(Ntp);
        a09_teacherAttendance.setAtt_GPSTime(""/*gpsdate*/);
        a09_teacherAttendance.setAtt_AppVersion(versionName);
        a09_teacherAttendance.setAtt_LocalAppId(LocalAppId);


        int j = dataSource.tbl_A09Teacherattendencedata_already_presnt(teacherid, currentDate1, Attendance);

        if (j == 0) {
            Beep();
            myvalue++;
            long isInserted = dataSource.insertA09_TeacherAttendence(a09_teacherAttendance);
        }

        if (TeacherName != null && !TeacherName.equalsIgnoreCase("")) {
            NameEntity nameEntity = new NameEntity();
            nameEntity.setName(TeacherName);
            nameEntity.setValue((myvalue) + "");
            nameEntityList.add(0, nameEntity);
        }

        if (nameEntityList != null && nameEntityList.size() > 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_ForTotalAttendanceCount.setText(nameEntityList.size() + "");
                  /*  AdapterforRunTimeTakenAttendance adapterforRunTimeTakenAttendance = new AdapterforRunTimeTakenAttendance(DetectorActivity.this, nameEntityList);
                    RV_forTakenAttendancePerson.setHasFixedSize(true);
                    RV_forTakenAttendancePerson.setLayoutManager(new LinearLayoutManager(DetectorActivity.this));
                    RV_forTakenAttendancePerson.setAdapter(adapterforRunTimeTakenAttendance);
                    adapterforRunTimeTakenAttendance.notifyDataSetChanged();*/
                }
            });
        }

        dataSource.close();

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
        if (TeacherAttendance != null && (TeacherAttendance.equalsIgnoreCase("TeacherAttendance") || TeacherAttendance.equalsIgnoreCase("Self"))) {
            Intent intent = new Intent(DetectorActivity.this, DashBoardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if (StudentAttendance != null && (StudentAttendance.equalsIgnoreCase("StudentAttendance") || StudentAttendance.equalsIgnoreCase("Self"))) {
            Intent intent = new Intent(DetectorActivity.this, DashBoardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        if (ProfileFor != null && ProfileFor.contains("Student")) {
            super.onBackPressed();
        }
        if (ProfileFor != null && ProfileFor.contains("Teacher")) {
            super.onBackPressed();
        }

    }

}