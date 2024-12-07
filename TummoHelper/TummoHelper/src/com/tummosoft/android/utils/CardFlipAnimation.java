package com.tummosoft.android.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.graphics.Camera;
import android.graphics.Matrix;

public class CardFlipAnimation extends Animation {
    private float centerX;
    private float centerY;
    private boolean isReversed;
    private Camera camera;

    public CardFlipAnimation(float centerX, float centerY, boolean isReversed) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.isReversed = isReversed;
        this.camera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float degrees = 180 * interpolatedTime;
        final Matrix matrix = t.getMatrix();

        camera.save();
        if (isReversed) {
            camera.rotateY(-degrees);
        } else {
            camera.rotateY(degrees);
        }
        camera.rotateY(180);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        
    }
}