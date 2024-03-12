package com.tummosoft.android.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import androidx.annotation.AnimRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;
import static com.tummosoft.android.utils.ViewHelper.Direction.BOTTOM_TO_TOP;
import static com.tummosoft.android.utils.ViewHelper.Direction.LEFT_TO_RIGHT;
import static com.tummosoft.android.utils.ViewHelper.Direction.RIGHT_TO_LEFT;
import static com.tummosoft.android.utils.ViewHelper.Direction.TOP_TO_BOTTOM;

@BA.ShortName("AnimationHelper")
@BA.Events(values = {"onAnimationUpdate(angle as float),onAnimationStart(),onAnimationEnd(),onAnimationRepeat()"})
public class AnimationHelper {

    private static Context baContext;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private static BA _ba;
    private int idx = 0;
    public static final int MODE_INFINITE = ObjectAnimator.INFINITE;
    public static final int MODE_RESTART = ObjectAnimator.RESTART;
    public static final int MODE_REVERSE = ObjectAnimator.REVERSE;
    public static final int MODE_DURATION_INFINITE = (int) ObjectAnimator.DURATION_INFINITE;

    public void initialize(BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        _eventName = event.toLowerCase();
    }

    public static Animation getAnim(@AnimRes int resId) {
        return AnimationUtils.loadAnimation(baContext, resId);
    }

    public ObjectAnimator startLinearInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startAccelerateDecelerateInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startAccelerateInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AccelerateInterpolator());        
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public static AnimationDrawable getDrawableAnimation(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        AnimationDrawable drawable = (AnimationDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static AnimatedStateListDrawable getAnimatedStateListDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        AnimatedStateListDrawable drawable = (AnimatedStateListDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public ObjectAnimator startAnticipateInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AnticipateInterpolator());
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    ValueAnimator animator = null;
    private boolean isPlaying = false;

    public void stopRotate() {
        isPlaying = false;
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    private float degree;

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public void setRotation(View view, float degree) {
        view.setRotation(degree);
    }

   
    public ObjectAnimator startAnticipateOvershootInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AnticipateOvershootInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startBounceInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new BounceInterpolator());
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startCycleInterpolator(View view, int Duration, String event, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new CycleInterpolator(2));
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startDecelerateInterpolator(View view, String event, int Duration, String effects, float startAt, float endAt, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationupdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public void startFlip(View view, int Duration, String rolation, int RepeatMode, int RepeatCount) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, rolation, 0f, 180f, 360f);
        anim.setDuration(Duration);
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.start();
    }

    public void startTranslateAnimation(View view, String event, int Duration, float deltaX, float deltaY, boolean keepPosition, int RepeatMode, int RepeatCount) {
        Animation anim = new TranslateAnimation(0, deltaX, 0, deltaY);
        anim.setDuration(Duration);
        anim.setRepeatMode(RepeatMode);
        anim.setRepeatCount(RepeatCount);
        anim.setFillAfter(keepPosition);
        if (event.isEmpty() == false) {
            anim.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationstart", null);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationend", null);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationrepeat", null);
                }
            });
        }
        view.startAnimation(anim);
    }
    
    public void setAnimatorOfFloat(View view, int Duration, String proper, float[] frame1, int RepeatMode, int RepeatCount) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, proper, frame1);
        rotationAnim.setDuration(Duration);
        rotationAnim.setRepeatMode(RepeatMode);
        rotationAnim.setRepeatCount(RepeatCount);
        rotationAnim.start();
    }

    public void startCollapse(int Duration, float TranslationY, View view) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationY", 0f, TranslationY);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.75f);
        set.setDuration(Duration);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(translate, alpha);
        set.start();
    }

    public void startExpansion(int Duration, float TranslationY, View view) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator translate = ObjectAnimator.ofFloat(view, "translationY", TranslationY, 0);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0.75f, 1f);
        set.setDuration(Duration);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(translate, alpha);
        set.start();
    }

    public void startFyling(View view) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, "translationX", view.getX(), 300, view.getX());
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translation).with(rotate).before(alpha);
        animSet.setDuration(5000);
        animSet.start();
    }

    @Nullable
    public static TranslateAnimation slideIn(final View view, String event, int duration, String direction, int RepeatMode, int RepeatCount) {
        return slideIn(view, event, duration, true, direction, RepeatMode, RepeatCount);
    }

    @Nullable
    public static TranslateAnimation slideIn(final View view, String event, int duration, boolean isNeedAnimation, String direction, int RepeatMode, int RepeatCount) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case "LEFT_TO_RIGHT":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case "TOP_TO_BOTTOM":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case "RIGHT_TO_LEFT":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case "BOTTOM_TO_TOP":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                default:
                    break;
            }
            translate.setInterpolator(new DecelerateInterpolator());
            translate.setDuration(duration);
            translate.setFillAfter(true);
            translate.setAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationstart", null);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationend", null);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationrepeat", null);
                }
            });

            view.setVisibility(View.VISIBLE);
            view.startAnimation(translate);
            return translate;
        } else {
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
            return null;
        }
    }

    @Nullable
    public static TranslateAnimation slideOut(final View view, String event, int duration, String direction) {
        return slideOut(view, event, duration, true, direction);
    }

    @Nullable
    public static TranslateAnimation slideOut(final View view, String event, int duration, boolean isNeedAnimation, String direction) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case "LEFT_TO_RIGHT":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case "TOP_TO_BOTTOM":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
                    );
                    break;
                case "RIGHT_TO_LEFT":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case "BOTTOM_TO_TOP":
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f
                    );
                    break;
                default:
                    break;
            }
            translate.setInterpolator(new DecelerateInterpolator());
            translate.setDuration(duration);
            translate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationstart", null);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationend", null);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    _ba.raiseEventFromUI(this, event.toLowerCase() + "_onanimationrepeat", null);
                }
            });
            view.startAnimation(translate);
            return translate;
        } else {
            view.clearAnimation();
            view.setVisibility(View.GONE);
            return null;
        }
    }

    public static void SimpleSlide(View view, Context context, int animID) {
        Animation shake = AnimationUtils.loadAnimation(context, animID);
        view.startAnimation(shake);
    }

//https://www.programcreek.com/java-api-examples/?code=myinnos%2FImageSliderWithSwipes%2FImageSliderWithSwipes-master%2Fimagesliderwithswipeslibrary%2Fsrc%2Fmain%2Fjava%2Fin%2Fmyinnos%2Fimagesliderwithswipeslibrary%2FTransformers%2FRotateDownTransformer.java#
}
//* I wish that this good deed would invalidate all the evil magic of the two men's families. Those two people often run on a train and that train uses an old engine.
//*
//