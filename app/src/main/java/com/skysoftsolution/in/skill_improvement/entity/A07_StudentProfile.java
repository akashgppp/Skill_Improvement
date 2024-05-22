package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;
import java.util.List;

public class A07_StudentProfile implements Serializable {
    public String SP_ID;
    public String SP_SchoolID;
    public String SP_StudentID;
    public String SP_ClassID;
    public String SP_SectionID;
    public String SP_CreationDate;
    public String SP_Lattitude;
    public String SP_Longitude;
    public String SP_IsLast;
    private String SP_IsSync;

    public List<A08_StudentProfilePhoto> objPhoto;

    public String getSP_IsSync() {
        return SP_IsSync;
    }

    public void setSP_IsSync(String SP_IsSync) {
        this.SP_IsSync = SP_IsSync;
    }

    public String getSP_ID() {
        return SP_ID;
    }

    public void setSP_ID(String SP_ID) {
        this.SP_ID = SP_ID;
    }

    public String getSP_SchoolID() {
        return SP_SchoolID;
    }

    public void setSP_SchoolID(String SP_SchoolID) {
        this.SP_SchoolID = SP_SchoolID;
    }

    public String getSP_StudentID() {
        return SP_StudentID;
    }

    public void setSP_StudentID(String SP_StudentID) {
        this.SP_StudentID = SP_StudentID;
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

    public String getSP_CreationDate() {
        return SP_CreationDate;
    }

    public void setSP_CreationDate(String SP_CreationDate) {
        this.SP_CreationDate = SP_CreationDate;
    }

    public String getSP_Lattitude() {
        return SP_Lattitude;
    }

    public void setSP_Lattitude(String SP_Lattitude) {
        this.SP_Lattitude = SP_Lattitude;
    }

    public String getSP_Longitude() {
        return SP_Longitude;
    }

    public void setSP_Longitude(String SP_Longitude) {
        this.SP_Longitude = SP_Longitude;
    }

    public String getSP_IsLast() {
        return SP_IsLast;
    }

    public void setSP_IsLast(String SP_IsLast) {
        this.SP_IsLast = SP_IsLast;
    }

    public List<A08_StudentProfilePhoto> getObjPhoto() {
        return objPhoto;
    }

    public void setObjPhoto(List<A08_StudentProfilePhoto> objPhoto) {
        this.objPhoto = objPhoto;
    }


}

