package com.tummosoft.android.utils;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xInterpolator")
public class xInterpolator extends AbsObjectWrapper<Interpolator> {

    public xInterpolator() {

    }

    public void initialize(final BA ba, String method) {
        method = method.toLowerCase();
        if (method.contains("linear")) {
            setObject(new LinearInterpolator());
        } else if (method.contains("accelerate")) {
            setObject(new AccelerateInterpolator());
        } else if (method.contains("decelerate")) {
            setObject(new DecelerateInterpolator());
        } else if (method.contains("acceleratedecelerate")) {
            setObject(new AccelerateDecelerateInterpolator());
        } else if (method.contains("anticipate")) {
            setObject(new AnticipateInterpolator());
        } else if (method.contains("bounce")) {
            setObject(new BounceInterpolator());
        } else if (method.contains("overshoot")) {
            setObject(new OvershootInterpolator());
        } else if (method.contains("anticipateovershoot")) {
            setObject(new AnticipateOvershootInterpolator());

        }

    }
}
