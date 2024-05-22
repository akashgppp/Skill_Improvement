package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;
import java.util.List;

public class A01_Teacher implements Serializable {
    private String Teacher_ID;
    private String Teacher_Name;
    private String Teacher_CurrentSchoolUdise;
    private String Teacher_CurrentMDMSchoolCode;
    private String Teacher_IsActive;
    private String Teacher_ehrmsCode;
    private String Teacher_MobileNo;
    private String Teacher_SchoolID;
    private String Teacher_IsHead;
    private String User_Pin;
    private String TP_IsVerified;
    private String TP_IsSync;
    private String TPP_FileServerPath;
    private String TPP_FileFullPath;
    private String TPP_Embeeding;

    private String School_DistrictID;
    private String TD_DesignationID;
    private String School_Name;

    private String Teacher_Designation;
    private String School_DistrictName;
    private String School_BlockName;
    public A01_TeacherProfile Profile;
    public String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<A09_TeacherAttendance> objTeacherAttendance;

    public A01_TeacherProfile getProfile() {
        return Profile;
    }

    public void setProfile(A01_TeacherProfile profile) {
        Profile = profile;
    }

    public List<A09_TeacherAttendance> getObjTeacherAttendance() {
        return objTeacherAttendance;
    }

    public void setObjTeacherAttendance(List<A09_TeacherAttendance> objTeacherAttendance) {
        this.objTeacherAttendance = objTeacherAttendance;
    }

    public String getTeacher_Designation() {
        return Teacher_Designation;
    }

    public void setTeacher_Designation(String teacher_Designation) {
        Teacher_Designation = teacher_Designation;
    }

    public String getSchool_DistrictName() {
        return School_DistrictName;
    }

    public void setSchool_DistrictName(String school_DistrictName) {
        School_DistrictName = school_DistrictName;
    }

    public String getSchool_BlockName() {
        return School_BlockName;
    }

    public void setSchool_BlockName(String school_BlockName) {
        School_BlockName = school_BlockName;
    }

    public String getSchool_DistrictID() {
        return School_DistrictID;
    }

    public void setSchool_DistrictID(String school_DistrictID) {
        School_DistrictID = school_DistrictID;
    }

    public String getTD_DesignationID() {
        return TD_DesignationID;
    }

    public void setTD_DesignationID(String TD_DesignationID) {
        this.TD_DesignationID = TD_DesignationID;
    }

    public String getSchool_Name() {
        return School_Name;
    }

    public void setSchool_Name(String school_Name) {
        School_Name = school_Name;
    }

    public String getTP_IsSync() {
        return TP_IsSync;
    }

    public void setTP_IsSync(String TP_IsSync) {
        this.TP_IsSync = TP_IsSync;
    }

    public String getTP_IsVerified() {
        return TP_IsVerified;
    }

    public void setTP_IsVerified(String TP_IsVerified) {
        this.TP_IsVerified = TP_IsVerified;
    }

    public String getTPP_FileServerPath() {
        return TPP_FileServerPath;
    }

    public void setTPP_FileServerPath(String TPP_FileServerPath) {
        this.TPP_FileServerPath = TPP_FileServerPath;
    }

    public String getTPP_FileFullPath() {
        return TPP_FileFullPath;
    }

    public void setTPP_FileFullPath(String TPP_FileFullPath) {
        this.TPP_FileFullPath = TPP_FileFullPath;
    }

    public String getTPP_Embeeding() {
        return TPP_Embeeding;
    }

    public void setTPP_Embeeding(String TPP_Embeeding) {
        this.TPP_Embeeding = TPP_Embeeding;
    }

    public String getUser_Pin() {
        return User_Pin;
    }

    public void setUser_Pin(String user_Pin) {
        User_Pin = user_Pin;
    }

    public String getTeacher_ID() {
        return Teacher_ID;
    }

    public void setTeacher_ID(String teacher_ID) {
        Teacher_ID = teacher_ID;
    }

    public String getTeacher_Name() {
        return Teacher_Name;
    }

    public void setTeacher_Name(String teacher_Name) {
        Teacher_Name = teacher_Name;
    }

    public String getTeacher_CurrentSchoolUdise() {
        return Teacher_CurrentSchoolUdise;
    }

    public void setTeacher_CurrentSchoolUdise(String teacher_CurrentSchoolUdise) {
        Teacher_CurrentSchoolUdise = teacher_CurrentSchoolUdise;
    }

    public String getTeacher_CurrentMDMSchoolCode() {
        return Teacher_CurrentMDMSchoolCode;
    }

    public void setTeacher_CurrentMDMSchoolCode(String teacher_CurrentMDMSchoolCode) {
        Teacher_CurrentMDMSchoolCode = teacher_CurrentMDMSchoolCode;
    }

    public String getTeacher_IsActive() {
        return Teacher_IsActive;
    }

    public void setTeacher_IsActive(String teacher_IsActive) {
        Teacher_IsActive = teacher_IsActive;
    }

    public String getTeacher_ehrmsCode() {
        return Teacher_ehrmsCode;
    }

    public void setTeacher_ehrmsCode(String teacher_ehrmsCode) {
        Teacher_ehrmsCode = teacher_ehrmsCode;
    }

    public String getTeacher_MobileNo() {
        return Teacher_MobileNo;
    }

    public void setTeacher_MobileNo(String teacher_MobileNo) {
        Teacher_MobileNo = teacher_MobileNo;
    }

    public String getTeacher_SchoolID() {
        return Teacher_SchoolID;
    }

    public void setTeacher_SchoolID(String teacher_SchoolID) {
        Teacher_SchoolID = teacher_SchoolID;
    }

    public String getTeacher_IsHead() {
        return Teacher_IsHead;
    }

    public void setTeacher_IsHead(String teacher_IsHead) {
        Teacher_IsHead = teacher_IsHead;
    }
}

