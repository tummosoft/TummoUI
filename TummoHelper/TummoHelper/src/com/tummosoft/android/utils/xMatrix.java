package com.tummosoft.android.utils;

import android.graphics.Matrix;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xMatrix")
public class xMatrix extends AbsObjectWrapper<Matrix> {

        public void initialize(final BA ba, String event) {
            setObject(new Matrix());
        }

        public void preTranslate(float dx, float dy) {
            getObject().preTranslate(dx, dy);
        }

        public void postTranslate(float dx, float dy) {
            getObject().postTranslate(dx, dy);
        }

        public void preSkew(float dx, float dy) {
            getObject().preSkew(dx, dy);
        }

        public void mapPoints(float[] values) {
            getObject().mapPoints(values);
        }

        public void mapRadius(float values) {
            getObject().mapRadius(values);
        }

        public void setTranslate(float dx, float dy) {
            getObject().setTranslate(dx, dy);
        }

        public void setRotate(float degree) {
            getObject().setRotate(degree);
        }

        public void setRotate(float sx, float sy) {
            getObject().setScale(sx, sy);
        }

        public void setSinCos(float sinValue, float cosValue) {
            getObject().setSinCos(sinValue, cosValue);
        }

        public void setMatrix(Matrix matrix) {
            getObject().set(matrix);
        }

        public void setMatrix(Matrix matrix1, Matrix matrix2) {
            getObject().setConcat(matrix1, matrix2);
        }

        public void reset() {
            getObject().reset();
        }

        public void mapVectors(float[] vecs) {
            getObject().mapVectors(vecs);
        }

        public boolean isAffine() {
            return getObject().isAffine();
        }

        public boolean isIdentity() {
            return getObject().isIdentity();
        }
    }