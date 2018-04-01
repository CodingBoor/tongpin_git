package com.qmx163.interpolator;

import android.view.animation.LinearInterpolator;

public class JellyInterpolator extends LinearInterpolator {
    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float x) {
        //return (float) (Math.pow(2, -10 * x) * Math.sin((x - factor / 4) * (2 * Math.PI) / factor) + 1);

        if (x < 0.3535) {
            return bounce(x);
        } else if (x < 0.7408) {
            return bounce(x - (float) 0.54719) + (float) 0.7;
        } else if (x < 0.9644) {
            return bounce(x - (float) 0.8526) + (float) 0.9;
        } else
            return bounce(x - (float) 1.0435) + (float) 0.95;
    }


    private float bounce(float t) {
        return t * t * 8;
    }

}
