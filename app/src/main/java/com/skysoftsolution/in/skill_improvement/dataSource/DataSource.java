package com.skysoftsolution.in.skill_improvement.dataSource;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.skysoftsolution.in.skill_improvement.entity.A01_Teacher;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherPersonList;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherProfile;
import com.skysoftsolution.in.skill_improvement.entity.A01_TeacherProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.A05_Student;
import com.skysoftsolution.in.skill_improvement.entity.A06_StudentDetails;
import com.skysoftsolution.in.skill_improvement.entity.A07_StudentProfile;
import com.skysoftsolution.in.skill_improvement.entity.A08_StudentProfilePhoto;
import com.skysoftsolution.in.skill_improvement.entity.A09_StudentAttendance;
import com.skysoftsolution.in.skill_improvement.entity.A09_TeacherAttendance;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private SQLiteDatabase database;
    private final SQLiteHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new SQLiteHelper(context, SQLiteHelper.DATABASE_NAME, null, SQLiteHelper.DATABASE_VERSION);
    }

    public static String base64Encode(String value) {

        byte[] encodedbytes = Base64.encode(value.getBytes(), Base64.DEFAULT);
        return new String(encodedbytes, StandardCharsets.UTF_8);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /*todo for Login Work 1/12/2023*/
    public long insertA01_TeacherDetails(A01_Teacher a01_teacher) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Teacher_ID, a01_teacher.getTeacher_ID());
        values.put(SQLiteHelper.Teacher_Name, a01_teacher.getTeacher_Name());
        values.put(SQLiteHelper.Teacher_CurrentSchoolUdise, a01_teacher.getTeacher_CurrentSchoolUdise());
        values.put(SQLiteHelper.Teacher_CurrentMDMSchoolCode, a01_teacher.getTeacher_CurrentMDMSchoolCode());
        values.put(SQLiteHelper.Teacher_IsActive, a01_teacher.getTeacher_IsActive());
        values.put(SQLiteHelper.Teacher_ehrmsCode, a01_teacher.getTeacher_ehrmsCode());
        values.put(SQLiteHelper.Teacher_MobileNo, a01_teacher.getTeacher_MobileNo());
        values.put(SQLiteHelper.Teacher_SchoolID, a01_teacher.getTeacher_SchoolID());
        values.put(SQLiteHelper.Teacher_IsHead, a01_teacher.getTeacher_IsHead());
        values.put(SQLiteHelper.User_Pin, a01_teacher.getUser_Pin());
        values.put(SQLiteHelper.School_DistrictID, a01_teacher.getSchool_DistrictID());
        values.put(SQLiteHelper.TD_DesignationID, a01_teacher.getTD_DesignationID());
        values.put(SQLiteHelper.School_Name, a01_teacher.getSchool_Name());
        values.put(SQLiteHelper.Teacher_Designation, a01_teacher.getTeacher_Designation());
        values.put(SQLiteHelper.School_DistrictName, a01_teacher.getSchool_DistrictName());
        values.put(SQLiteHelper.School_BlockName, a01_teacher.getSchool_BlockName());
        values.put(SQLiteHelper.Status, a01_teacher.getStatus());
        isInserted = database.insert(SQLiteHelper.tbl_A01_Teacher, null, values);
        return isInserted;
    }

    @SuppressLint("Range")
    public A01_Teacher getA01_TeacherDetails() {
        A01_Teacher a01_teacher = new A01_Teacher();
        String sql = "select  *  from tbl_A01_Teacher";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            a01_teacher.setTeacher_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ID)));
            a01_teacher.setTeacher_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name)));
            a01_teacher.setTeacher_CurrentSchoolUdise(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentSchoolUdise)));
            a01_teacher.setTeacher_CurrentMDMSchoolCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentMDMSchoolCode)));
            a01_teacher.setTeacher_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsActive)));
            a01_teacher.setTeacher_ehrmsCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ehrmsCode)));
            a01_teacher.setTeacher_MobileNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_MobileNo)));
            a01_teacher.setTeacher_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_SchoolID)));
            a01_teacher.setUser_Pin(cursor.getString(cursor.getColumnIndex(SQLiteHelper.User_Pin)));
            a01_teacher.setTeacher_IsHead(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsHead)));
            a01_teacher.setSchool_DistrictID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.School_DistrictID)));
            a01_teacher.setTD_DesignationID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TD_DesignationID)));
            a01_teacher.setSchool_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.School_Name)));
            a01_teacher.setTeacher_Designation(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Designation)));
            a01_teacher.setSchool_DistrictName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.School_DistrictName)));
            a01_teacher.setSchool_BlockName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.School_BlockName)));
            a01_teacher.setStatus(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Status)));
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacher;
    }

    @SuppressLint("Range")
    public List<A01_TeacherPersonList> getA01_TeacherDetailsList(String Forwhat) {
        List<A01_TeacherPersonList> a01_teacherArrayList = new ArrayList<>();
        String sql = "";
        if (Forwhat.equalsIgnoreCase("ProfileCapture")) {
            sql = "select  TA01T.Designation_Name, TA01T.Teacher_ID, TA01T.Teacher_Name, TA01T.Teacher_CurrentSchoolUdise, TA01T.Teacher_CurrentMDMSchoolCode, TA01T.Teacher_IsActive, TA01T.Teacher_ehrmsCode, TA01T.Teacher_MobileNo, TA01T.Teacher_SchoolID, TA01T.Teacher_IsHead, TA01T.User_Pin, TA01T_P.TP_IsVerified,TA01T_P.TP_IsSync from tbl_A01_TeacherList as TA01T left join tbl_A01_Teacher_profile TA01T_P on TA01T.Teacher_ID = TA01T_P.TP_TeacherID order by TA01T.Teacher_Name";
        } else if (Forwhat.equalsIgnoreCase("ProfileReport")) {
            sql = "select  TA01T.Designation_Name, TA01T.Teacher_ID, TA01T.Teacher_Name, TA01T.Teacher_CurrentSchoolUdise, TA01T.Teacher_CurrentMDMSchoolCode, TA01T.Teacher_IsActive, TA01T.Teacher_ehrmsCode, TA01T.Teacher_MobileNo, TA01T.Teacher_SchoolID, TA01T.Teacher_IsHead, TA01T.User_Pin, TA01T_P.TP_IsVerified,TA01T_P.TP_IsSync from tbl_A01_TeacherList as TA01T left join tbl_A01_Teacher_profile TA01T_P on TA01T.Teacher_ID = TA01T_P.TP_TeacherID where TA01T_P.TP_TeacherID not null order by TA01T.Teacher_Name";
        } else {
            sql = "select  TA01T.Designation_Name, TA01T.Teacher_ID, TA01T.Teacher_Name, TA01T.Teacher_CurrentSchoolUdise, TA01T.Teacher_CurrentMDMSchoolCode, TA01T.Teacher_IsActive, TA01T.Teacher_ehrmsCode, TA01T.Teacher_MobileNo, TA01T.Teacher_SchoolID, TA01T.Teacher_IsHead, TA01T.User_Pin, TA01T_P.TP_IsVerified,TA01T_P.TP_IsSync from tbl_A01_TeacherList as TA01T left join tbl_A01_Teacher_profile TA01T_P on TA01T.Teacher_ID = TA01T_P.TP_TeacherID order by TA01T.Teacher_Name";
        }
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherPersonList a01_teacher = new A01_TeacherPersonList();
            a01_teacher.setTeacher_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ID)));
            a01_teacher.setTeacher_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name)));
            a01_teacher.setTeacher_CurrentSchoolUdise(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentSchoolUdise)));
            a01_teacher.setTeacher_CurrentMDMSchoolCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentMDMSchoolCode)));
            a01_teacher.setTeacher_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsActive)));
            a01_teacher.setTeacher_ehrmsCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ehrmsCode)));
            a01_teacher.setTeacher_MobileNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_MobileNo)));
            a01_teacher.setTeacher_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_SchoolID)));
            a01_teacher.setUser_Pin(cursor.getString(cursor.getColumnIndex(SQLiteHelper.User_Pin)));
            a01_teacher.setTeacher_IsHead(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsHead)));
            a01_teacher.setTP_IsVerified(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsVerified)));
            a01_teacher.setTP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsSync)));
            a01_teacher.setDesignation_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Designation_Name)));
            a01_teacherArrayList.add(a01_teacher);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherArrayList;
    }

    @SuppressLint("Range")
    public List<A01_TeacherPersonList> getA01_TeacherDetailsListVerified() {
        List<A01_TeacherPersonList> a01_teacherArrayList = new ArrayList<>();
        String sql = "select TA01T.Designation_Name, TA01T.Teacher_ID, TA01T.Teacher_Name, TA01T.Teacher_CurrentSchoolUdise, TA01T.Teacher_CurrentMDMSchoolCode, TA01T.Teacher_IsActive, TA01T.Teacher_ehrmsCode, TA01T.Teacher_MobileNo, TA01T.Teacher_SchoolID, TA01T.Teacher_IsHead, TA01T.User_Pin, TA01T_P.TP_IsVerified,TA01T_P.TP_IsSync from tbl_A01_TeacherList as TA01T left join tbl_A01_Teacher_profile TA01T_P on TA01T.Teacher_ID = TA01T_P.TP_TeacherID where TA01T_P.TP_TeacherID not null order by TA01T.Teacher_Name";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherPersonList a01_teacher = new A01_TeacherPersonList();
            a01_teacher.setTeacher_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ID)));
            a01_teacher.setTeacher_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name)));
            a01_teacher.setTeacher_CurrentSchoolUdise(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentSchoolUdise)));
            a01_teacher.setTeacher_CurrentMDMSchoolCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_CurrentMDMSchoolCode)));
            a01_teacher.setTeacher_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsActive)));
            a01_teacher.setTeacher_ehrmsCode(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_ehrmsCode)));
            a01_teacher.setTeacher_MobileNo(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_MobileNo)));
            a01_teacher.setTeacher_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_SchoolID)));
            a01_teacher.setUser_Pin(cursor.getString(cursor.getColumnIndex(SQLiteHelper.User_Pin)));
            a01_teacher.setTeacher_IsHead(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_IsHead)));
            a01_teacher.setTP_IsVerified(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsVerified)));
            a01_teacher.setTP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsSync)));
            a01_teacher.setDesignation_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Designation_Name)));
            a01_teacherArrayList.add(a01_teacher);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherArrayList;
    }


    public long delete_tbl_A01_TeacherDetails() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_Teacher, null, null);
        return i;
    }

    /*-----------------------------------------------teacherProfile---------------------*/
    public long insertA01_TeacherProfile(A01_TeacherProfile a01_teacherProfile) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TP_ID, a01_teacherProfile.getTP_ID());
        values.put(SQLiteHelper.TP_TeacherID, a01_teacherProfile.getTP_TeacherID());
        values.put(SQLiteHelper.TP_CreatedOn, a01_teacherProfile.getTP_CreatedOn());
        values.put(SQLiteHelper.TP_VerifiedBy, a01_teacherProfile.getTP_VerifiedBy());
        values.put(SQLiteHelper.TP_IsActive, a01_teacherProfile.getTP_IsActive());
        values.put(SQLiteHelper.TP_IsVerified, a01_teacherProfile.getTP_IsVerified());
        values.put(SQLiteHelper.TP_IsSync, a01_teacherProfile.getTP_IsSync());
        values.put(SQLiteHelper.Teacher_SchoolID, a01_teacherProfile.getTeacher_SchoolID());
        values.put(SQLiteHelper.School_DistrictID, a01_teacherProfile.getSchool_DistrictID());
        values.put(SQLiteHelper.TD_DesignationID, a01_teacherProfile.getTD_DesignationID());
        values.put(SQLiteHelper.School_Name, a01_teacherProfile.getSchool_Name());
        isInserted = database.insert(SQLiteHelper.tbl_A01_Teacher_profile, null, values);
        return isInserted;
    }

    public long delete_tbl_A01_Teacherprofile() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_Teacher_profile, null, null);
        return i;
    }

    /*---------------------Ao1TeacherProfilePhoto---------------------*/
    public long insertA01_TeacherProfilePhoto(A01_TeacherProfilePhoto a01_teacherProfilePhoto) {
        long isInserted;
        ContentValues values = new ContentValues();
        String JsonEmbeedings = String.valueOf(a01_teacherProfilePhoto.getTPP_Embeeding());
        values.put(SQLiteHelper.TPP_ID, a01_teacherProfilePhoto.getTPP_ID());
        values.put(SQLiteHelper.TPP_ProfileID, a01_teacherProfilePhoto.getTPP_ProfileID());
        values.put(SQLiteHelper.TPP_FileName, a01_teacherProfilePhoto.getTPP_FileName());
        values.put(SQLiteHelper.TPP_FileServerPath, a01_teacherProfilePhoto.getTPP_FileServerPath());
        values.put(SQLiteHelper.TPP_FileFullPath, a01_teacherProfilePhoto.getTPP_FileFullPath());
        values.put(SQLiteHelper.TPP_IsLast, a01_teacherProfilePhoto.getTPP_IsLast());
        values.put(SQLiteHelper.TPP_ClickedOn, a01_teacherProfilePhoto.getTPP_ClickedOn());
        values.put(SQLiteHelper.TPP_Lattitude, a01_teacherProfilePhoto.getTPP_Lattitude());
        values.put(SQLiteHelper.TPP_Longitude, a01_teacherProfilePhoto.getTPP_Longitude());
        values.put(SQLiteHelper.TPP_PersonID, a01_teacherProfilePhoto.getTPP_PersonID());
        values.put(SQLiteHelper.TP_IsSync, a01_teacherProfilePhoto.getProfile_IsSync());
        values.put(SQLiteHelper.TPP_Embeeding, JsonEmbeedings);
        values.put(SQLiteHelper.TPP_EmbeedingBytes, a01_teacherProfilePhoto.getTPP_EmbeedingBytes());

        values.put(SQLiteHelper.TPP_TeacherID, a01_teacherProfilePhoto.getTPP_TeacherID());
        isInserted = database.insert(SQLiteHelper.tbl_A01_TeacherProfilePhoto, null, values);
        return isInserted;
    }

    public long delete_tbl_A01_TeacherprofilePhoto() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_TeacherProfilePhoto, null, null);
        return i;
    }

    /*-------------------------A05StudentDetail-------------------*/
    public long insertA05_Student(A05_Student a05_student) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Student_ID, a05_student.getStudent_ID());
        values.put(SQLiteHelper.Student_Name, a05_student.getStudent_Name().toString());
        values.put(SQLiteHelper.Student_FatherName, a05_student.getStudent_FatherName());
        values.put(SQLiteHelper.Student_MotherName, a05_student.getStudent_MotherName());
        values.put(SQLiteHelper.Student_UniqueID, a05_student.getStudent_UniqueID());
        values.put(SQLiteHelper.Student_Gender, a05_student.getStudent_Gender());
        values.put(SQLiteHelper.Student_IsActive, a05_student.getStudent_IsActive());
        values.put(SQLiteHelper.Student_SRNO, a05_student.getStudent_SRNO());
        values.put(SQLiteHelper.MorningAttendance, a05_student.getMorningAttendance());
        values.put(SQLiteHelper.EveningAttendance, a05_student.getEveningAttendance());
        values.put(SQLiteHelper.AddedOn, a05_student.getAddedOn());

        isInserted = database.insert(SQLiteHelper.tbl_A05_Student, null, values);
        return isInserted;
    }

    @SuppressLint("Range")
    public A05_Student gettblA05studentdetailobj(String StudentID) {
        A05_Student a05_students = new A05_Student();
        String sql = "select  *  from  tbl_A05_Student where Student_ID='" + StudentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A05_Student a05_student = new A05_Student();
            a05_student.setStudent_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_ID)));
            a05_student.setStudent_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Name)));
            a05_student.setStudent_FatherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_FatherName)));
            a05_student.setStudent_MotherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_MotherName)));
            a05_student.setStudent_UniqueID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_UniqueID)));
            a05_student.setStudent_Gender(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Gender)));
            a05_student.setStudent_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_IsActive)));
            a05_student.setStudent_SRNO(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_SRNO)));
            cursor.moveToNext();
        }
        return a05_students;
    }

    @SuppressLint("Range")
    public ArrayList<A05_Student> gettblA05studentdetailList(String sectionID, String classid) {
        ArrayList<A05_Student> a05_studentsList = new ArrayList<>();
        String sql = "";
        if (!sectionID.equalsIgnoreCase("") && !classid.equalsIgnoreCase("")) {
            sql = "select  A06S.SC_ClassID,A06S.SC_SectionID,A05S.Student_ID, A05S.Student_Name, A05S.Student_FatherName, A05S. Student_MotherName, A05S.Student_UniqueID, A05S. Student_Gender, A05S. Student_IsActive, A05S.Student_SRNO  from tbl_A05_Student   A05S left  join tbl_A06_Student   A06S on A05S.Student_ID =  A06S.SC_StudentID  where A05S.Student_ID not null and  A06S.SC_ClassID='" + classid + "' and A06S.SC_SectionID='" + sectionID + "' and A05S.Student_IsActive in ('True','true')  order by A05S.Student_Name";
        }
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A05_Student a05_student = new A05_Student();
            a05_student.setStudent_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_ID)));
            a05_student.setStudent_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Name)));
            a05_student.setStudent_FatherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_FatherName)));
            a05_student.setStudent_MotherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_MotherName)));
            a05_student.setStudent_UniqueID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_UniqueID)));
            a05_student.setStudent_Gender(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Gender)));
            a05_student.setStudent_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_IsActive)));
            a05_student.setStudent_SRNO(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_SRNO)));
            a05_student.setSC_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_ClassID)));
            a05_student.setSC_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_SectionID)));
            a05_studentsList.add(a05_student);
            cursor.moveToNext();
        }
        cursor.close();
        return a05_studentsList;
    }

    @SuppressLint("Range")
    public ArrayList<A05_Student> gettblA05studentdetailList_Report(String sectionID, String classid) {
        ArrayList<A05_Student> a05_studentsList = new ArrayList<>();
        String sql = "";
        if (!sectionID.equalsIgnoreCase("") && !classid.equalsIgnoreCase("")) {
            sql = "select A06S.SC_ClassID,A06S.SC_SectionID,A05S.Student_ID, A05S.Student_Name, A05S.Student_FatherName, A05S. Student_MotherName, A05S.Student_UniqueID,A05S. Student_Gender, A05S. Student_IsActive, A05S.Student_SRNO from tbl_A05_Student as A05S   inner join tbl_A06_Student as A06S  on A05S.Student_ID=A06S.SC_StudentID left join tbl_A07_Studentprofile as A07SP on   A05S.Student_ID= A07SP.SP_StudentID  where A06S.SC_ClassID='" + classid + "' and A06S.SC_SectionID='" + sectionID + "' and A05S.Student_IsActive in ('True','true')  AND A07SP.SP_StudentID IS NOT NULL order by A05S.Student_Name ";
        } else {
            sql = "select  A06S.SC_ClassID,A06S.SC_SectionID,A05S.Student_ID, A05S.Student_Name, A05S.Student_FatherName, A05S. Student_MotherName, A05S.Student_UniqueID, A05S. Student_Gender, A05S. Student_IsActive, A05S.Student_SRNO  from tbl_A05_Student   A05S left  join tbl_A06_Student   A06S on A05S.Student_ID =  A06S.SC_StudentID  where  A05S.Student_IsActive in ('True','true') and A05S.Student_ID not null  order by A05S.Student_Name";
        }
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A05_Student a05_student = new A05_Student();
            a05_student.setStudent_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_ID)));
            a05_student.setStudent_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Name)));
            a05_student.setStudent_FatherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_FatherName)));
            a05_student.setStudent_MotherName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_MotherName)));
            a05_student.setStudent_UniqueID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_UniqueID)));
            a05_student.setStudent_Gender(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Gender)));
            a05_student.setStudent_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_IsActive)));
            a05_student.setStudent_SRNO(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_SRNO)));
            a05_student.setSC_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_ClassID)));
            a05_student.setSC_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_SectionID)));
            a05_studentsList.add(a05_student);
            cursor.moveToNext();
        }
        return a05_studentsList;
    }

    public long delete_tbl_A05_StudentDetail() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A05_Student, null, null);
        return i;
    }

    @SuppressLint("Range")
    public A06_StudentDetails getStudentClassDetails(String StudentID) {
        A06_StudentDetails a06StudentDetails = new A06_StudentDetails();
        String sql = "select  *  from  tbl_A06_Student where SC_StudentID='" + StudentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            a06StudentDetails.setSC_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_ID)));
            a06StudentDetails.setSC_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_SchoolID)));
            a06StudentDetails.setSC_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_ClassID)));
            a06StudentDetails.setSC_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_SectionID)));
            a06StudentDetails.setSC_AddedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_AddedOn)));
            a06StudentDetails.setSC_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_IsActive)));
            a06StudentDetails.setSC_AcademicYearID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_AcademicYearID)));
            a06StudentDetails.setSC_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_StudentID)));
            cursor.moveToNext();
        }
        return a06StudentDetails;
    }

    /*-------------------------A06StudentDetail-------------------*/
    public long insertA06_Student(A06_StudentDetails a06_studentDetails) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SC_ID, a06_studentDetails.getSC_ID());
        values.put(SQLiteHelper.SC_SchoolID, a06_studentDetails.getSC_SchoolID());
        values.put(SQLiteHelper.SC_ClassID, a06_studentDetails.getSC_ClassID());
        values.put(SQLiteHelper.SC_SectionID, a06_studentDetails.getSC_SectionID());
        values.put(SQLiteHelper.SC_AddedOn, a06_studentDetails.getSC_AddedOn());
        values.put(SQLiteHelper.SC_IsActive, a06_studentDetails.getSC_IsActive());
        values.put(SQLiteHelper.SC_AcademicYearID, a06_studentDetails.getSC_AcademicYearID());
        values.put(SQLiteHelper.SC_StudentID, a06_studentDetails.getSC_StudentID());
        isInserted = database.insert(SQLiteHelper.tbl_A06_Student, null, values);
        return isInserted;
    }

    public long delete_tbl_A06_StudentDetail() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A06_Student, null, null);
        return i;
    }

    /*-------------------------A07StudentProfile-------------------*/
    public long insertA07_Studentprofile(A07_StudentProfile a07_studentProfile) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SP_ID, a07_studentProfile.getSP_ID());
        values.put(SQLiteHelper.SP_SchoolID, a07_studentProfile.getSP_SchoolID());
        values.put(SQLiteHelper.SP_StudentID, a07_studentProfile.getSP_StudentID());
        values.put(SQLiteHelper.SP_ClassID, a07_studentProfile.getSP_ClassID());
        values.put(SQLiteHelper.SP_SectionID, a07_studentProfile.getSP_SectionID());
        values.put(SQLiteHelper.SP_CreationDate, a07_studentProfile.getSP_CreationDate());
        values.put(SQLiteHelper.SP_Lattitude, a07_studentProfile.getSP_Lattitude());
        values.put(SQLiteHelper.SP_Longitude, a07_studentProfile.getSP_Longitude());
        values.put(SQLiteHelper.SP_IsLast, a07_studentProfile.getSP_IsLast());
        values.put(SQLiteHelper.SP_IsSync, a07_studentProfile.getSP_IsSync());
        isInserted = database.insert(SQLiteHelper.tbl_A07_Studentprofile, null, values);
        return isInserted;
    }

    public long delete_tbl_A07_StudentProfile() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A07_Studentprofile, null, null);
        return i;
    }

    /*-------------------------A08StudentProfile-------------------*/
    public long insertA08_Studentprofile(A08_StudentProfilePhoto a08_studentProfile) {
        long isInserted;
        ContentValues values = new ContentValues();
        String JsonEmbeedings = String.valueOf(a08_studentProfile.getSPP_Embeeding());
        values.put(SQLiteHelper.SPP_ID, a08_studentProfile.getSPP_ID());
        values.put(SQLiteHelper.SPP_ProfileID, a08_studentProfile.getSPP_ProfileID());
        values.put(SQLiteHelper.SPP_FileName, a08_studentProfile.getSPP_FileName());
        values.put(SQLiteHelper.SPP_FilePath, a08_studentProfile.getSPP_FilePath());
        values.put(SQLiteHelper.SPP_FileURL, a08_studentProfile.getSPP_FileURL());
        values.put(SQLiteHelper.SPP_ServerFilePath, a08_studentProfile.getSPP_ServerFilePath());
        values.put(SQLiteHelper.SPP_Lattitude, a08_studentProfile.getSPP_Lattitude());
        values.put(SQLiteHelper.SPP_Longitude, a08_studentProfile.getSPP_Longitude());
        values.put(SQLiteHelper.SPP_IsActive, a08_studentProfile.getSPP_IsActive());
        values.put(SQLiteHelper.SP_IsSync, a08_studentProfile.getSP_IsSync());
        values.put(SQLiteHelper.SPP_Embeeding, JsonEmbeedings);
        values.put(SQLiteHelper.SPP_EmbeedingBytes, a08_studentProfile.getSPP_EmbeedingBytes());
        values.put(SQLiteHelper.SPP_StudentID, a08_studentProfile.getSPP_StudentID());

        isInserted = database.insert(SQLiteHelper.tbl_A08_StudentProfilePhoto, null, values);
        return isInserted;
    }

    @SuppressLint("Range")
    public List<A08_StudentProfilePhoto> gettbl_A08studentProfilePhoto() {
        List<A08_StudentProfilePhoto> a08_studentProfilePhotos = new ArrayList<>();
        String sql = "select  *  from  tbl_A08_StudentProfilePhoto";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A08_StudentProfilePhoto a08StudentProfilePhoto = new A08_StudentProfilePhoto();
            a08StudentProfilePhoto.setSPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ID)));
            a08StudentProfilePhoto.setSPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ProfileID)));
            a08StudentProfilePhoto.setSPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileName)));
            a08StudentProfilePhoto.setSPP_FilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FilePath)));
            a08StudentProfilePhoto.setSPP_FileURL(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileURL)));
            a08StudentProfilePhoto.setSPP_ServerFilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ServerFilePath)));
            a08StudentProfilePhoto.setSPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Lattitude)));
            a08StudentProfilePhoto.setSPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Longitude)));
            a08StudentProfilePhoto.setSPP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_IsActive)));
            a08StudentProfilePhoto.setSPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Embeeding)));
            a08StudentProfilePhoto.setSPP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID)));

            a08_studentProfilePhotos.add(a08StudentProfilePhoto);
            cursor.moveToNext();
        }
        return a08_studentProfilePhotos;
    }

    @SuppressLint("Range")
    public A08_StudentProfilePhoto gettbl_A08studentProfilePhoto_StudentID(String studentID) {
        A08_StudentProfilePhoto a08StudentProfilePhoto = new A08_StudentProfilePhoto();
        String sql = "select  *  from  tbl_A08_StudentProfilePhoto where SPP_StudentID='" + studentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            a08StudentProfilePhoto.setSPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ID)));
            a08StudentProfilePhoto.setSPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ProfileID)));
            a08StudentProfilePhoto.setSPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileName)));
            a08StudentProfilePhoto.setSPP_FilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FilePath)));
            a08StudentProfilePhoto.setSPP_FileURL(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileURL)));
            a08StudentProfilePhoto.setSPP_ServerFilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ServerFilePath)));
            a08StudentProfilePhoto.setSPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Lattitude)));
            a08StudentProfilePhoto.setSPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Longitude)));
            a08StudentProfilePhoto.setSPP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_IsActive)));
            a08StudentProfilePhoto.setSPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Embeeding)));
            a08StudentProfilePhoto.setSPP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID)));
            cursor.moveToNext();
        }
        cursor.close();
        return a08StudentProfilePhoto;
    }

    public long delete_tbl_A08_Studentprofilephoto() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A08_StudentProfilePhoto, null, null);
        return i;
    }

    public long delete_tbl_A09studentattendencedata() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A09studentattendencedata, null, null);
        return i;
    }

    public long insertA09_StudentAttendence(A09_StudentAttendance a09_studentAttendance) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Att_SchoolID, a09_studentAttendance.getAtt_SchoolID());
        values.put(SQLiteHelper.Att_StudentID, a09_studentAttendance.getAtt_StudentID());
        values.put(SQLiteHelper.Att_UniqueID, a09_studentAttendance.getAtt_StudentID());
        values.put(SQLiteHelper.Att_AttendaceTypeID, a09_studentAttendance.getAtt_AttendaceTypeID());
        values.put(SQLiteHelper.Att_PresentAbscent, a09_studentAttendance.getAtt_PresentAbscent());
        values.put(SQLiteHelper.Att_AttendanceDateTime, a09_studentAttendance.getAtt_AttendanceDateTime());
        values.put(SQLiteHelper.Att_AddedBy, a09_studentAttendance.getAtt_AddedBy());
        values.put(SQLiteHelper.Att_ClassID, a09_studentAttendance.getAtt_ClassID());
        values.put(SQLiteHelper.Att_SectionID, a09_studentAttendance.getAtt_SectionID());
        values.put(SQLiteHelper.Att_Lattitude, a09_studentAttendance.getAtt_Lattitude());
        values.put(SQLiteHelper.Att_AbsentReasonID, a09_studentAttendance.getAtt_AbsentReasonID());
        values.put(SQLiteHelper.Is_Sync, a09_studentAttendance.getIs_Sync());
        values.put(SQLiteHelper.Att_Longitude, a09_studentAttendance.getAtt_Longitude());
        values.put(SQLiteHelper.NtpServerTime, a09_studentAttendance.getNtpServerTime());
        values.put(SQLiteHelper.AddedOn, a09_studentAttendance.getAtt_AddedON());
        values.put(SQLiteHelper.Att_AppVersion, a09_studentAttendance.getAtt_AppVersion());
        values.put(SQLiteHelper.Att_LocalAppId, a09_studentAttendance.getAtt_LocalAppId());
        values.put(SQLiteHelper.Att_GpsTime, a09_studentAttendance.getAtt_GPSTime());

        isInserted = database.insert(SQLiteHelper.tbl_A09studentattendencedata, null, values);
        return isInserted;
    }

    public long delete_tbl_A09Teacherattendencedata() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A09Teacherattendencedata, null, null);
        return i;
    }

    public long insertA09_TeacherAttendence(A09_TeacherAttendance a09_teacherAttendance) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Att_ID, a09_teacherAttendance.getAtt_ID());
        values.put(SQLiteHelper.Att_SchoolID, a09_teacherAttendance.getAtt_SchoolID());
        values.put(SQLiteHelper.Att_TeacherID, a09_teacherAttendance.getAtt_TeacherID());
        values.put(SQLiteHelper.Att_AttendaceTypeID, a09_teacherAttendance.getAtt_AttendaceTypeID());
        values.put(SQLiteHelper.Att_PresentAbscent, a09_teacherAttendance.getAtt_PresentAbscent());
        values.put(SQLiteHelper.Att_AttendanceDateTime, a09_teacherAttendance.getAtt_AttendanceDateTime());
        values.put(SQLiteHelper.Att_AddedBy, a09_teacherAttendance.getAtt_AddedBy());
        values.put(SQLiteHelper.Att_ClassID, a09_teacherAttendance.getAtt_ClassID());
        values.put(SQLiteHelper.Att_SectionID, a09_teacherAttendance.getAtt_SectionID());
        values.put(SQLiteHelper.Att_Lattitude, a09_teacherAttendance.getAtt_Lattitude());
        values.put(SQLiteHelper.Att_Longitude, a09_teacherAttendance.getAtt_Longitude());
        values.put(SQLiteHelper.Att_IsLate, a09_teacherAttendance.getAtt_IsLate());
        values.put(SQLiteHelper.Att_LateReasonID, a09_teacherAttendance.getAtt_LateReasonID());
        values.put(SQLiteHelper.Att_AbsentReasonID, a09_teacherAttendance.getAtt_AbsentReasonID());
        values.put(SQLiteHelper.Att_IsActive, a09_teacherAttendance.getAtt_IsActive());
        values.put(SQLiteHelper.Is_ssync, a09_teacherAttendance.getIs_ssync());
        values.put(SQLiteHelper.AddedOn, a09_teacherAttendance.getAtt_AddedON());
        values.put(SQLiteHelper.NTP_ServerTime, a09_teacherAttendance.getNTP_ServerTime());
        values.put(SQLiteHelper.Att_AppVersion, a09_teacherAttendance.getAtt_AppVersion());
        values.put(SQLiteHelper.Att_LocalAppId, a09_teacherAttendance.getAtt_LocalAppId());
        values.put(SQLiteHelper.Att_GpsTime, a09_teacherAttendance.getAtt_GPSTime());

        isInserted = database.insert(SQLiteHelper.tbl_A09Teacherattendencedata, null, values);
        return isInserted;
    }

    @SuppressLint("Range")
    public List<A09_TeacherAttendance> getTeacherAttedance() {
        List<A09_TeacherAttendance> m09_lateReasons = new ArrayList<>();
        String sql = "select  *  from  tbl_A09Teacherattendencedata where Is_ssync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A09_TeacherAttendance m09_lateReason = new A09_TeacherAttendance();
            m09_lateReason.setAtt_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ID)));
            m09_lateReason.setAtt_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SchoolID)));
            m09_lateReason.setAtt_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_TeacherID)));
            m09_lateReason.setAtt_AttendaceTypeID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID)));
            m09_lateReason.setAtt_PresentAbscent(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_PresentAbscent)));
            m09_lateReason.setAtt_AttendanceDateTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendanceDateTime)));
            m09_lateReason.setAtt_AddedBy(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AddedBy)));
            m09_lateReason.setAtt_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ClassID)));
            m09_lateReason.setAtt_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SectionID)));
            m09_lateReason.setAtt_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Lattitude)));
            m09_lateReason.setAtt_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Longitude)));
            m09_lateReason.setAtt_IsLate(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_IsLate)));
            m09_lateReason.setAtt_LateReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LateReasonID)));
            m09_lateReason.setAtt_AbsentReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AbsentReasonID)));
            m09_lateReason.setAtt_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_IsActive)));
            m09_lateReason.setIs_ssync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Is_ssync)));
            m09_lateReason.setAtt_AddedON(cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)));
            m09_lateReason.setNTP_ServerTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.NTP_ServerTime)));
            m09_lateReason.setAtt_AppVersion(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AppVersion)));
            m09_lateReason.setAtt_LocalAppId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LocalAppId)));
            m09_lateReason.setAtt_GPSTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_GpsTime)));
         //   m09_lateReason.setImageDetails(gettbl_A01_CapturedFaceDetailsTeacherAttendance_for_id(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_TeacherID)), cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)), cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID))));
            m09_lateReasons.add(m09_lateReason);
            cursor.moveToNext();
        }
        cursor.close();
        return m09_lateReasons;
    }

    @SuppressLint("Range")
    public List<A09_StudentAttendance> getStudentAttedance() {
        List<A09_StudentAttendance> m09_lateReasons = new ArrayList<>();
        String sql = "select  *  from  tbl_A09studentattendencedata where Is_Sync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A09_StudentAttendance m09_lateReason = new A09_StudentAttendance();
            m09_lateReason.setAtt_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SchoolID)));
            m09_lateReason.setAtt_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_StudentID)));
            m09_lateReason.setAtt_UniqueID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_UniqueID)));
            m09_lateReason.setAtt_AttendaceTypeID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID)));
            m09_lateReason.setAtt_PresentAbscent(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_PresentAbscent)));
            m09_lateReason.setAtt_AttendanceDateTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendanceDateTime)));
            m09_lateReason.setAtt_AddedBy(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AddedBy)));
            m09_lateReason.setAtt_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ClassID)));
            m09_lateReason.setAtt_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SectionID)));
            m09_lateReason.setAtt_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Lattitude)));
            m09_lateReason.setAtt_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Longitude)));
            m09_lateReason.setAtt_AbsentReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AbsentReasonID)));
            m09_lateReason.setIs_Sync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Is_Sync)));
            m09_lateReason.setAtt_AppVersion(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AppVersion)));
            m09_lateReason.setAtt_LocalAppId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LocalAppId)));
            m09_lateReason.setAtt_GPSTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_GpsTime)));
            m09_lateReason.setAtt_AddedON(cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)));
            m09_lateReason.setNtpServerTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.NtpServerTime)));
          //  m09_lateReason.setImageDetails(gettbl_A01_CapturedFaceDetailsStudentAttendance_for_id(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_StudentID)), cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)), cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID))));
            m09_lateReasons.add(m09_lateReason);
            cursor.moveToNext();
        }
        cursor.close();
        return m09_lateReasons;
    }

    public long updatetbl_A09_TeacherAttendanceData(String TeacherID) {
        long i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Is_ssync, "true");
        String where = SQLiteHelper.Att_TeacherID + "=? ";
        String[] whereArgs = {TeacherID};
        i = database.update(SQLiteHelper.tbl_A09Teacherattendencedata, values, where, whereArgs);
        return i;
    }

    public long updatetbl_A09_StudentAttendanceData(String Studentid) {
        long i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Is_Sync, "true");
        String where = SQLiteHelper.Att_StudentID + "=? ";
        String[] whereArgs = {Studentid};
        i = database.update(SQLiteHelper.tbl_A09studentattendencedata, values, where, whereArgs);
        return i;
    }

    /*-------------Late Reason Master-------------*/
    public long delete_tbl_M09_LateReasonlateTeacherdata() {
        long i;
        i = database.delete(SQLiteHelper.tbl_lateTeacherattendencedata, null, null);
        return i;
    }



    public int count_ClassMaster() {
        String sql = "select  *  from  tbl_MasterClassdata";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long delete_tbl_MasterSectiondata() {
        long i;
        i = database.delete(SQLiteHelper.tbl_MasterSectiondata, null, null);
        return i;
    }

    public int count_SectionMasterData() {
        String sql = "select  *  from  tbl_MasterSectiondata ";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfilePhoto> getListOf_tbl_ProfilePhotoDataMasterNew(String personId) {
        List<A01_TeacherProfilePhoto> tbl_profilePhotoList = new ArrayList<>();
        String sql = "select  *  from  tbl_A01_TeacherProfilePhoto where TPP_Embeeding not null and TPP_ProfileID='" + personId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfilePhoto traineeDetails = new A01_TeacherProfilePhoto();
            traineeDetails.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            traineeDetails.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            traineeDetails.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            traineeDetails.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            traineeDetails.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            traineeDetails.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            traineeDetails.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            traineeDetails.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            traineeDetails.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            traineeDetails.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            traineeDetails.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            traineeDetails.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            traineeDetails.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
            tbl_profilePhotoList.add(traineeDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return tbl_profilePhotoList;
    }

    @SuppressLint("Range")
    public A01_TeacherProfilePhoto getTeacherProfileData(String personId) {
        A01_TeacherProfilePhoto a01_teacherProfilePhoto = new A01_TeacherProfilePhoto();
        String sql = "select  *  from  tbl_A01_TeacherProfilePhoto where TPP_TeacherID='" + personId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            a01_teacherProfilePhoto.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            a01_teacherProfilePhoto.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            a01_teacherProfilePhoto.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            a01_teacherProfilePhoto.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            a01_teacherProfilePhoto.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            a01_teacherProfilePhoto.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            a01_teacherProfilePhoto.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            a01_teacherProfilePhoto.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            a01_teacherProfilePhoto.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            a01_teacherProfilePhoto.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            a01_teacherProfilePhoto.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            a01_teacherProfilePhoto.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            a01_teacherProfilePhoto.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherProfilePhoto;
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfilePhoto> getTeacherProfileDatalistISSync(String personId, String Is_Sync) {
        List<A01_TeacherProfilePhoto> a01_teacherProfilePhoto = new ArrayList<>();
        String sql = "select  *  from  tbl_A01_TeacherProfilePhoto where TPP_TeacherID='" + personId + "' and TP_IsSync='" + Is_Sync + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfilePhoto traineeDetails = new A01_TeacherProfilePhoto();
            traineeDetails.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            traineeDetails.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            traineeDetails.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            traineeDetails.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            traineeDetails.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            traineeDetails.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            traineeDetails.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            traineeDetails.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            traineeDetails.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            traineeDetails.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            traineeDetails.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            traineeDetails.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            traineeDetails.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
            a01_teacherProfilePhoto.add(traineeDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherProfilePhoto;
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfilePhoto> getTeacherProfileDatalistUnISSync(String Is_Sync) {
        List<A01_TeacherProfilePhoto> a01_teacherProfilePhoto = new ArrayList<>();
        String sql = "select  *  from  tbl_A01_TeacherProfilePhoto where TP_IsSync='" + Is_Sync + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfilePhoto traineeDetails = new A01_TeacherProfilePhoto();
            traineeDetails.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            traineeDetails.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            traineeDetails.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            traineeDetails.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            traineeDetails.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            traineeDetails.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            traineeDetails.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            traineeDetails.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            traineeDetails.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            traineeDetails.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            traineeDetails.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            traineeDetails.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            traineeDetails.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
            a01_teacherProfilePhoto.add(traineeDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherProfilePhoto;
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfilePhoto> getTeacherProfileDatalist(String personId) {
        List<A01_TeacherProfilePhoto> a01_teacherProfilePhoto = new ArrayList<>();
        String sql = "";
        if (personId != null && !personId.equalsIgnoreCase("")) {
            sql = "select  *  from  tbl_A01_TeacherProfilePhoto as A01_TPP inner join tbl_A01_Teacher_profile as A01_TP on  A01_TPP.TPP_TeacherID=A01_TP.TP_TeacherID where  A01_TP.TP_IsVerified='1' and A01_TPP.TPP_TeacherID='" + personId + "' ";
        } else {
            sql = "select  *  from  tbl_A01_TeacherProfilePhoto as A01_TPP inner join tbl_A01_Teacher_profile as A01_TP on  A01_TPP.TPP_TeacherID=A01_TP.TP_TeacherID where  A01_TP.TP_IsVerified='1'  ";
        }
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfilePhoto traineeDetails = new A01_TeacherProfilePhoto();
            traineeDetails.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            traineeDetails.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            traineeDetails.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            traineeDetails.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            traineeDetails.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            traineeDetails.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            traineeDetails.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            traineeDetails.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            traineeDetails.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            traineeDetails.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            traineeDetails.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            traineeDetails.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
            traineeDetails.setTPP_EmbeedingBytesForAtten(new Gson().fromJson(traineeDetails.getTPP_EmbeedingBytes(), byte[].class));
            traineeDetails.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            a01_teacherProfilePhoto.add(traineeDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherProfilePhoto;
    }

    public int gettblA05studentdetailListCount() {
        String sql = "select  A06S.SC_ClassID,A06S.SC_SectionID,A05S.Student_ID, A05S.Student_Name, A05S.Student_FatherName, A05S. Student_MotherName, A05S.Student_UniqueID, A05S. Student_Gender, A05S. Student_IsActive, A05S.Student_SRNO  from tbl_A05_Student   A05S left  join tbl_A06_Student   A06S on A05S.Student_ID =  A06S.SC_StudentID  where A05S.Student_ID not null ";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long update_ProfilePhotoDetails_forteacher(String ProfileID) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TP_IsSync, "true");
        String where = SQLiteHelper.TPP_TeacherID + "=? ";
        String[] whereArgs = {ProfileID};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A01_TeacherProfilePhoto, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    public long update_tbl_A01_Teacher_profile(String PersonID) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TP_IsSync, "true");
        String where = SQLiteHelper.TP_TeacherID + "=? ";
        String[] whereArgs = {PersonID};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A01_Teacher_profile, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfile> getA01_TeacherProfileUnsyncDataList(String true_false) {
        List<A01_TeacherProfile> teacherProfileList = new ArrayList<>();
        String sql = "select  *  from tbl_A01_Teacher_profile where TP_IsSync='" + true_false + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfile a01_teacherProfile = new A01_TeacherProfile();
            a01_teacherProfile.setTP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_ID)));
            a01_teacherProfile.setTP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_TeacherID)));
            a01_teacherProfile.setTP_CreatedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_CreatedOn)));
            a01_teacherProfile.setTP_VerifiedBy(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_VerifiedBy)));
            a01_teacherProfile.setTP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsActive)));
            a01_teacherProfile.setTP_IsVerified(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsVerified)));
            a01_teacherProfile.setTP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_IsSync)));
            a01_teacherProfile.setObjPhoto(getTeacherProfileDatalistUnISSync_ID("false", cursor.getString(cursor.getColumnIndex(SQLiteHelper.TP_TeacherID))));
            teacherProfileList.add(a01_teacherProfile);
            cursor.moveToNext();
        }
        cursor.close();
        return teacherProfileList;
    }

    @SuppressLint("Range")
    public List<A01_TeacherProfilePhoto> getTeacherProfileDatalistUnISSync_ID(String Is_Sync, String ID) {
        List<A01_TeacherProfilePhoto> a01_teacherProfilePhoto = new ArrayList<>();
        String sql = "select  *  from  tbl_A01_TeacherProfilePhoto where TP_IsSync='" + Is_Sync + "' and TPP_TeacherID='" + ID + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A01_TeacherProfilePhoto traineeDetails = new A01_TeacherProfilePhoto();
            traineeDetails.setTPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ID)));
            traineeDetails.setTPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ProfileID)));
            traineeDetails.setTPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileName)));
            traineeDetails.setTPP_FileServerPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileServerPath)));
            traineeDetails.setTPP_FileFullPath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_FileFullPath)));
            traineeDetails.setTPP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_IsLast)));
            traineeDetails.setTPP_ClickedOn(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_ClickedOn)));
            traineeDetails.setTPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Lattitude)));
            traineeDetails.setTPP_PersonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_PersonID)));
            traineeDetails.setTPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Longitude)));
            traineeDetails.setTPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_Embeeding)));
            traineeDetails.setTPP_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID)));
            traineeDetails.setTPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_EmbeedingBytes)));
          //  traineeDetails.setImageDetails(gettbl_A01_CapturedFaceDetailsTeacherProfilePhoto_for_id(cursor.getString(cursor.getColumnIndex(SQLiteHelper.TPP_TeacherID))));
            a01_teacherProfilePhoto.add(traineeDetails);
            cursor.moveToNext();
        }
        cursor.close();
        return a01_teacherProfilePhoto;
    }

    public long update_ProfilePhotoDetails_forStudent(String ProfileID) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SP_IsSync, "true");
        String where = SQLiteHelper.SP_StudentID + "=? ";
        String[] whereArgs = {ProfileID};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A07_Studentprofile, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    @SuppressLint("Range")
    public List<A07_StudentProfile> gettbl_A07studentprofileObj_false(String Is_Sync) {
        List<A07_StudentProfile> a07_studentProfileList = new ArrayList<>();
        String sql = "select  *  from  tbl_A07_Studentprofile where SP_IsSync='" + Is_Sync + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A07_StudentProfile a07StudentProfile = new A07_StudentProfile();
            a07StudentProfile.setSP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_ID)));
            a07StudentProfile.setSP_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_SchoolID)));
            a07StudentProfile.setSP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_StudentID)));
            a07StudentProfile.setSP_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_ClassID)));
            a07StudentProfile.setSP_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_SectionID)));
            a07StudentProfile.setSP_CreationDate(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_CreationDate)));
            a07StudentProfile.setSP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_Lattitude)));
            a07StudentProfile.setSP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_Longitude)));
            a07StudentProfile.setSP_IsLast(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsLast)));
            a07StudentProfile.setSP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsSync)));
            a07StudentProfile.setObjPhoto(gettbl_A08studentprofileData_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_StudentID)), "false"));
            a07_studentProfileList.add(a07StudentProfile);
            cursor.moveToNext();
        }
        cursor.close();
        return a07_studentProfileList;
    }

    @SuppressLint("Range")
    public List<A08_StudentProfilePhoto> gettbl_A08studentprofileData_ID(String sp_studentID, String value) {
        List<A08_StudentProfilePhoto> a08_studentProfilePhotos = new ArrayList<>();
        String sql = "select  *  from  tbl_A08_StudentProfilePhoto where SPP_StudentID='" + sp_studentID + "' and SP_IsSync ='" + value + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A08_StudentProfilePhoto a08StudentProfilePhoto = new A08_StudentProfilePhoto();
            a08StudentProfilePhoto.setSPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ID)));
            a08StudentProfilePhoto.setSPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ProfileID)));
            a08StudentProfilePhoto.setSPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileName)));
            a08StudentProfilePhoto.setSPP_FilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FilePath)));
            a08StudentProfilePhoto.setSPP_FileURL(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileURL)));
            a08StudentProfilePhoto.setSPP_ServerFilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ServerFilePath)));
            a08StudentProfilePhoto.setSPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Lattitude)));
            a08StudentProfilePhoto.setSPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Longitude)));
            a08StudentProfilePhoto.setSPP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_IsActive)));
            a08StudentProfilePhoto.setSPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Embeeding)));
            a08StudentProfilePhoto.setSP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsSync)));
            a08StudentProfilePhoto.setSPP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID)));
            a08StudentProfilePhoto.setSPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_EmbeedingBytes)));
          //  a08StudentProfilePhoto.setImageDetails(gettbl_A01_CapturedFaceDetailsStudentProfilePhoto_for_id(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID))));
            a08_studentProfilePhotos.add(a08StudentProfilePhoto);
            cursor.moveToNext();
        }
        return a08_studentProfilePhotos;
    }

    public long delete_tbl_A01_Teacherprofile_for_id(String TeacherID) {
        long i = 0;
        String sql = "select  *  from tbl_A01_Teacher_profile where TP_TeacherID='" + TeacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.TP_TeacherID + "=? ";
            String[] whereArgs = {TeacherID};
            i = database.delete(SQLiteHelper.tbl_A01_Teacher_profile, where, whereArgs);
        }
        return i;
    }

    public long delete_tbl_A01_TeacherProfilePhoto_for_id(String TeacherID) {
        long i = 0;
        String sql = "select  *  from tbl_A01_TeacherProfilePhoto where TPP_TeacherID='" + TeacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.TPP_TeacherID + "=? ";
            String[] whereArgs = {TeacherID};
            i = database.delete(SQLiteHelper.tbl_A01_TeacherProfilePhoto, where, whereArgs);
        }
        return i;
    }

    public long delete_tbl_A07_StudentProfile_for_id(String studentId) {
        long i = 0;
        String sql = "select  *  from tbl_A07_Studentprofile where SP_StudentID='" + studentId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.SP_StudentID + "=? ";
            String[] whereArgs = {studentId};
            i = database.delete(SQLiteHelper.tbl_A07_Studentprofile, where, whereArgs);
        }
        return i;
    }

    public long delete_tbl_A08_StudentprofileProfilePhoto_for_id(String studentId) {
        long i = 0;
        String sql = "select  *  from tbl_A08_StudentProfilePhoto where SPP_StudentID='" + studentId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.SPP_StudentID + "=? ";
            String[] whereArgs = {studentId};
            i = database.delete(SQLiteHelper.tbl_A08_StudentProfilePhoto, where, whereArgs);
        }
        return i;
    }

    @SuppressLint("Range")
    public ArrayList<A08_StudentProfilePhoto> getStudentForAttendance_Embeedings(String SectionID, String ClassID) {
        ArrayList<A08_StudentProfilePhoto> a08_studentProfilePhotos = new ArrayList<>();
        String sql = "Select tblA08.SPP_IsActive,tblA08.SPP_ID, tblA08.SPP_ProfileID, tblA08.SPP_FileName, tblA08.SPP_FilePath, tblA08.SPP_FileURL, tblA08.SPP_ServerFilePath, tblA08.SPP_Lattitude, tblA08.SPP_Longitude, tblA08.SPP_Embeeding, tblA08.SPP_EmbeedingBytes, tblA08.SP_IsSync, tblA08.SPP_StudentID, A07SP.SP_ClassID as SP_ClassID, A07SP.SP_SectionID as SP_SectionID from  tbl_A07_Studentprofile as A07SP left join tbl_A08_StudentProfilePhoto as tblA08 on tblA08.SPP_StudentID=A07SP.SP_StudentID where A07SP.SP_ClassID='" + ClassID + "' and A07SP.SP_SectionID='" + SectionID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A08_StudentProfilePhoto a08StudentProfilePhoto = new A08_StudentProfilePhoto();
            a08StudentProfilePhoto.setSPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ID)));
            a08StudentProfilePhoto.setSPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ProfileID)));
            a08StudentProfilePhoto.setSPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileName)));
            a08StudentProfilePhoto.setSPP_FilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FilePath)));
            a08StudentProfilePhoto.setSPP_FileURL(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileURL)));
            a08StudentProfilePhoto.setSPP_ServerFilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ServerFilePath)));
            a08StudentProfilePhoto.setSPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Lattitude)));
            a08StudentProfilePhoto.setSPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Longitude)));
            a08StudentProfilePhoto.setSPP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_IsActive)));
            a08StudentProfilePhoto.setSPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Embeeding)));
            a08StudentProfilePhoto.setSPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_EmbeedingBytes)));
            a08StudentProfilePhoto.setSPP_EmbeedingBytesForAtten(new Gson().fromJson(a08StudentProfilePhoto.getSPP_EmbeedingBytes(), byte[].class));
            a08StudentProfilePhoto.setSP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsSync)));
            a08StudentProfilePhoto.setSPP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID)));
            a08StudentProfilePhoto.setSP_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_SectionID)));
            a08StudentProfilePhoto.setSP_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_ClassID)));
            a08_studentProfilePhotos.add(a08StudentProfilePhoto);
            cursor.moveToNext();
        }
        cursor.close();
        return a08_studentProfilePhotos;
    }

    public int getStudentAlreadypresent(String studentid, String localdate, String AttendanceType) {
        int a = 0;
        try {
            String sql = "select  *  from  tbl_A09studentattendencedata where Att_StudentID ='" + studentid + "'  and  AddedOn='" + localdate + "' and  Att_AttendaceTypeID='" + AttendanceType + "' ";
            Cursor cursor = database.rawQuery(sql, null);
            a = cursor.getCount();
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return a;
    }

    @SuppressLint("Range")
    public String getNameForStudentA5(String Student_ID) {
        String student_ID = "";
        String sql = "select  *  from  tbl_A05_Student  where Student_ID='" + Student_ID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            student_ID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Name));
            cursor.moveToNext();
        }
        return student_ID;
    }

    @SuppressLint("Range")
    public String getA01_TeacherDetails(String Student_ID) {
        String student_ID = "";
        String sql = "select  *  from  tbl_A01_Teacher  where Teacher_ID='" + Student_ID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            student_ID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name));
            cursor.moveToNext();
        }
        return student_ID;
    }

    @SuppressLint("Range")
    public String get_SchoolCode(String Student_ID) {
        String student_ID = "";
        String sql = "select  *  from  tbl_A06_Student  where SC_StudentID='" + Student_ID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            student_ID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.SC_SchoolID));
            cursor.moveToNext();
        }
        return student_ID;
    }

    @SuppressLint("Range")
    public ArrayList<A08_StudentProfilePhoto> gettblA05studentdetailList_StudentID(String SectionID, String ClassID, String StudentID) {
        ArrayList<A08_StudentProfilePhoto> a08_studentProfilePhotos = new ArrayList<>();
        String sql = "Select tblA08.SPP_IsActive,tblA08.SPP_ID, tblA08.SPP_ProfileID, tblA08.SPP_FileName, tblA08.SPP_FilePath, tblA08.SPP_FileURL, tblA08.SPP_ServerFilePath, tblA08.SPP_Lattitude, tblA08.SPP_Longitude, tblA08.SPP_Embeeding, tblA08.SPP_EmbeedingBytes,tblA08.SP_IsSync, tblA08.SPP_StudentID, A07SP.SP_ClassID as SP_ClassID, A07SP.SP_SectionID as SP_SectionID from  tbl_A07_Studentprofile as A07SP left join tbl_A08_StudentProfilePhoto as tblA08 on tblA08.SPP_StudentID=A07SP.SP_StudentID where A07SP.SP_ClassID='" + ClassID + "' and A07SP.SP_SectionID='" + SectionID + "' and SPP_StudentID ='" + StudentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A08_StudentProfilePhoto a08StudentProfilePhoto = new A08_StudentProfilePhoto();
            a08StudentProfilePhoto.setSPP_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ID)));
            a08StudentProfilePhoto.setSPP_ProfileID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ProfileID)));
            a08StudentProfilePhoto.setSPP_FileName(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileName)));
            a08StudentProfilePhoto.setSPP_FilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FilePath)));
            a08StudentProfilePhoto.setSPP_FileURL(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_FileURL)));
            a08StudentProfilePhoto.setSPP_ServerFilePath(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_ServerFilePath)));
            a08StudentProfilePhoto.setSPP_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Lattitude)));
            a08StudentProfilePhoto.setSPP_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Longitude)));
            a08StudentProfilePhoto.setSPP_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_IsActive)));
            a08StudentProfilePhoto.setSPP_Embeeding(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_Embeeding)));
            a08StudentProfilePhoto.setSPP_EmbeedingBytes(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_EmbeedingBytes)));
            a08StudentProfilePhoto.setSPP_EmbeedingBytesForAtten(new Gson().fromJson(a08StudentProfilePhoto.getSPP_EmbeedingBytes(), byte[].class));
            a08StudentProfilePhoto.setSP_IsSync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsSync)));
            a08StudentProfilePhoto.setSPP_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SPP_StudentID)));
            a08StudentProfilePhoto.setSP_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_SectionID)));
            a08StudentProfilePhoto.setSP_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_ClassID)));
            a08_studentProfilePhotos.add(a08StudentProfilePhoto);
            cursor.moveToNext();
        }
        return a08_studentProfilePhotos;
    }

    @SuppressLint("Range")
    public List<A09_TeacherAttendance> getA01_TeacherAttendanceList(String date) {
        List<A09_TeacherAttendance> m09_lateReasons = new ArrayList<>();
        String sql = "select  *  from tbl_A09Teacherattendencedata where AddedOn ='" + date + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A09_TeacherAttendance m09_lateReason = new A09_TeacherAttendance();
            m09_lateReason.setAtt_ID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ID)));
            m09_lateReason.setAtt_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SchoolID)));
            m09_lateReason.setAtt_TeacherID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_TeacherID)));
            m09_lateReason.setAtt_AttendaceTypeID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID)));
            m09_lateReason.setAtt_PresentAbscent(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_PresentAbscent)));
            m09_lateReason.setAtt_AttendanceDateTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendanceDateTime)));
            m09_lateReason.setAtt_AddedBy(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AddedBy)));
            m09_lateReason.setAtt_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ClassID)));
            m09_lateReason.setAtt_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SectionID)));
            m09_lateReason.setAtt_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Lattitude)));
            m09_lateReason.setAtt_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Longitude)));
            m09_lateReason.setAtt_IsLate(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_IsLate)));
            m09_lateReason.setAtt_LateReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LateReasonID)));
            m09_lateReason.setAtt_AbsentReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AbsentReasonID)));
            m09_lateReason.setAtt_IsActive(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_IsActive)));
            m09_lateReason.setIs_ssync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Is_ssync)));
            m09_lateReason.setAtt_AddedON(cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)));
            m09_lateReason.setNTP_ServerTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.NTP_ServerTime)));
            m09_lateReason.setAtt_AppVersion(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AppVersion)));
            m09_lateReason.setAtt_LocalAppId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LocalAppId)));
            m09_lateReason.setAtt_GPSTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_GpsTime)));
            m09_lateReasons.add(m09_lateReason);
            cursor.moveToNext();
        }
        cursor.close();
        return m09_lateReasons;
    }

    @SuppressLint("Range")
    public String gettbl_A07studentprofileIsSynced(String studentId) {
        String studentIsSynced = "";
        String sql = "select  *  from  tbl_A07_Studentprofile where SP_StudentID='" + studentId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            studentIsSynced = cursor.getString(cursor.getColumnIndex(SQLiteHelper.SP_IsSync));
            cursor.moveToNext();
        }
        cursor.close();
        return studentIsSynced;
    }

    @SuppressLint("Range")
    public ArrayList<A09_StudentAttendance> getA09_StudentAttendance(String sectionID, String classid, String selecttedDate) {
        ArrayList<A09_StudentAttendance> m09_lateReasons = new ArrayList<>();
        String sql = "select A05S.Student_Name, A09SAtt.Att_SchoolID, A09SAtt.Att_StudentID, A09SAtt.Att_UniqueID, A09SAtt.Att_AttendaceTypeID, A09SAtt.Att_PresentAbscent, A09SAtt.Att_AttendanceDateTime, A09SAtt.Att_AddedBy, A09SAtt.Att_ClassID, A09SAtt.Att_SectionID, A09SAtt.Att_Lattitude, A09SAtt.Att_Longitude, A09SAtt.Att_AbsentReasonID, A09SAtt.Is_Sync, A09SAtt.AddedOn, A09SAtt.Att_AppVersion, A09SAtt.Att_LocalAppId, A09SAtt.Att_GpsTime  from  tbl_A09studentattendencedata as A09SAtt inner join tbl_A05_Student as A05S  on A09SAtt.Att_StudentID=A05S.Student_ID where A09SAtt.Att_SectionID='" + sectionID + "' and A09SAtt.Att_ClassID='" + classid + "' and A09SAtt.AddedOn='" + selecttedDate + "' order by A05S.Student_Name";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            A09_StudentAttendance m09_lateReason = new A09_StudentAttendance();
            m09_lateReason.setAtt_SchoolID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SchoolID)));
            m09_lateReason.setAtt_StudentID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_StudentID)));
            m09_lateReason.setAtt_UniqueID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_UniqueID)));
            m09_lateReason.setAtt_AttendaceTypeID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendaceTypeID)));
            m09_lateReason.setAtt_PresentAbscent(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_PresentAbscent)));
            m09_lateReason.setAtt_AttendanceDateTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AttendanceDateTime)));
            m09_lateReason.setAtt_AddedBy(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AddedBy)));
            m09_lateReason.setAtt_ClassID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_ClassID)));
            m09_lateReason.setAtt_SectionID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_SectionID)));
            m09_lateReason.setAtt_Lattitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Lattitude)));
            m09_lateReason.setAtt_Longitude(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_Longitude)));
            m09_lateReason.setAtt_AbsentReasonID(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AbsentReasonID)));
            m09_lateReason.setIs_Sync(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Is_Sync)));
            m09_lateReason.setAtt_AddedON(cursor.getString(cursor.getColumnIndex(SQLiteHelper.AddedOn)));
            m09_lateReason.setAtt_AppVersion(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_AppVersion)));
            m09_lateReason.setAtt_LocalAppId(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_LocalAppId)));
            m09_lateReason.setAtt_GPSTime(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Att_GpsTime)));
            m09_lateReason.setStudent_Name(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Student_Name)));
            m09_lateReasons.add(m09_lateReason);
            cursor.moveToNext();
        }
        cursor.close();
        return m09_lateReasons;
    }

    public int count_tbl_A01_TeacherProfilePhoto() {
        String sql = "select  *  from tbl_A01_TeacherProfilePhoto";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A01_Teacher_profile() {
        String sql = "select  *  from tbl_A01_Teacher_profile";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A05_Student() {
        String sql = "select  *  from tbl_A05_Student";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A06_Student() {
        String sql = "select  *  from tbl_A06_Student";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A07_Studentprofile() {
        String sql = "select  *  from tbl_A07_Studentprofile";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A08_StudentProfilePhoto() {
        String sql = "select  *  from tbl_A08_StudentProfilePhoto";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long update_tbl_A08_StudentProfilePhoto(String PersonID) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SP_IsSync, "true");
        String where = SQLiteHelper.SPP_StudentID + "=? ";
        String[] whereArgs = {PersonID};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A08_StudentProfilePhoto, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    public long delete_tbl_M05_SchoolLocation() {
        long i;
        i = database.delete(SQLiteHelper.tbl_M05_SchoolLocation, null, null);
        return i;
    }

    public long delete_tbl_M05_SchoolLocationPhoto() {
        long i;
        i = database.delete(SQLiteHelper.tbl_M05_SchoolLocationPhoto, null, null);
        return i;
    }

    public long update_tbl_M05_SchoolLocationPhoto(String commonId) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.LocationPhoto_IsSync, "true");
        String where = SQLiteHelper.commonID + "=? ";
        String[] whereArgs = {commonId};
        long isUpdate = database.update(SQLiteHelper.tbl_M05_SchoolLocationPhoto, values, where, whereArgs);
        return isUpdate;
    }

    public long update_tbl_M05_SchoolLocation(String commonId) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Location_IsSync, "true");
        String where = SQLiteHelper.commonID + "=? ";
        String[] whereArgs = {commonId};
        long isUpdate = database.update(SQLiteHelper.tbl_M05_SchoolLocation, values, where, whereArgs);
        return isUpdate;
    }

    public int teacher_profile_count() {
        String sql = "select  *  from tbl_A01_Teacher_profile where TP_IsSync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int teacher_attendance_count() {
        String sql = "select  *  from  tbl_A09Teacherattendencedata where Is_ssync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int student_profile_count() {
        String sql = "select  *  from  tbl_A07_Studentprofile where SP_IsSync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int student_attendance_count() {
        String sql = "select  *  from  tbl_A09studentattendencedata where Is_Sync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long updatetbl_A01_Teacherprofile_verification(String TeacherID, String IsVerified) {
        long i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TP_IsVerified, IsVerified);
        String where = SQLiteHelper.TP_TeacherID + "=? ";
        String[] whereArgs = {TeacherID};
        i = database.update(SQLiteHelper.tbl_A01_Teacher_profile, values, where, whereArgs);
        return i;
    }

    public int getCount_for_teacher_profile_photo_data(String TeacherID) {
        String sql = "select  *  from tbl_A01_TeacherProfilePhoto where TPP_TeacherID='" + TeacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long updatetbl_A01_Teacherprofile_AllDetail(A01_TeacherProfilePhoto a01_teacherProfilePhoto) {
        long i = 0;
        ContentValues values = new ContentValues();
        String JsonEmbeedings = String.valueOf(a01_teacherProfilePhoto.getTPP_Embeeding());
        values.put(SQLiteHelper.TPP_ID, a01_teacherProfilePhoto.getTPP_ID());
        values.put(SQLiteHelper.TPP_ProfileID, a01_teacherProfilePhoto.getTPP_ProfileID());
        values.put(SQLiteHelper.TPP_FileName, a01_teacherProfilePhoto.getTPP_FileName());
        values.put(SQLiteHelper.TPP_FileServerPath, a01_teacherProfilePhoto.getTPP_FileServerPath());
        values.put(SQLiteHelper.TPP_FileFullPath, a01_teacherProfilePhoto.getTPP_FileFullPath());
        values.put(SQLiteHelper.TPP_IsLast, a01_teacherProfilePhoto.getTPP_IsLast());
        values.put(SQLiteHelper.TPP_ClickedOn, a01_teacherProfilePhoto.getTPP_ClickedOn());
        values.put(SQLiteHelper.TPP_Lattitude, a01_teacherProfilePhoto.getTPP_Lattitude());
        values.put(SQLiteHelper.TPP_Longitude, a01_teacherProfilePhoto.getTPP_Longitude());
        values.put(SQLiteHelper.TPP_PersonID, a01_teacherProfilePhoto.getTPP_PersonID());
        values.put(SQLiteHelper.TP_IsSync, a01_teacherProfilePhoto.getProfile_IsSync());
        values.put(SQLiteHelper.TPP_Embeeding, JsonEmbeedings);
        values.put(SQLiteHelper.TPP_TeacherID, a01_teacherProfilePhoto.getTPP_TeacherID());
        values.put(SQLiteHelper.TPP_EmbeedingBytes, a01_teacherProfilePhoto.getTPP_EmbeedingBytes());
        String where = SQLiteHelper.TPP_TeacherID + "=? ";
        String[] whereArgs = {a01_teacherProfilePhoto.getTPP_TeacherID()};
        i = database.update(SQLiteHelper.tbl_A01_TeacherProfilePhoto, values, where, whereArgs);
        return i;
    }

    @SuppressLint("Range")
    public int count_TeacherProfileVerifiedNew(String schoolID) {
        String sql = "select * from tbl_A01_TeacherList as A01T  inner join tbl_A01_Teacher_profile as  A01TP on A01T.Teacher_ID = A01TP.TP_TeacherID where A01TP.TP_IsVerified not in ('1','2') or A01TP.TP_IsVerified is null and A01T.Teacher_SchoolID='" + schoolID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_TeacherProfileVerifiedVerified(String schoolID) {
        String sql = "select * from tbl_A01_TeacherList as A01T  inner join tbl_A01_Teacher_profile as  A01TP on A01T.Teacher_ID = A01TP.TP_TeacherID where A01TP.TP_IsVerified in ('1') and A01T.Teacher_SchoolID='" + schoolID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int getCount_a05_student_get_count(String student_id) {
        String sql = "select  *  from tbl_A05_Student where Student_ID='" + student_id + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int update_tbl_A05_Student(A05_Student a05_student) {
        int i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Student_ID, a05_student.getStudent_ID());
        values.put(SQLiteHelper.Student_Name, a05_student.getStudent_Name());
        values.put(SQLiteHelper.Student_FatherName, a05_student.getStudent_FatherName());
        values.put(SQLiteHelper.Student_MotherName, a05_student.getStudent_MotherName());
        values.put(SQLiteHelper.Student_UniqueID, a05_student.getStudent_UniqueID());
        values.put(SQLiteHelper.Student_Gender, a05_student.getStudent_Gender());
        values.put(SQLiteHelper.Student_IsActive, a05_student.getStudent_IsActive());
        values.put(SQLiteHelper.Student_SRNO, a05_student.getStudent_SRNO());
        values.put(SQLiteHelper.MorningAttendance, a05_student.getMorningAttendance());
        values.put(SQLiteHelper.EveningAttendance, a05_student.getEveningAttendance());
        String where = SQLiteHelper.Student_ID + "=? ";
        String[] whereArgs = {a05_student.getStudent_ID()};
        i = database.update(SQLiteHelper.tbl_A05_Student, values, where, whereArgs);
        return i;
    }

    public int getCount_a06_student_get_count(String sc_studentID) {
        String sql = "select  *  from tbl_A06_Student  where SC_StudentID='" + sc_studentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int update_tbl_A06_Student(A06_StudentDetails a06_studentDetails) {
        int i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SC_ID, a06_studentDetails.getSC_ID());
        values.put(SQLiteHelper.SC_SchoolID, a06_studentDetails.getSC_SchoolID());
        values.put(SQLiteHelper.SC_ClassID, a06_studentDetails.getSC_ClassID());
        values.put(SQLiteHelper.SC_SectionID, a06_studentDetails.getSC_SectionID());
        values.put(SQLiteHelper.SC_AddedOn, a06_studentDetails.getSC_AddedOn());
        values.put(SQLiteHelper.SC_IsActive, a06_studentDetails.getSC_IsActive());
        values.put(SQLiteHelper.SC_AcademicYearID, a06_studentDetails.getSC_AcademicYearID());
        String where = SQLiteHelper.SC_StudentID + "=? ";
        String[] whereArgs = {a06_studentDetails.getSC_StudentID()};
        i = database.update(SQLiteHelper.tbl_A06_Student, values, where, whereArgs);
        return i;
    }

    public int getCount_tbl_A07_Studentprofile(String sp_studentID) {
        String sql = "select  *  from tbl_A07_Studentprofile where SP_StudentID='" + sp_studentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int update_tbl_A07_Studentprofile(A07_StudentProfile a07_studentProfile) {
        int i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.SP_ID, a07_studentProfile.getSP_ID());
        values.put(SQLiteHelper.SP_SchoolID, a07_studentProfile.getSP_SchoolID());
        values.put(SQLiteHelper.SP_ClassID, a07_studentProfile.getSP_ClassID());
        values.put(SQLiteHelper.SP_SectionID, a07_studentProfile.getSP_SectionID());
        values.put(SQLiteHelper.SP_CreationDate, a07_studentProfile.getSP_CreationDate());
        values.put(SQLiteHelper.SP_Lattitude, a07_studentProfile.getSP_Lattitude());
        values.put(SQLiteHelper.SP_Longitude, a07_studentProfile.getSP_Longitude());
        values.put(SQLiteHelper.SP_IsLast, a07_studentProfile.getSP_IsLast());
        values.put(SQLiteHelper.SP_IsSync, a07_studentProfile.getSP_IsSync());
        String where = SQLiteHelper.SP_StudentID + "=? ";
        String[] whereArgs = {a07_studentProfile.getSP_StudentID()};
        i = database.update(SQLiteHelper.tbl_A07_Studentprofile, values, where, whereArgs);
        return i;
    }

    public int getCount_A08_StudentProfilePhoto(String spp_studentID) {
        String sql = "select  *  from  tbl_A08_StudentProfilePhoto where SPP_StudentID='" + spp_studentID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long update_tbl_A08_StudentProfilePhoto_refresh(A08_StudentProfilePhoto a08_studentProfile) {
        ContentValues values = new ContentValues();
        String JsonEmbeedings = String.valueOf(a08_studentProfile.getSPP_Embeeding());
        values.put(SQLiteHelper.SPP_ID, a08_studentProfile.getSPP_ID());
        values.put(SQLiteHelper.SPP_ProfileID, a08_studentProfile.getSPP_ProfileID());
        values.put(SQLiteHelper.SPP_FileName, a08_studentProfile.getSPP_FileName());
        values.put(SQLiteHelper.SPP_FilePath, a08_studentProfile.getSPP_FilePath());
        values.put(SQLiteHelper.SPP_FileURL, a08_studentProfile.getSPP_FileURL());
        values.put(SQLiteHelper.SPP_ServerFilePath, a08_studentProfile.getSPP_ServerFilePath());
        values.put(SQLiteHelper.SPP_Lattitude, a08_studentProfile.getSPP_Lattitude());
        values.put(SQLiteHelper.SPP_Longitude, a08_studentProfile.getSPP_Longitude());
        values.put(SQLiteHelper.SPP_IsActive, a08_studentProfile.getSPP_IsActive());
        values.put(SQLiteHelper.SP_IsSync, a08_studentProfile.getSP_IsSync());
        values.put(SQLiteHelper.SPP_Embeeding, JsonEmbeedings);
        String where = SQLiteHelper.SPP_StudentID + "=? ";
        String[] whereArgs = {a08_studentProfile.getSPP_StudentID()};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A08_StudentProfilePhoto, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    public long update_tbl_A09_StudentAttendance(A09_StudentAttendance a09_studentAttendance) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Att_SchoolID, a09_studentAttendance.getAtt_SchoolID());
        values.put(SQLiteHelper.Att_StudentID, a09_studentAttendance.getAtt_StudentID());
        values.put(SQLiteHelper.Att_UniqueID, a09_studentAttendance.getAtt_StudentID());
        values.put(SQLiteHelper.Att_AttendaceTypeID, a09_studentAttendance.getAtt_AttendaceTypeID());
        values.put(SQLiteHelper.Att_PresentAbscent, a09_studentAttendance.getAtt_PresentAbscent());
        values.put(SQLiteHelper.Att_AttendanceDateTime, a09_studentAttendance.getAtt_AttendanceDateTime());
        values.put(SQLiteHelper.Att_AddedBy, a09_studentAttendance.getAtt_AddedBy());
        values.put(SQLiteHelper.Att_ClassID, a09_studentAttendance.getAtt_ClassID());
        values.put(SQLiteHelper.Att_SectionID, a09_studentAttendance.getAtt_SectionID());
        values.put(SQLiteHelper.Is_Sync, a09_studentAttendance.getIs_Sync());
        values.put(SQLiteHelper.AddedOn, a09_studentAttendance.getAtt_AddedON());
        String where = SQLiteHelper.Att_StudentID + "=? and " + SQLiteHelper.Att_AttendaceTypeID + "=? and " + SQLiteHelper.AddedOn + "=? ";
        String[] whereArgs = {a09_studentAttendance.getAtt_StudentID(), a09_studentAttendance.getAtt_AttendaceTypeID(), a09_studentAttendance.getAtt_AddedON()};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A09studentattendencedata, values, where, whereArgs);
        return UpdateApprovalStatus;
    }

    public int count_tbl_A09_StudentAttendance(String studentId, String att_AttendaceTypeID, String addedon) {
        String sql = "select  *  from  tbl_A09studentattendencedata where Att_StudentID='" + studentId + "' and Att_AttendaceTypeID='" + att_AttendaceTypeID + "' and AddedOn='" + addedon + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long inserttbl_A01_TeacherPersonList(A01_TeacherPersonList a01_teacher) {
        long isInserted;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Teacher_ID, a01_teacher.getTeacher_ID());
        values.put(SQLiteHelper.Teacher_Name, a01_teacher.getTeacher_Name().toString());
        values.put(SQLiteHelper.Teacher_CurrentSchoolUdise, a01_teacher.getTeacher_CurrentSchoolUdise());
        values.put(SQLiteHelper.Teacher_CurrentMDMSchoolCode, a01_teacher.getTeacher_CurrentMDMSchoolCode());
        values.put(SQLiteHelper.Teacher_IsActive, a01_teacher.getTeacher_IsActive());
        values.put(SQLiteHelper.Teacher_ehrmsCode, a01_teacher.getTeacher_ehrmsCode());
        values.put(SQLiteHelper.Teacher_MobileNo, a01_teacher.getTeacher_MobileNo());
        values.put(SQLiteHelper.Teacher_SchoolID, a01_teacher.getTeacher_SchoolID());
        values.put(SQLiteHelper.Teacher_IsHead, a01_teacher.getTeacher_IsHead());
        values.put(SQLiteHelper.User_Pin, a01_teacher.getUser_Pin());
        values.put(SQLiteHelper.School_DistrictID, a01_teacher.getSchool_DistrictID());
        values.put(SQLiteHelper.TD_DesignationID, a01_teacher.getTD_DesignationID());
        values.put(SQLiteHelper.School_Name, a01_teacher.getSchool_Name());
        values.put(SQLiteHelper.Teacher_Designation, a01_teacher.getTeacher_Designation());
        values.put(SQLiteHelper.School_DistrictName, a01_teacher.getSchool_DistrictName());
        values.put(SQLiteHelper.School_BlockName, a01_teacher.getSchool_BlockName());
        values.put(SQLiteHelper.Designation_Name, a01_teacher.getDesignation_Name());
        isInserted = database.insert(SQLiteHelper.tbl_A01_TeacherList, null, values);
        return isInserted;
    }

    public long delete_A01_TeacherPersonList() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_TeacherList, null, null);
        return i;
    }

    @SuppressLint("Range")
    public String gettbl_A01_TeacherList(String Teacher_ID) {
        String student_ID = "";
        String sql = "select  *  from  tbl_A01_TeacherList  where Teacher_ID='" + Teacher_ID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            student_ID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name));
            cursor.moveToNext();
        }
        return student_ID;
    }

    public long update_ttbl_A01_TeacherPersonList(A01_TeacherPersonList a01_teacher) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Teacher_ID, a01_teacher.getTeacher_ID());
        values.put(SQLiteHelper.Teacher_Name, a01_teacher.getTeacher_Name().toString());
        values.put(SQLiteHelper.Teacher_CurrentSchoolUdise, a01_teacher.getTeacher_CurrentSchoolUdise());
        values.put(SQLiteHelper.Teacher_CurrentMDMSchoolCode, a01_teacher.getTeacher_CurrentMDMSchoolCode());
        values.put(SQLiteHelper.Teacher_IsActive, a01_teacher.getTeacher_IsActive());
        values.put(SQLiteHelper.Teacher_ehrmsCode, a01_teacher.getTeacher_ehrmsCode());
        values.put(SQLiteHelper.Teacher_MobileNo, a01_teacher.getTeacher_MobileNo());
        values.put(SQLiteHelper.Teacher_SchoolID, a01_teacher.getTeacher_SchoolID());
        values.put(SQLiteHelper.Teacher_IsHead, a01_teacher.getTeacher_IsHead());
        values.put(SQLiteHelper.User_Pin, a01_teacher.getUser_Pin());
        values.put(SQLiteHelper.School_DistrictID, a01_teacher.getSchool_DistrictID());
        values.put(SQLiteHelper.TD_DesignationID, a01_teacher.getTD_DesignationID());
        values.put(SQLiteHelper.School_Name, a01_teacher.getSchool_Name());
        values.put(SQLiteHelper.Teacher_Designation, a01_teacher.getTeacher_Designation());
        values.put(SQLiteHelper.School_DistrictName, a01_teacher.getSchool_DistrictName());
        values.put(SQLiteHelper.School_BlockName, a01_teacher.getSchool_BlockName());
        values.put(SQLiteHelper.Designation_Name, a01_teacher.getDesignation_Name());

        String where = SQLiteHelper.Teacher_ID + "=? ";
        String[] whereArgs = {a01_teacher.getTeacher_ID()};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A01_TeacherList, values, where, whereArgs);
        return UpdateApprovalStatus;
    }


    public long count_tbl_A01_TeacherPersonList(String teacherID) {
        String sql = "select  *  from  tbl_A01_TeacherList  where Teacher_ID='" + teacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_tbl_A01_Teacher_profile_ByTeacherID(String teacherID) {
        String sql = "select  *  from tbl_A01_Teacher_profile where TP_TeacherID='" + teacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public long update_tbl_A01_Teacher_profile_TeacherID(A01_TeacherProfile a01_teacherProfile) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.TP_ID, a01_teacherProfile.getTP_ID());
        values.put(SQLiteHelper.TP_TeacherID, a01_teacherProfile.getTP_TeacherID());
        values.put(SQLiteHelper.TP_CreatedOn, a01_teacherProfile.getTP_CreatedOn());
        values.put(SQLiteHelper.TP_VerifiedBy, a01_teacherProfile.getTP_VerifiedBy());
        values.put(SQLiteHelper.TP_IsActive, a01_teacherProfile.getTP_IsActive());
        values.put(SQLiteHelper.TP_IsVerified, a01_teacherProfile.getTP_IsVerified());
        values.put(SQLiteHelper.TP_IsSync, a01_teacherProfile.getTP_IsSync());
        values.put(SQLiteHelper.Teacher_SchoolID, a01_teacherProfile.getTeacher_SchoolID());
        values.put(SQLiteHelper.School_DistrictID, a01_teacherProfile.getSchool_DistrictID());
        values.put(SQLiteHelper.TD_DesignationID, a01_teacherProfile.getTD_DesignationID());
        values.put(SQLiteHelper.School_Name, a01_teacherProfile.getSchool_Name());
        String where = SQLiteHelper.TP_TeacherID + "=? ";
        String[] whereArgs = {a01_teacherProfile.getTP_TeacherID()};
        long UpdateApprovalStatus = database.update(SQLiteHelper.tbl_A01_Teacher_profile, values, where, whereArgs);
        return UpdateApprovalStatus;
    }


    public long updatetbl_A09_TeacherAttendanceData_TecaherID(A09_TeacherAttendance a09_teacherAttendance) {
        long i = 0;
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.Att_ID, a09_teacherAttendance.getAtt_ID());
        values.put(SQLiteHelper.Att_SchoolID, a09_teacherAttendance.getAtt_SchoolID());
        values.put(SQLiteHelper.Att_TeacherID, a09_teacherAttendance.getAtt_TeacherID());
        values.put(SQLiteHelper.Att_AttendaceTypeID, a09_teacherAttendance.getAtt_AttendaceTypeID());
        values.put(SQLiteHelper.Att_PresentAbscent, a09_teacherAttendance.getAtt_PresentAbscent());
        values.put(SQLiteHelper.Att_AttendanceDateTime, a09_teacherAttendance.getAtt_AttendanceDateTime());
        values.put(SQLiteHelper.Att_AddedBy, a09_teacherAttendance.getAtt_AddedBy());
        values.put(SQLiteHelper.Att_ClassID, a09_teacherAttendance.getAtt_ClassID());
        values.put(SQLiteHelper.Att_SectionID, a09_teacherAttendance.getAtt_SectionID());
        values.put(SQLiteHelper.Att_Lattitude, a09_teacherAttendance.getAtt_Lattitude());
        values.put(SQLiteHelper.Att_Longitude, a09_teacherAttendance.getAtt_Longitude());
        values.put(SQLiteHelper.Att_IsLate, a09_teacherAttendance.getAtt_IsLate());
        values.put(SQLiteHelper.Att_LateReasonID, a09_teacherAttendance.getAtt_LateReasonID());
        values.put(SQLiteHelper.Att_AbsentReasonID, a09_teacherAttendance.getAtt_AbsentReasonID());
        values.put(SQLiteHelper.Att_IsActive, a09_teacherAttendance.getAtt_IsActive());
        values.put(SQLiteHelper.Is_ssync, a09_teacherAttendance.getIs_ssync());
        values.put(SQLiteHelper.AddedOn, a09_teacherAttendance.getAtt_AddedON());
        values.put(SQLiteHelper.NTP_ServerTime, a09_teacherAttendance.getNTP_ServerTime());
        values.put(SQLiteHelper.Att_AppVersion, a09_teacherAttendance.getAtt_AppVersion());
        values.put(SQLiteHelper.Att_LocalAppId, a09_teacherAttendance.getAtt_LocalAppId());
        values.put(SQLiteHelper.Att_GpsTime, a09_teacherAttendance.getAtt_GPSTime());
        String where = SQLiteHelper.Att_TeacherID + "=? and " + SQLiteHelper.Att_AttendaceTypeID + "=? and " + SQLiteHelper.AddedOn + "=? ";
        String[] whereArgs = {a09_teacherAttendance.getAtt_TeacherID(), a09_teacherAttendance.getAtt_AttendaceTypeID(), a09_teacherAttendance.getAtt_AddedON()};
        i = database.update(SQLiteHelper.tbl_A09Teacherattendencedata, values, where, whereArgs);
        return i;
    }

    @SuppressLint("Range")
    public String gettbl_A01_TeacherName(String teacherID) {
        String student_ID = "";
        String sql = "select  *  from  tbl_A01_TeacherList  where Teacher_ID='" + teacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            student_ID = cursor.getString(cursor.getColumnIndex(SQLiteHelper.Teacher_Name));
            cursor.moveToNext();
        }
        return student_ID;
    }

    public int count_TeacherAttendancefalse_PS() {
        String sql = "select  *  from  tbl_A09Teacherattendencedata where Is_ssync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_StudentAttendancefalse_PS() {
        String sql = "select  *  from  tbl_A09studentattendencedata where Is_Sync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_Studentfacedata_PS() {
        String sql = "select  *  from  tbl_A07_Studentprofile where SP_IsSync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int count_Teacherfacedata_PS() {
        String sql = "select  *  from tbl_A01_Teacher_profile where TP_IsSync='false'";
        Cursor cursor = database.rawQuery(sql, null);
        return cursor.getCount();
    }

    public int tbl_A09Teacherattendencedata_already_presnt(String studentid, String localdate, String AttendanceType) {
        int a = 0;
        try {
            String sql = "select  *  from  tbl_A09Teacherattendencedata where Att_TeacherID ='" + studentid + "'  and  AddedOn='" + localdate + "' and  Att_AttendaceTypeID='" + AttendanceType + "' ";
            Cursor cursor = database.rawQuery(sql, null);
            a = cursor.getCount();
        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return a;
    }

    public long delete_A01_CapturedFaceDetailsTeacherProfilePhoto_id(String TeacherID) {
        long i = 0;
        String sql = "select  *  from tbl_A01_CapturedFaceDetailsTeacherProfilePhoto where CID_PersonId='" + TeacherID + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.CID_PersonId + "=? ";
            String[] whereArgs = {TeacherID};
            i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsTeacherProfilePhoto, where, whereArgs);
        }
        return i;
    }

    public long delete_tbl_A01_CapturedFaceDetailsStudentProfilePhoto_for_id(String studentId) {
        long i = 0;
        String sql = "select  *  from tbl_A01_CapturedFaceDetailsStudentProfilePhoto where CID_PersonId='" + studentId + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            String where = SQLiteHelper.CID_PersonId + "=? ";
            String[] whereArgs = {studentId};
            i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsStudentProfilePhoto, where, whereArgs);
        }
        return i;
    }

    public long delete_tbl_A01_CapturedFaceDetailsTeacherProfilePhoto() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsTeacherProfilePhoto, null, null);
        return i;
    }

    public long delete_tbl_A01_CapturedFaceDetailsStudentProfilePhoto() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsStudentProfilePhoto, null, null);
        return i;
    }

    public long delete_tbl_A01_CapturedFaceDetailsTeacherAttendance() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsTeacherAttendance, null, null);
        return i;
    }

    public long delete_tbl_A01_CapturedFaceDetailsStudentAttendance() {
        long i;
        i = database.delete(SQLiteHelper.tbl_A01_CapturedFaceDetailsStudentAttendance, null, null);
        return i;
    }

}