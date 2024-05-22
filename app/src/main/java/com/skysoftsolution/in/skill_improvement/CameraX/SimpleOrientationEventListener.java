package com.skysoftsolution.in.skill_improvement.CameraX;

import android.content.Context;
import android.view.OrientationEventListener;

abstract class SimpleOrientationEventListener extends OrientationEventListener {
    public static final int ORIENTATION_PORTRAIT = 0;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT_REVERSE = 2;
    public static final int ORIENTATION_LANDSCAPE_REVERSE = 3;
    private int lastorientation = 0;

    public SimpleOrientationEventListener(Context context) {
        super(context);
    }

    public SimpleOrientationEventListener(Context context, int rate) {
        super(context, rate);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        int curOrientation;
        if (orientation <= 0) {
            curOrientation = 0;
        }

        if (orientation <= 45) {
            curOrientation = ORIENTATION_PORTRAIT;
        } else if (orientation <= 135) {
            curOrientation = ORIENTATION_LANDSCAPE_REVERSE;
        } else if (orientation <= 225) {
            curOrientation = ORIENTATION_PORTRAIT_REVERSE;
        } else if (orientation <= 335) {
            curOrientation = ORIENTATION_LANDSCAPE;
        } else {
            curOrientation = ORIENTATION_PORTRAIT;
        }

        if (curOrientation != lastorientation) {
            onChanged(lastorientation, curOrientation);
        } else {
            onChanged(0, 0);
        }
    }

    public abstract void onChanged(int lastorientation, int orientation);
}