package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A09_StudentAttendance implements Serializable {
    private String Att_SchoolID;
    private String Att_StudentID;
    private String Att_UniqueID;
    private String Att_AttendaceTypeID;
    private String Att_PresentAbscent;
    private String Att_AttendanceDateTime;
    private String Att_AddedBy;
    private String Att_ClassID;
    private String Att_SectionID;
    private String Att_Lattitude;
    private String Att_Longitude;
    private String Att_AbsentReasonID;
    private String ResultString;
    private String Is_Sync;
    private String NtpServerTime;
    private String Att_AddedON;
    private String Att_AppVersion;
    private String Att_LocalAppId;
    private String Att_GPSTime;
    public String Student_Name;

    public A01_CapturedFaceDetails ImageDetails;

    public A01_CapturedFaceDetails getImageDetails() {
        return ImageDetails;
    }

    public void setImageDetails(A01_CapturedFaceDetails imageDetails) {
        ImageDetails = imageDetails;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getAtt_AppVersion() {
        return Att_AppVersion;
    }

    public void setAtt_AppVersion(String att_AppVersion) {
        Att_AppVersion = att_AppVersion;
    }

    public String getAtt_LocalAppId() {
        return Att_LocalAppId;
    }

    public void setAtt_LocalAppId(String att_LocalAppId) {
        Att_LocalAppId = att_LocalAppId;
    }



    public String getNtpServerTime() {
        return NtpServerTime;
    }

    public void setNtpServerTime(String ntpServerTime) {
        NtpServerTime = ntpServerTime;
    }


    public String getAtt_AddedON() {
        return Att_AddedON;
    }

    public void setAtt_AddedON(String att_AddedON) {
        Att_AddedON = att_AddedON;
    }

    public String getAtt_GPSTime() {
        return Att_GPSTime;
    }

    public void setAtt_GPSTime(String att_GPSTime) {
        Att_GPSTime = att_GPSTime;
    }

    public String getIs_Sync() {
        return Is_Sync;
    }

    public void setIs_Sync(String is_Sync) {
        Is_Sync = is_Sync;
    }


    public String getAtt_SchoolID() {
        return Att_SchoolID;
    }

    public void setAtt_SchoolID(String att_SchoolID) {
        Att_SchoolID = att_SchoolID;
    }

    public String getAtt_StudentID() {
        return Att_StudentID;
    }

    public void setAtt_StudentID(String att_StudentID) {
        Att_StudentID = att_StudentID;
    }

    public String getAtt_UniqueID() {
        return Att_UniqueID;
    }

    public void setAtt_UniqueID(String att_UniqueID) {
        Att_UniqueID = att_UniqueID;
    }

    public String getAtt_AttendaceTypeID() {
        return Att_AttendaceTypeID;
    }

    public void setAtt_AttendaceTypeID(String att_AttendaceTypeID) {
        Att_AttendaceTypeID = att_AttendaceTypeID;
    }

    public String getAtt_PresentAbscent() {
        return Att_PresentAbscent;
    }

    public void setAtt_PresentAbscent(String att_PresentAbscent) {
        Att_PresentAbscent = att_PresentAbscent;
    }

    public String getAtt_AttendanceDateTime() {
        return Att_AttendanceDateTime;
    }

    public void setAtt_AttendanceDateTime(String att_AttendanceDateTime) {
        Att_AttendanceDateTime = att_AttendanceDateTime;
    }

    public String getAtt_AddedBy() {
        return Att_AddedBy;
    }

    public void setAtt_AddedBy(String att_AddedBy) {
        Att_AddedBy = att_AddedBy;
    }

    public String getAtt_ClassID() {
        return Att_ClassID;
    }

    public void setAtt_ClassID(String att_ClassID) {
        Att_ClassID = att_ClassID;
    }

    public String getAtt_SectionID() {
        return Att_SectionID;
    }

    public void setAtt_SectionID(String att_SectionID) {
        Att_SectionID = att_SectionID;
    }

    public String getAtt_Lattitude() {
        return Att_Lattitude;
    }

    public void setAtt_Lattitude(String att_Lattitude) {
        Att_Lattitude = att_Lattitude;
    }

    public String getAtt_Longitude() {
        return Att_Longitude;
    }

    public void setAtt_Longitude(String att_Longitude) {
        Att_Longitude = att_Longitude;
    }

    public String getAtt_AbsentReasonID() {
        return Att_AbsentReasonID;
    }

    public void setAtt_AbsentReasonID(String att_AbsentReasonID) {
        Att_AbsentReasonID = att_AbsentReasonID;
    }

    public String getResultString() {
        return ResultString;
    }

    public void setResultString(String resultString) {
        ResultString = resultString;
    }
}

