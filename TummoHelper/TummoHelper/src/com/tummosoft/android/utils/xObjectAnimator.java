package com.tummosoft.android.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;

@BA.ShortName("xObjectAnimator")
@BA.Events(values = {"onAnimationStart(anmtr as xAnimator)", "onAnimationEnd(anmtr as xAnimator)", "onAnimationCancel(anmtr as xAnimator)", "onAnimationRepeat(anmtr as xAnimator)"})
public class xObjectAnimator extends AbsObjectWrapper<ObjectAnimator> {

    private Context baContext;
    private String _eventName;
    private BA _ba;

    public void initialize(final BA ba, String event) {
        _ba = ba;
        _eventName = event.toLowerCase();

        setObject(new ObjectAnimator());
        getObject().addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator anmtr) {
                xAnimator anmtr1 = new xAnimator();
                anmtr1.setObject(anmtr);
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationstart", anmtr1);
            }

            @Override
            public void onAnimationEnd(Animator anmtr) {
                xAnimator anmtr1 = new xAnimator();
                anmtr1.setObject(anmtr);
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationend", anmtr1);
            }

            @Override
            public void onAnimationCancel(Animator anmtr) {
                xAnimator anmtr1 = new xAnimator();
                anmtr1.setObject(anmtr);
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationcancel", anmtr1);
            }

            @Override
            public void onAnimationRepeat(Animator anmtr) {
                xAnimator anmtr1 = new xAnimator();
                anmtr1.setObject(anmtr);
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationrepeat", anmtr1);
            }
        });
    }

    public void setAutoCancel(boolean value) {
        getObject().setAutoCancel(value);
    }

    public void setInterpolator(xInterpolator value) {
        getObject().setInterpolator(value.getObject());
    }

    public void setDuration(long value) {
        getObject().setDuration(value);
    }

    public void setCurrentFraction(float fraction) {
        getObject().setCurrentFraction(fraction);
    }

    public void setCurrentPlayTime(long currentPlayTime) {
        getObject().setCurrentPlayTime(currentPlayTime);
    }

    public ObjectAnimator getObjectAnimator() {
        return getObject();
    }

    public void Start() {
        getObject().start();
    }
}
