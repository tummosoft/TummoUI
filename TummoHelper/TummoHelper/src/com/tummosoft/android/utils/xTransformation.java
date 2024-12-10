package com.tummosoft.android.utils;

import android.graphics.Matrix;
import android.view.animation.Transformation;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xTransformation")
public class xTransformation extends AbsObjectWrapper<Transformation> {

    public void initialize(final BA ba, String event) {
        setObject(new Transformation());
    }

    public void Clear() {
        getObject().clear();
    }

    public float getAlpha() {
        return getObject().getAlpha();
    }

    public Matrix getMatrix() {
        return getObject().getMatrix();
    }

    public int getTransformationType() {
        return getObject().getTransformationType();
    }

    public void setTransformationType(int value) {
        getObject().setTransformationType(value);
    }
}
