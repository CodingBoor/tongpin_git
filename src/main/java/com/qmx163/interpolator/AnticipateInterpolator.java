package com.qmx163.interpolator;

import android.view.animation.LinearInterpolator;

public class AnticipateInterpolator extends LinearInterpolator {
    private float factor;

    public AnticipateInterpolator() {
        this.factor = 2.0f;
    }
    public AnticipateInterpolator(float factor) {
        this.factor = factor;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) input * input * ((factor + 1) * input - factor);
    }
}
