package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A09_TeacherAttendance implements Serializable {
    private String Att_ID;
    private String Att_SchoolID;
    private String Att_TeacherID;
    private String Att_AttendaceTypeID;
    private String Att_PresentAbscent;
    private String Att_AttendanceDateTime;
    private String Att_AddedBy;
    private String Att_ClassID;
    private String Att_SectionID;
    private String Att_Lattitude;
    private String Att_Longitude;
    private String Att_IsLate;
    private String Att_LateReasonID;
    private String Att_AbsentReasonID;
    private String Att_IsActive;
    private String Is_ssync;
    private String Att_TeacherName;

    private String Att_AddedON;
    private String NTP_ServerTime;

    private String Att_AppVersion;
    private String Att_LocalAppId;
    private String Att_GPSTime;

    public A01_CapturedFaceDetails ImageDetails;


    public A01_CapturedFaceDetails getImageDetails() {
        return ImageDetails;
    }

    public void setImageDetails(A01_CapturedFaceDetails imageDetails) {
        ImageDetails = imageDetails;
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

    public String getAtt_TeacherName() {
        return Att_TeacherName;
    }

    public void setAtt_TeacherName(String att_TeacherName) {
        Att_TeacherName = att_TeacherName;
    }

    public String getNTP_ServerTime() {
        return NTP_ServerTime;
    }

    public void setNTP_ServerTime(String NTP_ServerTime) {
        this.NTP_ServerTime = NTP_ServerTime;
    }

    public String getIs_ssync() {
        return Is_ssync;
    }

    public void setIs_ssync(String is_ssync) {
        Is_ssync = is_ssync;
    }

    private String ResultString;

    public String getAtt_ID() {
        return Att_ID;
    }

    public void setAtt_ID(String att_ID) {
        Att_ID = att_ID;
    }

    public String getAtt_SchoolID() {
        return Att_SchoolID;
    }

    public void setAtt_SchoolID(String att_SchoolID) {
        Att_SchoolID = att_SchoolID;
    }

    public String getAtt_TeacherID() {
        return Att_TeacherID;
    }

    public void setAtt_TeacherID(String att_TeacherID) {
        Att_TeacherID = att_TeacherID;
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

    public String getAtt_IsLate() {
        return Att_IsLate;
    }

    public void setAtt_IsLate(String att_IsLate) {
        Att_IsLate = att_IsLate;
    }

    public String getAtt_LateReasonID() {
        return Att_LateReasonID;
    }

    public void setAtt_LateReasonID(String att_LateReasonID) {
        Att_LateReasonID = att_LateReasonID;
    }

    public String getAtt_AbsentReasonID() {
        return Att_AbsentReasonID;
    }

    public void setAtt_AbsentReasonID(String att_AbsentReasonID) {
        Att_AbsentReasonID = att_AbsentReasonID;
    }

    public String getAtt_IsActive() {
        return Att_IsActive;
    }

    public void setAtt_IsActive(String att_IsActive) {
        Att_IsActive = att_IsActive;
    }

    public String getResultString() {
        return ResultString;
    }

    public void setResultString(String resultString) {
        ResultString = resultString;
    }
}

