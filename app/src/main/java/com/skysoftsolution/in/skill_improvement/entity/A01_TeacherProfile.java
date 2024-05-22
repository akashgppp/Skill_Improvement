package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;
import java.util.List;

public class A01_TeacherProfile implements Serializable {
    public String TP_ID;
    public String TP_TeacherID;
    public String TP_CreatedOn;
    public String TP_VerifiedBy;
    public String TP_IsVerified;
    public String TP_IsActive;
    public String TP_IsSync;
    public float left;
    public float top;
    public float right;
    public float bottom;
    private String Title;
    private Integer color;
    private String Teacher_SchoolID;
    private String School_DistrictID;
    private String TD_DesignationID;
    private String School_Name;
    public List<A01_TeacherProfilePhoto> objPhoto;

    public String getTeacher_SchoolID() {
        return Teacher_SchoolID;
    }

    public void setTeacher_SchoolID(String teacher_SchoolID) {
        Teacher_SchoolID = teacher_SchoolID;
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

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }


    public String getTP_ID() {
        return TP_ID;
    }

    public void setTP_ID(String TP_ID) {
        this.TP_ID = TP_ID;
    }

    public String getTP_TeacherID() {
        return TP_TeacherID;
    }

    public void setTP_TeacherID(String TP_TeacherID) {
        this.TP_TeacherID = TP_TeacherID;
    }

    public String getTP_CreatedOn() {
        return TP_CreatedOn;
    }

    public void setTP_CreatedOn(String TP_CreatedOn) {
        this.TP_CreatedOn = TP_CreatedOn;
    }

    public String getTP_VerifiedBy() {
        return TP_VerifiedBy;
    }

    public void setTP_VerifiedBy(String TP_VerifiedBy) {
        this.TP_VerifiedBy = TP_VerifiedBy;
    }

    public String getTP_IsVerified() {
        return TP_IsVerified;
    }

    public void setTP_IsVerified(String TP_IsVerified) {
        this.TP_IsVerified = TP_IsVerified;
    }

    public String getTP_IsActive() {
        return TP_IsActive;
    }

    public void setTP_IsActive(String TP_IsActive) {
        this.TP_IsActive = TP_IsActive;
    }

    public List<A01_TeacherProfilePhoto> getObjPhoto() {
        return objPhoto;
    }

    public void setObjPhoto(List<A01_TeacherProfilePhoto> objPhoto) {
        this.objPhoto = objPhoto;
    }
}

