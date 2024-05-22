package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A01_TeacherProfilePhoto implements Serializable {
    public String TPP_ID;
    public String TPP_ProfileID;
    public String TPP_FileName;
    public String TPP_FileServerPath;
    public String TPP_FileFullPath;
    public String TPP_IsLast;
    public String TPP_ClickedOn;
    public String TPP_Lattitude;
    public String TPP_Longitude;
    public String TPP_PersonID;
    public String Profile_IsSync;
    private String TPP_Embeeding;
    private String TPP_TeacherID;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private String Title;
    private Integer color;

    public A01_CapturedFaceDetails ImageDetails;

    public A01_CapturedFaceDetails getImageDetails() {
        return ImageDetails;
    }

    public void setImageDetails(A01_CapturedFaceDetails imageDetails) {
        ImageDetails = imageDetails;
    }

    public String getTPP_TeacherID() {
        return TPP_TeacherID;
    }

    public void setTPP_TeacherID(String TPP_TeacherID) {
        this.TPP_TeacherID = TPP_TeacherID;
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

    public String getTPP_Embeeding() {
        return TPP_Embeeding;
    }

    public void setTPP_Embeeding(String TPP_Embeeding) {
        this.TPP_Embeeding = TPP_Embeeding;
    }

    public String getProfile_IsSync() {
        return Profile_IsSync;
    }

    public void setProfile_IsSync(String profile_IsSync) {
        Profile_IsSync = profile_IsSync;
    }


    public String getTPP_ID() {
        return TPP_ID;
    }

    public void setTPP_ID(String TPP_ID) {
        this.TPP_ID = TPP_ID;
    }

    public String getTPP_ProfileID() {
        return TPP_ProfileID;
    }

    public void setTPP_ProfileID(String TPP_ProfileID) {
        this.TPP_ProfileID = TPP_ProfileID;
    }

    public String getTPP_FileName() {
        return TPP_FileName;
    }

    public void setTPP_FileName(String TPP_FileName) {
        this.TPP_FileName = TPP_FileName;
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

    public String getTPP_IsLast() {
        return TPP_IsLast;
    }

    public void setTPP_IsLast(String TPP_IsLast) {
        this.TPP_IsLast = TPP_IsLast;
    }

    public String getTPP_ClickedOn() {
        return TPP_ClickedOn;
    }

    public void setTPP_ClickedOn(String TPP_ClickedOn) {
        this.TPP_ClickedOn = TPP_ClickedOn;
    }

    public String getTPP_Lattitude() {
        return TPP_Lattitude;
    }

    public void setTPP_Lattitude(String TPP_Lattitude) {
        this.TPP_Lattitude = TPP_Lattitude;
    }

    public String getTPP_Longitude() {
        return TPP_Longitude;
    }

    public void setTPP_Longitude(String TPP_Longitude) {
        this.TPP_Longitude = TPP_Longitude;
    }

    public String getTPP_PersonID() {
        return TPP_PersonID;
    }

    public void setTPP_PersonID(String TPP_PersonID) {
        this.TPP_PersonID = TPP_PersonID;
    }

    private byte[] TPP_EmbeedingBytesForAtten;

    public byte[] getTPP_EmbeedingBytesForAtten() {
        return TPP_EmbeedingBytesForAtten;
    }

    public void setTPP_EmbeedingBytesForAtten(byte[] TPP_EmbeedingBytesForAtten) {
        this.TPP_EmbeedingBytesForAtten = TPP_EmbeedingBytesForAtten;
    }

    private String TPP_EmbeedingBytes;

    public String getTPP_EmbeedingBytes() {
        return TPP_EmbeedingBytes;
    }

    public void setTPP_EmbeedingBytes(String TPP_EmbeedingBytes) {
        this.TPP_EmbeedingBytes = TPP_EmbeedingBytes;
    }
}
