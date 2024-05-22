package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A08_StudentProfilePhoto implements Serializable {
    public String SPP_ID;
    public String SPP_ProfileID;
    public String SPP_FileName;
    public String SPP_FilePath;
    public String SPP_FileURL;
    public String SPP_ServerFilePath;
    public String SPP_Lattitude;
    public String SPP_Longitude;
    private String SPP_Embeeding;
    private float left;
    private float top;
    private float right;
    private float bottom;
    private String Title;
    private Integer color;
    private String SP_IsSync;
    private String LocalApp_Id;
    private String SPP_StudentID;
    private String SP_ClassID;
    private String SP_SectionID;

    public A01_CapturedFaceDetails ImageDetails;

    private byte[] SPP_EmbeedingBytesForAtten;

    public byte[] getSPP_EmbeedingBytesForAtten() {
        return SPP_EmbeedingBytesForAtten;
    }

    public void setSPP_EmbeedingBytesForAtten(byte[] SPP_EmbeedingBytesForAtten) {
        this.SPP_EmbeedingBytesForAtten = SPP_EmbeedingBytesForAtten;
    }

    public A01_CapturedFaceDetails getImageDetails() {
        return ImageDetails;
    }

    public void setImageDetails(A01_CapturedFaceDetails imageDetails) {
        ImageDetails = imageDetails;
    }

    public String getSP_ClassID() {
        return SP_ClassID;
    }

    public void setSP_ClassID(String SP_ClassID) {
        this.SP_ClassID = SP_ClassID;
    }

    public String getSP_SectionID() {
        return SP_SectionID;
    }

    public void setSP_SectionID(String SP_SectionID) {
        this.SP_SectionID = SP_SectionID;
    }

    public String getSPP_StudentID() {
        return SPP_StudentID;
    }

    public void setSPP_StudentID(String SPP_StudentID) {
        this.SPP_StudentID = SPP_StudentID;
    }

    public String getLocalApp_Id() {
        return LocalApp_Id;
    }

    public void setLocalApp_Id(String localApp_Id) {
        LocalApp_Id = localApp_Id;
    }

    public String getSP_IsSync() {
        return SP_IsSync;
    }

    public void setSP_IsSync(String SP_IsSync) {
        this.SP_IsSync = SP_IsSync;
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

    public String getSPP_Embeeding() {
        return SPP_Embeeding;
    }

    public void setSPP_Embeeding(String SPP_Embeeding) {
        this.SPP_Embeeding = SPP_Embeeding;
    }

    /* public ArrayList<Float> getSPP_Embeeding() {
            return SPP_Embeeding;
        }

        public void setSPP_Embeeding(ArrayList<Float> SPP_Embeeding) {
            this.SPP_Embeeding = SPP_Embeeding;
        }
    */
    public String SPP_IsActive;

    /*public ArrayList<Float> SPP_Embeeding = new ArrayList<>();
     */
    public String getSPP_ID() {
        return SPP_ID;
    }

    public void setSPP_ID(String SPP_ID) {
        this.SPP_ID = SPP_ID;
    }

    public String getSPP_ProfileID() {
        return SPP_ProfileID;
    }

    public void setSPP_ProfileID(String SPP_ProfileID) {
        this.SPP_ProfileID = SPP_ProfileID;
    }

    public String getSPP_FileName() {
        return SPP_FileName;
    }

    public void setSPP_FileName(String SPP_FileName) {
        this.SPP_FileName = SPP_FileName;
    }

    public String getSPP_FilePath() {
        return SPP_FilePath;
    }

    public void setSPP_FilePath(String SPP_FilePath) {
        this.SPP_FilePath = SPP_FilePath;
    }

    public String getSPP_FileURL() {
        return SPP_FileURL;
    }

    public void setSPP_FileURL(String SPP_FileURL) {
        this.SPP_FileURL = SPP_FileURL;
    }

    public String getSPP_ServerFilePath() {
        return SPP_ServerFilePath;
    }

    public void setSPP_ServerFilePath(String SPP_ServerFilePath) {
        this.SPP_ServerFilePath = SPP_ServerFilePath;
    }

    public String getSPP_Lattitude() {
        return SPP_Lattitude;
    }

    public void setSPP_Lattitude(String SPP_Lattitude) {
        this.SPP_Lattitude = SPP_Lattitude;
    }

    public String getSPP_Longitude() {
        return SPP_Longitude;
    }

    public void setSPP_Longitude(String SPP_Longitude) {
        this.SPP_Longitude = SPP_Longitude;
    }

    public String getSPP_IsActive() {
        return SPP_IsActive;
    }

    public void setSPP_IsActive(String SPP_IsActive) {
        this.SPP_IsActive = SPP_IsActive;
    }


    // private byte[] SPP_EmbeedingBytes;
    private String SPP_EmbeedingBytes;

    public String getSPP_EmbeedingBytes() {
        return SPP_EmbeedingBytes;
    }

    public void setSPP_EmbeedingBytes(String SPP_EmbeedingBytes) {
        this.SPP_EmbeedingBytes = SPP_EmbeedingBytes;
    }
}

