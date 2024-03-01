package com.tummosoft.layout;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.tummosoft.animation.CollapseViewHeightAnim;
import com.tummosoft.animation.CollapseViewWidthAnim;
import com.tummosoft.animation.ExpandViewHeightAnim;
import com.tummosoft.animation.ExpandViewWidthAnim;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import com.tummosoft.control.image.xImageView;
import java.util.List;


@BA.ShortName("xAnimation")
public class xAnimation extends AbsObjectWrapper<Animation> {
    private BA _ba;
    private String _event;
       
    public void initialize(BA ba, String event, int animID) {
        _ba = ba;
        _event = event;
        setObject(AnimationUtils.loadAnimation(_ba.context, animID));
    }
    
    public void StartAnim(View view) {
        view.startAnimation(getObject());
    }
    
    public static void expandLayoutVertically(final View view, int expandHeight, int durationMs) {

        ExpandViewHeightAnim expandAnimation = new ExpandViewHeightAnim(view, view.getHeight(), expandHeight);
        expandAnimation.setDuration(durationMs);
        expandAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        expandAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(expandAnimation);
    }
    
    public static void collapseLayoutVertically(final View view, int durationMs) {

        CollapseViewHeightAnim collapseAnimation = new CollapseViewHeightAnim(view, view.getHeight(), 0);
        collapseAnimation.setDuration(durationMs);
        collapseAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        collapseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /*view.setVisibility(View.INVISIBLE);*/
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(collapseAnimation);
    }

    public static void expandLayoutHorizontally(final View view, int expandWidth, int durationMs) {

        ExpandViewWidthAnim expandAnimation = new ExpandViewWidthAnim(view, view.getWidth(), expandWidth);
        expandAnimation.setDuration(durationMs);
        expandAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        expandAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(expandAnimation);
    }
    
    public static void collapseLayoutHorizontally(final View view, int durationMs) {

        CollapseViewWidthAnim collapseAnimation = new CollapseViewWidthAnim(view, view.getWidth(), 0);
        collapseAnimation.setDuration(durationMs);
        collapseAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        collapseAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(collapseAnimation);
    }
    
     
}
