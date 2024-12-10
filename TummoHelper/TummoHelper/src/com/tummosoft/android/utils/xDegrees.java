package com.tummosoft.android.utils;

import anywheresoftware.b4a.BA;

@BA.ShortName("xDegrees")
public class xDegrees {

    private int mFromDegrees;
    private int mtoDegrees;
    private int mcenterX1;
    private float mcenterX2;
    private int mcenterY1;
    private float mcenterY2;

    public int getFromDegrees() {
        return mFromDegrees;
    }

    public void setFromDegrees(int mFromDegrees) {
        this.mFromDegrees = mFromDegrees;
    }

    public int getToDegrees() {
        return mtoDegrees;
    }

    public void setToDegrees(int mtoDegrees) {
        this.mtoDegrees = mtoDegrees;
    }

    public int getCenterX1() {
        return mcenterX1;
    }

    public void setCenterX1(int mcenterX1) {
        this.mcenterX1 = mcenterX1;
    }

    public float getCenterX2() {
        return mcenterX2;
    }

    public void setCenterX2(float mcenterX2) {
        this.mcenterX2 = mcenterX2;
    }

    public int getCenterY1() {
        return mcenterY1;
    }

    public void setCenterY1(int mcenterY1) {
        this.mcenterY1 = mcenterY1;
    }

    public float getCenterY2() {
        return mcenterY2;
    }

    public void setCenterY2(float mcenterY2) {
        this.mcenterY2 = mcenterY2;
    }

    public xDegrees(int FromDegrees, int toDegrees, int centerX1, float centerX2, int centerY1, float centerY2) {
        mFromDegrees = FromDegrees;
        mtoDegrees = toDegrees;
        mcenterX1 = centerX1;
        mcenterX2 = centerX2;
        mcenterY1 = centerY1;
        mcenterY2 = centerY2;
    }

    public xDegrees() {

    }

    public void initialize(int FromDegrees, int toDegrees, int centerX1, float centerX2, int centerY1, float centerY2) {
        mFromDegrees = FromDegrees;
        mtoDegrees = toDegrees;
        mcenterX1 = centerX1;
        mcenterX2 = centerX2;
        mcenterY1 = centerY1;
        mcenterY2 = centerY2;
    }
}
