package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class A01_CapturedFaceDetails implements Serializable {
    private String CID_ID;
    private String CID_TPPID;
    private String CID_PersonId;
    private String CID_FaceLiveness;
    private String CID_FaceQuality;
    private String CID_FaceLuminance;
    private String CID_PredictedAge;
    private String CID_PredictedGender;
    private String CID_EyeOpenClose;
    private String CID_MouthOpenClose;
    private String CID_YawThreshold;
    private String CID_PitchThreshold;
    private String CID_RollThreshold;
    private String CID_OcculsionThreshold;

    private String Att_AddedON;

    private String Att_AttendaceTypeID;

    public String getAtt_AddedON() {
        return Att_AddedON;
    }

    public void setAtt_AddedON(String att_AddedON) {
        Att_AddedON = att_AddedON;
    }

    public String getAtt_AttendaceTypeID() {
        return Att_AttendaceTypeID;
    }

    public void setAtt_AttendaceTypeID(String att_AttendaceTypeID) {
        Att_AttendaceTypeID = att_AttendaceTypeID;
    }

    public String getCID_PersonId() {
        return CID_PersonId;
    }

    public void setCID_PersonId(String CID_PersonId) {
        this.CID_PersonId = CID_PersonId;
    }

    public String getCID_ID() {
        return CID_ID;
    }

    public void setCID_ID(String CID_ID) {
        this.CID_ID = CID_ID;
    }

    public String getCID_TPPID() {
        return CID_TPPID;
    }

    public void setCID_TPPID(String CID_TPPID) {
        this.CID_TPPID = CID_TPPID;
    }

    public String getCID_FaceLiveness() {
        return CID_FaceLiveness;
    }

    public void setCID_FaceLiveness(String CID_FaceLiveness) {
        this.CID_FaceLiveness = CID_FaceLiveness;
    }

    public String getCID_FaceQuality() {
        return CID_FaceQuality;
    }

    public void setCID_FaceQuality(String CID_FaceQuality) {
        this.CID_FaceQuality = CID_FaceQuality;
    }

    public String getCID_FaceLuminance() {
        return CID_FaceLuminance;
    }

    public void setCID_FaceLuminance(String CID_FaceLuminance) {
        this.CID_FaceLuminance = CID_FaceLuminance;
    }

    public String getCID_PredictedAge() {
        return CID_PredictedAge;
    }

    public void setCID_PredictedAge(String CID_PredictedAge) {
        this.CID_PredictedAge = CID_PredictedAge;
    }

    public String getCID_PredictedGender() {
        return CID_PredictedGender;
    }

    public void setCID_PredictedGender(String CID_PredictedGender) {
        this.CID_PredictedGender = CID_PredictedGender;
    }

    public String getCID_EyeOpenClose() {
        return CID_EyeOpenClose;
    }

    public void setCID_EyeOpenClose(String CID_EyeOpenClose) {
        this.CID_EyeOpenClose = CID_EyeOpenClose;
    }

    public String getCID_MouthOpenClose() {
        return CID_MouthOpenClose;
    }

    public void setCID_MouthOpenClose(String CID_MouthOpenClose) {
        this.CID_MouthOpenClose = CID_MouthOpenClose;
    }

    public String getCID_YawThreshold() {
        return CID_YawThreshold;
    }

    public void setCID_YawThreshold(String CID_YawThreshold) {
        this.CID_YawThreshold = CID_YawThreshold;
    }

    public String getCID_PitchThreshold() {
        return CID_PitchThreshold;
    }

    public void setCID_PitchThreshold(String CID_PitchThreshold) {
        this.CID_PitchThreshold = CID_PitchThreshold;
    }

    public String getCID_RollThreshold() {
        return CID_RollThreshold;
    }

    public void setCID_RollThreshold(String CID_RollThreshold) {
        this.CID_RollThreshold = CID_RollThreshold;
    }

    public String getCID_OcculsionThreshold() {
        return CID_OcculsionThreshold;
    }

    public void setCID_OcculsionThreshold(String CID_OcculsionThreshold) {
        this.CID_OcculsionThreshold = CID_OcculsionThreshold;
    }
}

