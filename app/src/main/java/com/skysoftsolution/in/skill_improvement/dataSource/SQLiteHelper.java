package com.skysoftsolution.in.skill_improvement.dataSource;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    Context context;
    public static final String DATABASE_NAME = "SkillImprove.db";
    public static final int DATABASE_VERSION = 5;

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /*todo for Login Work 1/12/2023*/
    public static final String tbl_A01_Teacher = "tbl_A01_Teacher";
    public static final String Teacher_ID = "Teacher_ID";
    public static final String Teacher_Name = "Teacher_Name";
    public static final String Teacher_CurrentSchoolUdise = "Teacher_CurrentSchoolUdise";
    public static final String Teacher_CurrentMDMSchoolCode = "Teacher_CurrentMDMSchoolCode";
    public static final String Teacher_IsActive = "Teacher_IsActive";
    public static final String Teacher_ehrmsCode = "Teacher_ehrmsCode";
    public static final String Teacher_MobileNo = "Teacher_MobileNo";
    public static final String Teacher_SchoolID = "Teacher_SchoolID";
    public static final String Teacher_IsHead = "Teacher_IsHead";
    public static final String User_Pin = "User_Pin";
    public static final String Is_Sync = "Is_Sync";
    public static final String School_DistrictID = "School_DistrictID";
    public static final String TD_DesignationID = "TD_DesignationID";
    public static final String School_Name = "School_Name";
    public static final String Teacher_Designation = "Teacher_Designation";
    public static final String School_DistrictName = "School_DistrictName";
    public static final String School_BlockName = "School_BlockName";
    public static final String Status = "Status";
    public static final String commonID = "commonID";

    public static final String create_tbl_A01_Teacher = "create table " + tbl_A01_Teacher + "(" + commonID + " integer primary key autoincrement," + Teacher_ID + " text," + Teacher_Name + " text," + Teacher_CurrentSchoolUdise + " text," + Teacher_CurrentMDMSchoolCode + " text," + Teacher_IsActive + " text," + Teacher_ehrmsCode + " text," + Teacher_MobileNo + " text," + Teacher_SchoolID + " text," + School_DistrictID + " text," + TD_DesignationID + " text," + Teacher_Designation + " text," + School_DistrictName + " text," + School_BlockName + " text," + School_Name + " text," + User_Pin + " text," + Status + " text," + Teacher_IsHead + " text )";


    /*-------------------------TeacherProfile-----------------------*/
    public static final String TP_ID = "TP_ID";
    public static final String tbl_A01_Teacher_profile = "tbl_A01_Teacher_profile";
    public static final String TP_TeacherID = "TP_TeacherID";
    public static final String TP_CreatedOn = "TP_CreatedOn";
    public static final String TP_VerifiedBy = "TP_VerifiedBy";
    public static final String TP_IsVerified = "TP_IsVerified";
    public static final String TP_IsActive = "TP_IsActive";
    public static final String TP_IsSync = "TP_IsSync";

    public static final String create_tbl_A01_Teacher_profile = "create table " + tbl_A01_Teacher_profile + "(" + commonID + " integer primary key autoincrement," + TP_IsActive + " text," + TP_ID + " text," + TP_TeacherID + " text," + TP_CreatedOn + " text," + TP_IsSync + " text," + TP_VerifiedBy + " text," + Teacher_SchoolID + " text," + School_DistrictID + " text," + TD_DesignationID + " text," + School_Name + " text," + TP_IsVerified + " text )";

    /*-------------------------TeacherProfilePhoto-----------------------*/
    public static final String tbl_A01_TeacherProfilePhoto = "tbl_A01_TeacherProfilePhoto";
    public static final String TPP_ID = "TPP_ID";
    public static final String TPP_ProfileID = "TPP_ProfileID";
    public static final String TPP_FileName = "TPP_FileName";
    public static final String TPP_FileServerPath = "TPP_FileServerPath";
    public static final String TPP_FileFullPath = "TPP_FileFullPath";
    public static final String TPP_IsLast = "TPP_IsLast";
    public static final String TPP_ClickedOn = "TPP_ClickedOn";
    public static final String TPP_Lattitude = "TPP_Lattitude";
    public static final String TPP_Longitude = "TPP_Longitude";
    public static final String TPP_Embeeding = "TPP_Embeeding";
    public static final String TPP_PersonID = "TPP_PersonID";
    public static final String TPP_TeacherID = "TPP_TeacherID";
    public static final String SPP_EmbeedingBytes = "SPP_EmbeedingBytes";
    public static final String TPP_EmbeedingBytes = "TPP_EmbeedingBytes";

    public static final String create_tbl_A01_TeacherProfilePhoto = "create table " + tbl_A01_TeacherProfilePhoto + "(" + commonID + " integer primary key autoincrement," + TPP_ID + " text," + TPP_ProfileID + " text," + TPP_FileName + " text," + TPP_FileServerPath + " text," + TPP_FileFullPath + " text," + TPP_IsLast + " text," + TPP_PersonID + " text," + TP_IsSync + " text," + TPP_ClickedOn + " text," + TPP_Lattitude + " text," + TPP_Longitude + " text," + TPP_TeacherID + " text," + TPP_EmbeedingBytes + " text," + TPP_Embeeding + " text )";

    /*-------------------------A05_StudentDetail-----------------------*/
    public static final String tbl_A05_Student = "tbl_A05_Student";
    public static final String Student_ID = "Student_ID";
    public static final String Student_SRNO = "Student_SRNO";
    public static final String Student_FatherName = "Student_FatherName";
    public static final String Student_MotherName = "Student_MotherName";
    public static final String Student_UniqueID = "Student_UniqueID";
    public static final String Student_Gender = "Student_Gender";
    public static final String Student_IsActive = "Student_IsActive";
    public static final String MorningAttendance = "MorningAttendance";
    public static final String EveningAttendance = "EveningAttendance";
    public static final String AddedOn = "AddedOn";
    public static final String Student_Name = "Student_Name";
    public static final String Common_Id = "Common_Id";

    public static final String create_tbl_A05_Student = "create table " + tbl_A05_Student + "(" + commonID + " integer primary key autoincrement," + Student_ID + " text," + Student_Name + " text," + Student_FatherName + " text," + Student_MotherName + " text," + Student_UniqueID + " text," + Student_Gender + " text," + Student_IsActive + " text," + MorningAttendance + " text," + EveningAttendance + " text," + AddedOn + " text," + Student_SRNO + " text )";

    /*-------------------------A06_StudentDetails-----------------------*/
    public static final String tbl_A06_Student = "tbl_A06_Student";
    public static final String SC_ID = "SC_ID";
    public static final String SC_SchoolID = "SC_SchoolID";
    public static final String SC_ClassID = "SC_ClassID";
    public static final String SC_SectionID = "SC_SectionID";
    public static final String SC_AddedOn = "SC_AddedOn";
    public static final String SC_IsActive = "SC_IsActive";
    public static final String SC_AcademicYearID = "SC_AcademicYearID";
    public static final String SC_StudentID = "SC_StudentID";

    public static final String create_tbl_A06_Student = "create table " + tbl_A06_Student + "(" + commonID + " integer primary key autoincrement," + SC_ID + " text," + SC_SchoolID + " text," + SC_ClassID + " text," + SC_SectionID + " text," + SC_AddedOn + " text," + SC_IsActive + " text," + SC_AcademicYearID + " text," + SC_StudentID + " text )";

    /*-------------------------A07_Studentprofile-----------------------*/
    public static final String tbl_A07_Studentprofile = "tbl_A07_Studentprofile";
    public static final String SP_ID = "SP_ID";
    public static final String SP_SchoolID = "SP_SchoolID";
    public static final String SP_StudentID = "SP_StudentID";
    public static final String SP_ClassID = "SP_ClassID";
    public static final String SP_SectionID = "SP_SectionID";
    public static final String SP_CreationDate = "SP_CreationDate";
    public static final String SP_Lattitude = "SP_Lattitude";
    public static final String SP_Longitude = "SP_Longitude";
    public static final String SP_IsLast = "SP_IsLast";
    public static final String SP_IsSync = "SP_IsSync";
    public static final String create_tbl_A07_Studentprofile = "create table " + tbl_A07_Studentprofile + "(" + commonID + " integer primary key autoincrement," + SP_ID + " text," + SP_SchoolID + " text," + SP_StudentID + " text," + SP_ClassID + " text," + SP_SectionID + " text," + SP_CreationDate + " text," + SP_Lattitude + " text," + SP_Longitude + " text," + SP_IsSync + " text," + SP_IsLast + " text )";

    /*-------------------------A08_Studentprofile-----------------------*/
    public static final String tbl_A08_StudentProfilePhoto = "tbl_A08_StudentProfilePhoto";
    public static final String SPP_ID = "SPP_ID";
    public static final String SPP_ProfileID = "SPP_ProfileID";
    public static final String SPP_FileName = "SPP_FileName";
    public static final String SPP_FilePath = "SPP_FilePath";
    public static final String SPP_FileURL = "SPP_FileURL";
    public static final String SPP_ServerFilePath = "SPP_ServerFilePath";
    public static final String SPP_Lattitude = "SPP_Lattitude";
    public static final String SPP_Longitude = "SPP_Longitude";
    public static final String SPP_IsActive = "SPP_IsActive";
    public static final String SPP_Embeeding = "SPP_Embeeding";
    public static final String SPP_StudentID = "SPP_StudentID";

    public static final String create_tbl_A08_StudentProfilePhoto = "create table " + tbl_A08_StudentProfilePhoto + "(" + commonID + " integer primary key autoincrement," + SPP_ID + " text," + SPP_ProfileID + " text," + SPP_FileName + " text," + SPP_FilePath + " text," + SPP_FileURL + " text," + SPP_ServerFilePath + " text," + SPP_Lattitude + " text," + SPP_Longitude + " text," + SPP_IsActive + " text," + SP_IsSync + " text," + SPP_StudentID + " text," + SPP_EmbeedingBytes + " text," + SPP_Embeeding + " text )";

    /*----------------------A09Studentdetail--------------------*/
    public static final String tbl_A09studentattendencedata = "tbl_A09studentattendencedata";
    public static final String Att_SchoolID = "Att_SchoolID";
    public static final String Att_StudentID = "Att_StudentID";
    public static final String Att_UniqueID = "Att_UniqueID";
    public static final String Att_AttendaceTypeID = "Att_AttendaceTypeID";
    public static final String Att_PresentAbscent = "Att_PresentAbscent";
    public static final String Att_AttendanceDateTime = "Att_AttendanceDateTime";
    public static final String Att_AddedBy = "Att_AddedBy";
    public static final String Att_ClassID = "Att_ClassID";
    public static final String Att_SectionID = "Att_SectionID";
    public static final String Att_Lattitude = "Att_Lattitude";
    public static final String Att_AbsentReasonID = "Att_AbsentReasonID";
    public static final String Att_Longitude = "Att_Longitude";

    public static final String NtpServerTime = "NtpServerTime";
    public static final String Att_AppVersion = "Att_AppVersion";
    public static final String Att_LocalAppId = "Att_LocalAppId";
    public static final String Att_GpsTime = "Att_GpsTime";

    public static final String create_tbl_A09studentattendencedata = "create table " + tbl_A09studentattendencedata + "(" + commonID + " integer primary key autoincrement," + Att_SchoolID + " text," + Att_StudentID + " text," + Att_UniqueID + " text," + Att_AttendaceTypeID + " text," + Att_PresentAbscent + " text," + Att_AttendanceDateTime + " text," + Att_AddedBy + " text," + Att_ClassID + " text," + Att_SectionID + " text," + Att_Lattitude + " text," + NtpServerTime + " text," + Att_AppVersion + " text," + Att_LocalAppId + " text," + Att_GpsTime + " text," + AddedOn + " text," + Att_AbsentReasonID + " text," + Is_Sync + " text," + Att_Longitude + " text )";

    /*----------------------A09teacherdetail-------------------------*/
    public static final String tbl_A09Teacherattendencedata = "tbl_A09Teacherattendencedata";
    public static final String Att_ID = "Att_ID";
    public static final String Att_TeacherID = "Att_TeacherID";
    public static final String Att_IsLate = "Att_IsLate";
    public static final String Att_LateReasonID = "Att_LateReasonID";
    public static final String Att_IsActive = "Att_IsActive";
    public static final String Is_ssync = "Is_ssync";
    public static final String NTP_ServerTime = "NTP_ServerTime";

    public static final String create_tbl_A09Teacherattendencedata = "create table " + tbl_A09Teacherattendencedata + "(" + commonID + " integer primary key autoincrement," + Att_ID + " text," + Att_SchoolID + " text," + Att_TeacherID + " text," + Att_AttendaceTypeID + " text," + Att_PresentAbscent + " text," + Att_AttendanceDateTime + " text," + Att_AddedBy + " text," + Att_ClassID + " text," + Att_SectionID + " text," + Att_Lattitude + " text," + Att_Longitude + " text," + Att_IsLate + " text," + AddedOn + " text," + NTP_ServerTime + " text," + Att_LateReasonID + " text," + Att_AppVersion + " text," + Att_LocalAppId + " text," + Att_GpsTime + " text," + Att_AbsentReasonID + " text," + Att_IsActive + " text," + Is_ssync + " text )";

    /*----------------------Latereason-------------------------*/
    public static final String tbl_lateTeacherattendencedata = "tbl_lateTeacherattendencedata";
    public static final String LR_ID = "LR_ID";
    public static final String LR_ReasonText = "LR_ReasonText";
    public static final String LR_IsActive = "LR_IsActive";
    public static final String create_tbl_lateTeacherattendencedata = "create table " + tbl_lateTeacherattendencedata + "(" + commonID + " integer primary key autoincrement," + LR_ID + " text," + LR_ReasonText + " text," + LR_IsActive + " text )";

    /*---------------------Master class-------------------*/
    public static final String tbl_MasterClassdata = "tbl_MasterClassdata";
    public static final String Class_ID = "Class_ID";
    public static final String Class_IsActive = "Class_IsActive";
    public static final String Class_Name = "Class_Name";
    public static final String create_tbl_MasterClassdata = "create table " + tbl_MasterClassdata + "(" + commonID + " integer primary key autoincrement," + Class_ID + " text," + Class_Name + " text," + Class_IsActive + " text )";

    /*----------------Master Section---------------------*/
    public static final String tbl_MasterSectiondata = "tbl_MasterSectiondata";
    public static final String Section_ID = "Section_ID";
    public static final String Section_Name = "Section_Name";
    public static final String Section_IsActive = "Section_IsActive";
    public static final String create_tbl_MasterSectiondata = "create table " + tbl_MasterSectiondata + "(" + commonID + " integer primary key autoincrement," + Section_ID + " text," + Section_Name + " text," + Section_IsActive + " text )";


    /////////////////////////////////////////////Todo LocationWork 04122023
    public static final String tbl_M05_SchoolLocation = "tbl_M05_SchoolLocation";
    public static final String Location_IsSync = "Location_IsSync";
    public static final String Location_ID = "Location_ID";
    public static final String Location_SchoolID = "Location_SchoolID";
    public static final String Location_Lattitude = "Location_Lattitude";
    public static final String Location_Longitude = "Location_Longitude";
    public static final String Location_IsActive = "Location_IsActive";
    public static final String Location_AddedOn = "Location_AddedOn";
    public static final String Location_AddedBy = "Location_AddedBy";
    public static final String Location_IsVerified = "Location_IsVerified";
    public static final String Location_IsVerifiedOn = "Location_IsVerifiedOn";
    public static final String Location_VerifiedBy = "Location_VerifiedBy";

    public static final String create_tbl_M05_SchoolLocation = "create table " + tbl_M05_SchoolLocation + "(" + commonID + " integer primary key autoincrement," + Location_ID + " text," + Location_SchoolID + " text," + Location_Lattitude + " text," + Location_Longitude + " text," + Location_IsActive + " text," + Location_AddedOn + " text," + Location_AddedBy + " text," + Location_IsVerified + " text," + Location_IsVerifiedOn + " text," + Location_VerifiedBy + " text," + Location_IsSync + " text )";

    public static final String tbl_M05_SchoolLocationPhoto = "tbl_M05_SchoolLocationPhoto";
    public static final String LocationPhoto_ID = "LocationPhoto_ID";
    public static final String LocationPhoto_LocationID = "LocationPhoto_LocationID";
    public static final String LocationPhoto_FileName = "LocationPhoto_FileName";
    public static final String LocationPhoto_FileURL = "LocationPhoto_FileURL";
    public static final String LocationPhoto_PhysicalPath = "LocationPhoto_PhysicalPath";
    public static final String LocationPhoto_Lattitude = "LocationPhoto_Lattitude";
    public static final String LocationPhoto_Longitude = "LocationPhoto_Longitude";
    public static final String LocationPhoto_IsActive = "LocationPhoto_IsActive";
    public static final String LocationPhoto_ClickedAt = "LocationPhoto_ClickedAt";
    public static final String LocationPhoto_IsSync = "LocationPhoto_IsSync";

    public static final String create_tbl_M05_SchoolLocationPhoto = "create table " + tbl_M05_SchoolLocationPhoto + "(" + commonID + " integer primary key autoincrement," + Common_Id + " text," + LocationPhoto_ID + " text," + LocationPhoto_LocationID + " text," + LocationPhoto_FileName + " text," + LocationPhoto_FileURL + " text," + LocationPhoto_PhysicalPath + " text," + LocationPhoto_Lattitude + " text," + LocationPhoto_Longitude + " text," + LocationPhoto_IsActive + " text," + LocationPhoto_ClickedAt + " text," + LocationPhoto_IsSync + " text )";

    public static final String tbl_A01_TeacherList = "tbl_A01_TeacherList";
    public static final String Designation_Name = "Designation_Name";

    public static final String create_tbl_A01_TeacherList = "create table " + tbl_A01_TeacherList + "(" + commonID + " integer primary key autoincrement," + Teacher_ID + " text," + Teacher_Name + " text," + Teacher_CurrentSchoolUdise + " text," + Teacher_CurrentMDMSchoolCode + " text," + Teacher_IsActive + " text," + Teacher_ehrmsCode + " text," + Teacher_MobileNo + " text," + Teacher_SchoolID + " text," + School_DistrictID + " text," + TD_DesignationID + " text," + Teacher_Designation + " text," + School_DistrictName + " text," + School_BlockName + " text," + School_Name + " text," + User_Pin + " text," + Designation_Name + " text," + Teacher_IsHead + " text )";

    /*todo new Image Details*/
    public static final String tbl_A01_CapturedFaceDetailsTeacherProfilePhoto = "tbl_A01_CapturedFaceDetailsTeacherProfilePhoto";
    public static final String tbl_A01_CapturedFaceDetailsStudentProfilePhoto = "tbl_A01_CapturedFaceDetailsStudentProfilePhoto";
    public static final String tbl_A01_CapturedFaceDetailsTeacherAttendance = "tbl_A01_CapturedFaceDetailsTeacherAttendance";
    public static final String tbl_A01_CapturedFaceDetailsStudentAttendance = "tbl_A01_CapturedFaceDetailsStudentAttendance";
    public static final String CID_ID = "CID_ID";
    public static final String CID_TPPID = "CID_TPPID";
    public static final String CID_FaceLiveness = "CID_FaceLiveness";
    public static final String CID_FaceQuality = "CID_FaceQuality";
    public static final String CID_FaceLuminance = "CID_FaceLuminance";
    public static final String CID_PredictedAge = "CID_PredictedAge";
    public static final String CID_PredictedGender = "CID_PredictedGender";
    public static final String CID_EyeOpenClose = "CID_EyeOpenClose";
    public static final String CID_MouthOpenClose = "CID_MouthOpenClose";
    public static final String CID_YawThreshold = "CID_YawThreshold";
    public static final String CID_PitchThreshold = "CID_PitchThreshold";
    public static final String CID_RollThreshold = "CID_RollThreshold";
    public static final String CID_OcculsionThreshold = "CID_OcculsionThreshold";
    public static final String CID_PersonId = "CID_PersonId";

    public static final String Att_AddedON = "Att_AddedON";

    public static final String create_tbl_A01_CapturedFaceDetailsTeacherProfilePhoto = "create table " + tbl_A01_CapturedFaceDetailsTeacherProfilePhoto + "(" + commonID + " integer primary key autoincrement," + CID_ID + " text," + CID_TPPID + " text," + CID_FaceLiveness + " text," + CID_PersonId + " text," + CID_FaceLuminance + " text," + CID_FaceQuality + " text," + CID_PredictedAge + " text," + CID_PredictedGender + " text," + CID_EyeOpenClose + " text," + CID_MouthOpenClose + " text," + CID_YawThreshold + " text," + CID_PitchThreshold + " text," + CID_RollThreshold + " text," + CID_OcculsionThreshold + " text )";


    public static final String create_tbl_A01_CapturedFaceDetailsStudentProfilePhoto = "create table " + tbl_A01_CapturedFaceDetailsStudentProfilePhoto + "(" + commonID + " integer primary key autoincrement," + CID_ID + " text," + CID_TPPID + " text," + CID_FaceLiveness + " text," + CID_PersonId + " text," + CID_FaceQuality + " text," + CID_FaceLuminance + " text," + CID_PredictedAge + " text," + CID_PredictedGender + " text," + CID_EyeOpenClose + " text," + CID_MouthOpenClose + " text," + CID_YawThreshold + " text," + CID_PitchThreshold + " text," + CID_RollThreshold + " text," + CID_OcculsionThreshold + " text )";
    public static final String create_tbl_A01_CapturedFaceDetailsTeacherAttendance = "create table " + tbl_A01_CapturedFaceDetailsTeacherAttendance + "(" + commonID + " integer primary key autoincrement," + CID_ID + " text," + CID_TPPID + " text," + CID_FaceLiveness + " text," + CID_PersonId + " text," + CID_FaceQuality + " text," + Att_AddedON + " text," + Att_AttendaceTypeID + " text," + CID_FaceLuminance + " text," + CID_PredictedAge + " text," + CID_PredictedGender + " text," + CID_EyeOpenClose + " text," + CID_MouthOpenClose + " text," + CID_YawThreshold + " text," + CID_PitchThreshold + " text," + CID_RollThreshold + " text," + CID_OcculsionThreshold + " text )";
    public static final String create_tbl_A01_CapturedFaceDetailsStudentAttendance = "create table " + tbl_A01_CapturedFaceDetailsStudentAttendance + "(" + commonID + " integer primary key autoincrement," + CID_ID + " text," + CID_TPPID + " text," + CID_FaceLiveness + " text," + CID_PersonId + " text," + CID_FaceQuality + " text," + CID_FaceLuminance + " text," + CID_PredictedAge + " text," + Att_AddedON + " text," + Att_AttendaceTypeID + " text," + CID_PredictedGender + " text," + CID_EyeOpenClose + " text," + CID_MouthOpenClose + " text," + CID_YawThreshold + " text," + CID_PitchThreshold + " text," + CID_RollThreshold + " text," + CID_OcculsionThreshold + " text )";

    /*end*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*todo for Login Work 1/12/2023*/
        db.execSQL(create_tbl_A01_Teacher);
        db.execSQL(create_tbl_A01_Teacher_profile);
        db.execSQL(create_tbl_A01_TeacherProfilePhoto);
        db.execSQL(create_tbl_A05_Student);
        db.execSQL(create_tbl_A06_Student);
        db.execSQL(create_tbl_A07_Studentprofile);
        db.execSQL(create_tbl_A08_StudentProfilePhoto);
        db.execSQL(create_tbl_A09studentattendencedata);
        db.execSQL(create_tbl_A09Teacherattendencedata);
        db.execSQL(create_tbl_MasterClassdata);
        db.execSQL(create_tbl_MasterSectiondata);
        db.execSQL(create_tbl_lateTeacherattendencedata);
        db.execSQL(create_tbl_M05_SchoolLocation);
        db.execSQL(create_tbl_M05_SchoolLocationPhoto);
        db.execSQL(create_tbl_A01_TeacherList);
        db.execSQL(create_tbl_A01_CapturedFaceDetailsTeacherProfilePhoto);
        db.execSQL(create_tbl_A01_CapturedFaceDetailsStudentProfilePhoto);
        db.execSQL(create_tbl_A01_CapturedFaceDetailsTeacherAttendance);
        db.execSQL(create_tbl_A01_CapturedFaceDetailsStudentAttendance);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            /*todo for Login Work 1/12/2023*/
            db.execSQL(create_tbl_A01_Teacher);
            db.execSQL(create_tbl_A01_Teacher_profile);
            db.execSQL(create_tbl_A01_TeacherProfilePhoto);
            db.execSQL(create_tbl_A05_Student);
            db.execSQL(create_tbl_A06_Student);
            db.execSQL(create_tbl_A07_Studentprofile);
            db.execSQL(create_tbl_A08_StudentProfilePhoto);
            db.execSQL(create_tbl_A09studentattendencedata);
            db.execSQL(create_tbl_A09Teacherattendencedata);
            db.execSQL(create_tbl_MasterClassdata);
            db.execSQL(create_tbl_MasterSectiondata);
            db.execSQL(create_tbl_lateTeacherattendencedata);
            db.execSQL(create_tbl_M05_SchoolLocation);
            db.execSQL(create_tbl_M05_SchoolLocationPhoto);
            db.execSQL(create_tbl_A01_TeacherList);
            db.execSQL(create_tbl_A01_CapturedFaceDetailsTeacherProfilePhoto);
            db.execSQL(create_tbl_A01_CapturedFaceDetailsStudentProfilePhoto);
            db.execSQL(create_tbl_A01_CapturedFaceDetailsTeacherAttendance);
            db.execSQL(create_tbl_A01_CapturedFaceDetailsStudentAttendance);


        } catch (Exception e) {

        }

    }

}