package com.tummosoft.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandViewWidthAnim extends Animation {
    private View view;
    private int width;
    private int diffWidth;

    public ExpandViewWidthAnim(View view, int prevWidth, int toWidth) {
        this.view = view;
        this.width = prevWidth;
        this.diffWidth = toWidth - prevWidth;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) (width + diffWidth * interpolatedTime);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
