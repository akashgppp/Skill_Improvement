package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;
import java.util.List;

public class A05_Student implements Serializable {
    public String Student_ID;
    public String Student_Name;
    public String Student_FatherName;
    public String Student_MotherName;
    public String Student_UniqueID;
    public String Student_Gender;
    public String Student_IsActive;
    public String Student_SRNO ;
    public String MorningAttendance ;
    public String SC_ClassID ;
    public String SC_SectionID ;

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

    public String getAddedOn() {
        return AddedOn;
    }

    public void setAddedOn(String addedOn) {
        AddedOn = addedOn;
    }

    public String AddedOn ;

    public String getMorningAttendance() {
        return MorningAttendance;
    }

    public void setMorningAttendance(String morningAttendance) {
        MorningAttendance = morningAttendance;
    }



    public String getEveningAttendance() {
        return EveningAttendance;
    }

    public void setEveningAttendance(String eveningAttendance) {
        EveningAttendance = eveningAttendance;
    }

    public String EveningAttendance ;
    public A06_StudentDetails objdetails ;

    public String getStudent_ID() {
        return Student_ID;
    }

    public void setStudent_ID(String student_ID) {
        Student_ID = student_ID;
    }

    public String getStudent_Name() {
        return Student_Name;
    }

    public void setStudent_Name(String student_Name) {
        Student_Name = student_Name;
    }

    public String getStudent_FatherName() {
        return Student_FatherName;
    }

    public void setStudent_FatherName(String student_FatherName) {
        Student_FatherName = student_FatherName;
    }

    public String getStudent_MotherName() {
        return Student_MotherName;
    }

    public void setStudent_MotherName(String student_MotherName) {
        Student_MotherName = student_MotherName;
    }

    public String getStudent_UniqueID() {
        return Student_UniqueID;
    }

    public void setStudent_UniqueID(String student_UniqueID) {
        Student_UniqueID = student_UniqueID;
    }

    public String getStudent_Gender() {
        return Student_Gender;
    }

    public void setStudent_Gender(String student_Gender) {
        Student_Gender = student_Gender;
    }

    public String getStudent_IsActive() {
        return Student_IsActive;
    }

    public void setStudent_IsActive(String student_IsActive) {
        Student_IsActive = student_IsActive;
    }

    public String getStudent_SRNO() {
        return Student_SRNO;
    }

    public void setStudent_SRNO(String student_SRNO) {
        Student_SRNO = student_SRNO;
    }

    public A06_StudentDetails getObjdetails() {
        return objdetails;
    }

    public void setObjdetails(A06_StudentDetails objdetails) {
        this.objdetails = objdetails;
    }

    public List<A09_StudentAttendance> objAttendance;

    public List<A09_StudentAttendance> getObjAttendance() {
        return objAttendance;
    }

    public void setObjAttendance(List<A09_StudentAttendance> objAttendance) {
        this.objAttendance = objAttendance;
    }


}
