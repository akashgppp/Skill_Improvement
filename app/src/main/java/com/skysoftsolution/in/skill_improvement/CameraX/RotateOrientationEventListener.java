package com.skysoftsolution.in.skill_improvement.CameraX;

import android.content.Context;

abstract class RotateOrientationEventListener extends SimpleOrientationEventListener {
    public RotateOrientationEventListener(Context context) {
        super(context);
    }

    public RotateOrientationEventListener(Context context, int rate) {
        super(context, rate);
    }

    @Override
    public void onChanged(int lastorientation, int orientation) {
        int startDeg = lastorientation == 0
                ? orientation == 3 ? 360 : 0
                : lastorientation == 1 ? 90
                : lastorientation == 2 ? 180
                : 270;
        int endDeg = orientation == 0
                ? lastorientation == 1 ? 0 : 360
                : orientation == 1 ? 90
                : orientation == 2 ? 180
                : 270;
        onRotateChanged(startDeg, endDeg);
    }

    public abstract void onRotateChanged(int startDeg, int endDeg);
}
