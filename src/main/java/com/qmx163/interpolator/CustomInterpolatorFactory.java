package com.qmx163.interpolator;

import android.view.animation.Interpolator;

/**
 * 一个自定义的插值器静态工厂
 *
 * Created by 邓靖 on  2017/3/10  11:16
 */
public class CustomInterpolatorFactory {

    private CustomInterpolatorFactory(){}

    public static Interpolator getSpringInterPolator(float factor){
        return new SpringInterpolator(factor);
    }
    public static Interpolator getSpringInterPolator(){
        return new SpringInterpolator();
    }

    public static Interpolator getAnticipateInterpolator(float factor){
        return new AnticipateInterpolator(factor);
    }
    public static Interpolator getAnticipateInterpolator(){
        return new AnticipateInterpolator();
    }

    public static Interpolator getAnticipateOverShootInterpolator(float factor){
        return new AnticipateOverShootInterpolator(factor);
    }
    public static Interpolator getAnticipateOverShootInterpolator(){
        return new AnticipateOverShootInterpolator();
    }

    public static Interpolator getJellyInterpolator(){
        return new JellyInterpolator();
    }


    public static Interpolator getOverShootInterpolator(float factor){
        return new OverShootInterpolator(factor);
    }
    public static Interpolator getOverShootInterpolator(){
        return new OverShootInterpolator();
    }
}
