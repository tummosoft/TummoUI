package com.tummosoft.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ExpandViewHeightAnim extends Animation {
      private View view;
    private int height;
    private int diffHeight;

    public ExpandViewHeightAnim(View view, int prevHeight, int toHeight) {
        this.view = view;
        this.height = prevHeight;
        this.diffHeight = toHeight - prevHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (height + diffHeight * interpolatedTime);
        view.setLayoutParams(layoutParams);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
