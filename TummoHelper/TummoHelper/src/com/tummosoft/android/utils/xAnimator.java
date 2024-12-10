package com.tummosoft.android.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xAnimator")
public class xAnimator extends AbsObjectWrapper<Animator> {
     private Context baContext;
    private String _eventName;
    private BA _ba;

    public void initialize(final BA ba, String event) {
        _ba = ba;
        _eventName = event.toLowerCase();        
    }
    
    public void setInterpolator(xInterpolator value) {
        getObject().setInterpolator(value.getObject());
    }

    public void setDuration(long value) {
        getObject().setDuration(value);
    }
    
    public void Start() {
        getObject().start();
    }
}
