package com.tummosoft.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CollapseViewHeightAnim extends Animation {
    private View view;
    private int height;
    private int diffHeight;

    public CollapseViewHeightAnim(View view, int prevHeight, int toHeight) {
        this.view = view;
        this.height = prevHeight;
        this.diffHeight = prevHeight - toHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (Math.abs(height - diffHeight) == 0) {

            layoutParams.height = (int) (height - diffHeight * interpolatedTime);
            view.setLayoutParams(layoutParams);

        }
    }



    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
