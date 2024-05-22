package com.skysoftsolution.in.skill_improvement.Datetime;

import java.io.IOException;
import java.util.Locale;

public class InvalidNtpServerResponseException extends IOException {
    public final String property;
    public final float expectedValue;
    public final float actualValue;

    InvalidNtpServerResponseException(String detailMessage) {
        super(detailMessage);
        this.property = "na";
        this.expectedValue = 0F;
        this.actualValue = 0F;
    }

    InvalidNtpServerResponseException(String message, String property, float actualValue, float expectedValue) {
        super(String.format(Locale.getDefault(), message, property, actualValue, expectedValue));
        this.property = property;
        this.actualValue = actualValue;
        this.expectedValue = expectedValue;
    }

}