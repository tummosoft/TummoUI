package com.tummosoft.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.ColorInt;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;
import com.tummosoft.Utils.ViewHelper;
import java.util.ArrayList;
import java.util.List;

@BA.ShortName("xAnimation")
@BA.ActivityObject
@BA.Events(values = {"onAnimationUpdate(angle as float)"})
public class xAnimation {

    private static Context baContext;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private BA _ba;
    private int idx = 0;

    public void initialize(BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        _eventName = event.toLowerCase();
    }

    public ObjectAnimator startLinearInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new LinearInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startAccelerateDecelerateInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startAccelerateInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AccelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startAnticipateInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AnticipateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
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
    
    public void startRotate(View view, float degree) {
        stopRotate();
        isPlaying = true;
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0, 360);
            animator.setEvaluator(new TypeEvaluator<Float>() {
                @Override
                public Float evaluate(float fraction, Float startValue, Float endValue) {
                    return fraction * 360;
                }
            });
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(3000)
                    .setRepeatMode(ValueAnimator.RESTART);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    setDegree((Float) animation.getAnimatedValue());
                    view.setRotation(degree);
                }
            });
        }
        animator.start();
    }

    public ObjectAnimator startAnticipateOvershootInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new AnticipateOvershootInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startBounceInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new BounceInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startCycleInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new CycleInterpolator(2));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }

    public ObjectAnimator startDecelerateInterpolator(View view, int Duration, String effects, float startAt, float endAt) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, effects, startAt, endAt);
        anim.setDuration(Duration);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName + "_onAnimationUpdate", value);
            }
        });
        anim.start();
        return anim;
    }
    
    public void startFlip(View view,int Duration, String rolation) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, rolation, 0f, 180f, 360f);
        rotationAnim.setDuration(Duration);
        rotationAnim.start();
    }

    public void startMoveX(View view,int Duration, float x) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "translationX", 0f, x, -x, 0f);
        rotationAnim.setDuration(Duration);
        rotationAnim.start();
    }

    public void startScale(View view,int Duration, float size, float wide) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "scaleX", size, wide, size);
        rotationAnim.setDuration(Duration);
        rotationAnim.start();
    }

    public void startAlpha(View view, int Duration, float frame1, float frame2, float frame3) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "alpha", frame1, frame2, frame3);
        rotationAnim.setDuration(Duration);
        rotationAnim.start();
    }

    public void setAnimatorOfFloat(View view, int Duration, String proper, float frame1, float frame2, float frame3) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "alpha", frame1, frame2, frame3);
        rotationAnim.setDuration(Duration);
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
    
     public static void playBackgroundBlinkAnimation(final View v, @ColorInt int bgColor) {
        if (v == null) {
            return;
        }
        int[] alphaArray = new int[]{0, 255, 0};
        playViewBackgroundAnimation(v, bgColor, alphaArray, 300);
    }
        
    public static Animator playViewBackgroundAnimation(final View v, @ColorInt int bgColor, int[] alphaArray, int stepDuration, final Runnable endAction) {
        int animationCount = alphaArray.length - 1;

        Drawable bgDrawable = new ColorDrawable(bgColor);
        final Drawable oldBgDrawable = v.getBackground();
        ViewHelper.setBackgroundKeepingPadding(v, bgDrawable);

        List<Animator> animatorList = new ArrayList<>();
        for (int i = 0; i < animationCount; i++) {
            ObjectAnimator animator = ObjectAnimator.ofInt(v.getBackground(), "alpha", alphaArray[i], alphaArray[i + 1]);
            animatorList.add(animator);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(stepDuration);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewHelper.setBackgroundKeepingPadding(v, oldBgDrawable);
                if (endAction != null) {
                    endAction.run();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animatorSet.playSequentially(animatorList);
        animatorSet.start();
        return animatorSet;
    }

    public static void playViewBackgroundAnimation(final View v, @ColorInt int bgColor, int[] alphaArray, int stepDuration) {
        playViewBackgroundAnimation(v, bgColor, alphaArray, stepDuration, null);
    }

 
    public static void playViewBackgroundAnimation(final View v, @ColorInt int startColor, @ColorInt int endColor, long duration, int repeatCount, int setAnimTagId, final Runnable endAction) {
        final Drawable oldBgDrawable = v.getBackground();
        ViewHelper.setBackgroundColorKeepPadding(v, startColor);
        final ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(startColor, endColor);
        anim.setDuration(duration / (repeatCount + 1));
        anim.setRepeatCount(repeatCount);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewHelper.setBackgroundColorKeepPadding(v, (Integer) animation.getAnimatedValue());
            }
        });
        if (setAnimTagId != 0) {
            v.setTag(setAnimTagId, anim);
        }
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ViewHelper.setBackgroundKeepingPadding(v, oldBgDrawable);
                if (endAction != null) {
                    endAction.run();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    public static void playViewBackgroundAnimation(final View v, int startColor, int endColor, long duration) {
        playViewBackgroundAnimation(v, startColor, endColor, duration, 0, 0, null);
    }
    
    public static AlphaAnimation fadeIn(View view, int duration, Animation.AnimationListener listener) {
        return fadeIn(view, duration, listener, true);
    }

    public static AlphaAnimation fadeIn(View view, int duration, Animation.AnimationListener listener, boolean isNeedAnimation) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            view.setVisibility(View.VISIBLE);
            AlphaAnimation alpha = new AlphaAnimation(0, 1);
            alpha.setInterpolator(new DecelerateInterpolator());
            alpha.setDuration(duration);
            alpha.setFillAfter(true);
            if (listener != null) {
                alpha.setAnimationListener(listener);
            }
            view.startAnimation(alpha);
            return alpha;
        } else {
            view.setAlpha(1);
            view.setVisibility(View.VISIBLE);
            return null;
        }
    }
      
    public static AlphaAnimation fadeOut(final View view, int duration, final Animation.AnimationListener listener) {
        return fadeOut(view, duration, listener, true);
    }

    public static AlphaAnimation fadeOut(final View view, int duration, final Animation.AnimationListener listener, boolean isNeedAnimation) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            AlphaAnimation alpha = new AlphaAnimation(1, 0);
            alpha.setInterpolator(new DecelerateInterpolator());
            alpha.setDuration(duration);
            alpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationStart(animation);
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    if (listener != null) {
                        listener.onAnimationEnd(animation);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    if (listener != null) {
                        listener.onAnimationRepeat(animation);
                    }
                }
            });
            view.startAnimation(alpha);
            return alpha;
        } else {
            view.setVisibility(View.GONE);
            return null;
        }
    }
       
    public static void clearValueAnimator(Animator animator) {
        if (animator != null) {
            animator.removeAllListeners();
            if (animator instanceof ValueAnimator) {
                ((ValueAnimator) animator).removeAllUpdateListeners();
            }

            if (Build.VERSION.SDK_INT >= 19) {
                animator.pause();
            }
            animator.cancel();
        }
    }
       

}
