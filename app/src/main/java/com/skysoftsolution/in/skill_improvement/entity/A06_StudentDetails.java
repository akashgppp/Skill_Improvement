package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A06_StudentDetails implements Serializable {
    public String SC_ID;
    public String SC_SchoolID;
    public String SC_ClassID;
    public String SC_SectionID;
    public String SC_AddedOn;
    public String SC_IsActive;
    public String SC_AcademicYearID;
    public String SC_StudentID;
    public A07_StudentProfile objProfile;

    public String getSC_ID() {
        return SC_ID;
    }

    public void setSC_ID(String SC_ID) {
        this.SC_ID = SC_ID;
    }

    public String getSC_SchoolID() {
        return SC_SchoolID;
    }

    public void setSC_SchoolID(String SC_SchoolID) {
        this.SC_SchoolID = SC_SchoolID;
    }

    public String getSC_ClassID() {
        return SC_ClassID;
    }

    public void setSC_ClassID(String SC_ClassID) {
        this.SC_ClassID = SC_ClassID;
    }

    public String getSC_SectionID() {
        return SC_SectionID;
    }

    public void setSC_SectionID(String SC_SectionID) {
        this.SC_SectionID = SC_SectionID;
    }

    public String getSC_AddedOn() {
        return SC_AddedOn;
    }

    public void setSC_AddedOn(String SC_AddedOn) {
        this.SC_AddedOn = SC_AddedOn;
    }

    public String getSC_IsActive() {
        return SC_IsActive;
    }

    public void setSC_IsActive(String SC_IsActive) {
        this.SC_IsActive = SC_IsActive;
    }

    public String getSC_AcademicYearID() {
        return SC_AcademicYearID;
    }

    public void setSC_AcademicYearID(String SC_AcademicYearID) {
        this.SC_AcademicYearID = SC_AcademicYearID;
    }

    public String getSC_StudentID() {
        return SC_StudentID;
    }

    public void setSC_StudentID(String SC_StudentID) {
        this.SC_StudentID = SC_StudentID;
    }

    public A07_StudentProfile getObjProfile() {
        return objProfile;
    }

    public void setObjProfile(A07_StudentProfile objProfile) {
        this.objProfile = objProfile;
    }
}