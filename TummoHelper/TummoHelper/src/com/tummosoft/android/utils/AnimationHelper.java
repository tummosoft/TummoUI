package com.tummosoft.android.utils;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
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
import android.view.animation.OvershootInterpolator;
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
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import android.animation.ValueAnimator;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@BA.ShortName("AnimationHelper")
@BA.Events(values = {"applyTransformation(interpolatedTime as float, t as Transformation)", "onAnimationUpdate(angle as float),onAnimationStart(),onAnimationEnd(),onAnimationRepeat(),onAnimationCancel(),startTransition(view as Object),endTransition(view as Object)"})
public class AnimationHelper extends Animation {

    private Context baContext;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private BA _ba;
    private int idx = 0;
    public final int ANIMATION_ABSOLUTE = Animation.ABSOLUTE;
    public final int ANIMATION_INFINITE = Animation.INFINITE;
    public final int ANIMATION_RELATIVE_TO_PARENT = Animation.RELATIVE_TO_PARENT;
    public final int ANIMATION_RELATIVE_TO_SELF = Animation.RELATIVE_TO_SELF;
    public final int ANIMATION_RESTART = Animation.RESTART;
    public final int ANIMATION_REVERSE = Animation.REVERSE;
    public final int START_ON_FIRST_FRAME = Animation.START_ON_FIRST_FRAME;
    public final int ZORDER_BOTTOM = Animation.ZORDER_BOTTOM;
    public final int ZORDER_NORMAL = Animation.ZORDER_NORMAL;
    public final int ZORDER_TOP = Animation.ZORDER_TOP;

    public final int MODE_INFINITE = ObjectAnimator.INFINITE;
    public final int MODE_RESTART = ObjectAnimator.RESTART;
    public final int MODE_REVERSE = ObjectAnimator.REVERSE;
    public final int MODE_DURATION_INFINITE = (int) ObjectAnimator.DURATION_INFINITE;
    private ValueAnimator valueAnimator = null;
    private LayoutTransition transition = null;
    private AnimationDrawable drawable = null;
    private TimeInterpolator timeinterpolator = null;
    private Interpolator interpolator = null;
    private AnimationSet collectAnimation = null;

    public void initialize(final BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        _eventName = event.toLowerCase();
        valueAnimator = new ValueAnimator();

    }

    private boolean isFrontVisible = true;

    public TranslateAnimation CreateTranslateAnimation(int[] position, long Duration, Interpolator Polator, boolean FillAfter) {
        TranslateAnimation animation = new TranslateAnimation(
                position[0],
                position[1],
                position[2],
                position[3]
        );

        animation.setDuration(Duration);
        if (Polator != null) {
            animation.setInterpolator(Polator);
        }
        animation.setFillAfter(FillAfter);

        return animation;
    }

    public ScaleAnimation CreateScaleAnimation(float[] position, int centerX, int centerY, long Duration, xInterpolator Polator, boolean FillAfter) {
        ScaleAnimation animation = new ScaleAnimation(
                position[0],
                position[1],
                position[2],
                position[3],
                centerX,
                position[4],
                centerY,
                position[5]
        );

        animation.setDuration(Duration);
        if (Polator != null) {
            animation.setInterpolator(Polator.getObject());
        }
        animation.setFillAfter(FillAfter);

        return animation;
    }
    
    public ObjectAnimator CreateObjectAnimatorOfFloat(View view, String propertyName, float... values) {
        return ObjectAnimator.ofFloat(view, propertyName, values);
    }

    public ObjectAnimator CreateObjectAnimatorOfInt(View view, String propertyName, int... values) {
        return ObjectAnimator.ofInt(view, propertyName, values);
    }

    public AlphaAnimation CreateAlphaAnimation(float fromAlpha, float toAlpha, long Duration, xInterpolator Polator, boolean FillAfter, int repeatCount, int repeatMode, boolean FillBefore) {
        AlphaAnimation animation = new AlphaAnimation(
                fromAlpha,
                toAlpha
        );

        animation.setDuration(Duration);
        if (Polator != null) {
            animation.setInterpolator(Polator.getObject());
        }
        if (repeatCount != -1) {
            animation.setRepeatCount(repeatCount);
        }
        if (repeatMode != -1) {
            animation.setRepeatCount(repeatMode);
        }

        animation.setFillAfter(FillAfter);
        animation.setFillBefore(FillBefore);

        return animation;
    }
    
     public RotateAnimation CreateRotateAnimation(xDegrees degress, xInterpolator Polator, long Duration, boolean FillAfter, int repeatCount, int repeatMode, boolean FillBefore) {
        RotateAnimation animation = new RotateAnimation(degress.getFromDegrees(), degress.getToDegrees(), degress.getCenterX1(), degress.getCenterX2(), degress.getCenterY1(), degress.getCenterY2());

        animation.setDuration(Duration);
        if (Polator != null) {
            animation.setInterpolator(Polator.getObject());
        }
        if (repeatCount != -1) {
            animation.setRepeatCount(repeatCount);
        }
        if (repeatMode != -1) {
            animation.setRepeatCount(repeatMode);
        }

        animation.setFillAfter(FillAfter);
        animation.setFillBefore(FillBefore);

        return animation;
    }
     
      public TranslateAnimation CreateTranslateAnimation(xDegrees degress, xInterpolator Polator, long Duration, boolean FillAfter, int repeatCount, int repeatMode, boolean FillBefore) {
        TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0,
                                                Animation.ABSOLUTE, 0,
                                                Animation.ABSOLUTE, 0,
                                                Animation.ABSOLUTE, 0);

        animation.setDuration(Duration);
        if (Polator != null) {
            animation.setInterpolator(Polator.getObject());
        }
        if (repeatCount != -1) {
            animation.setRepeatCount(repeatCount);
        }
        if (repeatMode != -1) {
            animation.setRepeatCount(repeatMode);
        }

        animation.setFillAfter(FillAfter);
        animation.setFillBefore(FillBefore);

        return animation;
    }
    

    public void AnimationSet_initialize() {
        collectAnimation = new AnimationSet(true);
    }

    public void AnimationSet_addAnimation(Animation item) {
        collectAnimation.addAnimation(item);        
    }

    public void AnimationSet_Reset() {
        collectAnimation.reset();
    }

    public void AnimationSet_Start(View view) {
        view.startAnimation(collectAnimation);
    }
    
    private AnimatorSet collectAnimator;
     public void AnimatorSet_initialize() {
        collectAnimator = new AnimatorSet();
    }

    public void AnimatorSet_addAnimator(Object... items) {        
        Collection<Animator> collection = new ArrayList<>();
        
        for (Object item : items) {
           collection.add((Animator)item);
        }
        
        collectAnimator.playTogether(collection);
    }
    
    public void AnimatorSet_Start() {
       collectAnimator.start();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        xTransformation t1 = new xTransformation(); //applyTransformation(interpolatedTime as float, t as Transformation)
        t1.setObject(t);
        _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_applytransformation", interpolatedTime, t1);
    }

    public void SetAnimationEvent(String _event, AnimationHelper anim, long Duration, boolean FillAfter) {
        anim.setDuration(Duration);
        anim.setFillAfter(FillAfter);
        anim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation anmtn) {
                _ba.raiseEventFromUI(this, _event.toLowerCase() + "_onanimationstart", anmtn);
            }

            @Override
            public void onAnimationEnd(Animation anmtn) {
                _ba.raiseEventFromUI(this, _event.toLowerCase() + "_onanimationend", anmtn);
            }

            @Override
            public void onAnimationRepeat(Animation anmtn) {
                _ba.raiseEventFromUI(this, _event.toLowerCase() + "_onanimationrepeat", anmtn);
            }
        });
    }

    public Animation getAnim(@AnimRes int resId) {
        return AnimationUtils.loadAnimation(baContext, resId);
    }

    public void AnimationFromResID(Object viewObject, int resourceID) {
        View view = (View) viewObject;
        Animation animation = AnimationUtils.loadAnimation(baContext, resourceID);
        view.startAnimation(animation);
    }

    public void AnimationDrawable_initialize(boolean oneShot) {
        drawable = new AnimationDrawable();
        drawable.setOneShot(oneShot);
    }

    public void AnimationDrawable_addFrame(Drawable frame, int duration) {
        drawable.addFrame(frame, duration);
    }

    public Drawable GetAnimationDrawable() {
        return drawable;
    }

    public void AnimationDrawable_Start() {
        drawable.start();
    }

    public void AnimationDrawable_Stop() {
        drawable.stop();
    }

    public ObjectAnimator CreateObjectAnimator(Object view, String PropertyName, float... values) {
        return ObjectAnimator.ofFloat(null, PropertyName, values);
    }

    public void LayoutAnimator_initialize(LinearLayout linearLayoutContainer) {
        transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_starttransition", view);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_endtransition", view);
            }
        });

        linearLayoutContainer.setLayoutTransition(transition);
    }

    public void LayoutAnimator_initialize2(TableLayout layout) {
        transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_starttransition", view);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_endtransition", view);
            }
        });

        layout.setLayoutTransition(transition);
    }

    public void LayoutAnimator_initialize3(RelativeLayout layout) {
        transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_starttransition", view);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_endtransition", view);
            }
        });

        layout.setLayoutTransition(transition);
    }

    public void LayoutAnimator_initialize4(GridView layout) {
        transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_starttransition", view);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_endtransition", view);
            }
        });

        layout.setLayoutTransition(transition);
    }

    public void LayoutAnimator_initialize5(ConstraintLayout layout) {
        transition = new LayoutTransition();
        transition.addTransitionListener(new LayoutTransition.TransitionListener() {
            @Override
            public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_starttransition", view);
            }

            @Override
            public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_endtransition", view);
            }
        });

        layout.setLayoutTransition(transition);
    }

    public void LayoutAnimatorIn(ObjectAnimator ObjAnimIn, String TransitionMethod) {
        TransitionMethod = TransitionMethod.toUpperCase();
        if (TransitionMethod.contains("APPEARING")) {
            transition.setAnimator(LayoutTransition.APPEARING, ObjAnimIn);
        } else if (TransitionMethod.contains("CHANGE_APPEARING")) {
            transition.setAnimator(LayoutTransition.CHANGE_APPEARING, ObjAnimIn); //APPEARING, CHANGE_APPEARING, CHANGE_DISAPPEARING, CHANGING, DISAPPEARING
        } else if (TransitionMethod.contains("CHANGE_DISAPPEARING")) {
            transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, ObjAnimIn);
        } else if (TransitionMethod.contains("CHANGING")) {
            transition.setAnimator(LayoutTransition.CHANGING, ObjAnimIn);
        } else if (TransitionMethod.contains("DISAPPEARING")) {
            transition.setAnimator(LayoutTransition.DISAPPEARING, ObjAnimIn);
        }
    }

    public void LayoutAnimatorOut(ObjectAnimator ObjAnimOut, String TransitionMethod) {
        TransitionMethod = TransitionMethod.toUpperCase();
        if (TransitionMethod.contains("APPEARING")) {
            transition.setAnimator(LayoutTransition.APPEARING, ObjAnimOut);
        } else if (TransitionMethod.contains("CHANGE_APPEARING")) {
            transition.setAnimator(LayoutTransition.CHANGE_APPEARING, ObjAnimOut);
        } else if (TransitionMethod.contains("CHANGE_DISAPPEARING")) {
            transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, ObjAnimOut);
        } else if (TransitionMethod.contains("CHANGING")) {
            transition.setAnimator(LayoutTransition.CHANGING, ObjAnimOut);
        } else if (TransitionMethod.contains("DISAPPEARING")) {
            transition.setAnimator(LayoutTransition.DISAPPEARING, ObjAnimOut);
        }
    }

    public void ValueAnimatorOfInt(int... values) {
        valueAnimator = ValueAnimator.ofInt(values);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Object curValue = animation.getAnimatedValue();
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationupdate", curValue);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animation) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationstart", null);
            }

            public void onAnimationEnd(Animator animation) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationend", null);
            }

            public void onAnimationCancel(Animator animation) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onAnimationCancel", null);
            }

            public void onAnimationRepeat(Animator animation) {
                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationrepeat", null);
            }
        });
    }

    public void ValueAnimatorOfInt_Start(int restart, int repeatCount, int Duration) {
        valueAnimator.setRepeatMode(restart);
        valueAnimator.setRepeatCount(repeatCount);
        valueAnimator.setDuration(Duration);
        valueAnimator.start();
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

    public AnimationDrawable getDrawableAnimation(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        AnimationDrawable drawable = (AnimationDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public AnimatedStateListDrawable getAnimatedStateListDrawable(@DrawableRes int resId) {
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
        setInterpolator(new FastOutSlowInInterpolator());
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

//    // Testing
//    public void setTimeInterPolatorValue(String value) {
//        value = value.toLowerCase();
//        if (value.contains("linear")) {
//            timeinterpolator = new LinearInterpolator();
//        } else if (value.contains("accelerate")) {
//            timeinterpolator = new AccelerateInterpolator();
//        } else if (value.contains("decelerate")) {
//            timeinterpolator = new DecelerateInterpolator();
//        } else if (value.contains("accelerateDecelerate")) {
//            timeinterpolator = new AccelerateDecelerateInterpolator();
//        } else if (value.contains("bounce")) {
//            timeinterpolator = new BounceInterpolator();
//        } else if (value.contains("overshoot")) {
//            timeinterpolator = new OvershootInterpolator();
//        } else if (value.contains("anticipate")) {
//            timeinterpolator = new AnticipateInterpolator();
//        } else if (value.contains("anticipateOvershoot")) {
//            timeinterpolator = new AnticipateOvershootInterpolator();
//        }
//
//    }
//    
//    
//    private int mRepeatMode = -1;
//    
//    public void setRepetModeValue(String value) {
//        value = value.toUpperCase();
//        if (value.contains("RESTART")) {
//            mRepeatMode = Animation.RESTART;
//        } else if (value.contains("REVERSE")) {
//            mRepeatMode = Animation.REVERSE;        
//        }
//    }
//    
//    private int xRelativeValues = -1;
//    private int yRelativeValues = -1;
//    
//    public void setRelativeValues(String value1, String value2) {
//        xRelativeValues = convertRelative(value1.toUpperCase());
//        yRelativeValues = convertRelative(value2.toUpperCase());
//    }
//    
//    private int convertRelative(String value) {
//        int result = 0;
//         value = value.toUpperCase();
//        if (value.contains("RELATIVE_TO_PARENT")) { //RELATIVE_TO_PARENT, RELATIVE_TO_SELF, START_ON_FIRST_FRAME, ZORDER_BOTTOM,ZORDER_NORMAL,ZORDER_TOP
//            result = Animation.RELATIVE_TO_PARENT;
//        } else if (value.contains("RELATIVE_TO_SELF")) {
//            result = Animation.RELATIVE_TO_SELF;
//        } else if (value.contains("START_ON_FIRST_FRAME")) {
//            result = Animation.START_ON_FIRST_FRAME;
//        } else if (value.contains("ZORDER_BOTTOM")) {
//            result = Animation.ZORDER_BOTTOM;
//        } else if (value.contains("ZORDER_NORMAL")) {
//            result = Animation.ZORDER_NORMAL;
//        } else if (value.contains("ZORDER_TOP")) {
//            result = Animation.ZORDER_TOP;
//        }
//        
//        return result;
//    }
//    
//    int mRepeatCount = -1;
//    public void setRepeatCountValue(int value) {
//        mRepeatCount = value;
//    }
//     
//    private Collection<Animator> collect = null;
//    private AnimatorSet AnimatorCollections = null;
//    public void AnimatorSet_initialize() {
//        AnimatorCollections = new AnimatorSet();
//        collect = new ArrayList<>();;
//        AnimatorCollections.addListener(new AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator anmtr) {
//                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationstart", null);
//            }
//
//            @Override
//            public void onAnimationEnd(Animator anmtr) {
//                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationend", null);
//            }
//
//            @Override
//            public void onAnimationCancel(Animator anmtr) {                
//                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationcancel", null);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator anmtr) {                
//                _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_onanimationrepeat", null);
//            }
//        });
//    }
//    
//    
//    public void AnimatorSet_Add(xObjectAnimator animator) {
//        collect.add(animator.getObject());         
//    }
//         
//    public void AnimatorSet_Start() {
//        AnimatorCollections.playTogether(collect);
//        AnimatorCollections.start();         
//    }
//     
//    public ObjectAnimator CreateObjectAnimatorInt(View view, int Duration, Interpolator interpolator, String propertyName, int... values) {
//        ObjectAnimator animatorObj = ObjectAnimator.ofInt(view, propertyName, values);     
//        animatorObj.setDuration(Duration);
//        animatorObj.setInterpolator(interpolator);
//        return animatorObj;
//    }
//    
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
    public TranslateAnimation slideIn(final View view, String event, int duration, String direction, int RepeatMode, int RepeatCount) {
        return slideIn(view, event, duration, true, direction, RepeatMode, RepeatCount);
    }

    @Nullable
    public TranslateAnimation slideIn(final View view, String event, int duration, boolean isNeedAnimation, String direction, int RepeatMode, int RepeatCount) {
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
    public TranslateAnimation slideOut(final View view, String event, int duration, String direction) {
        return slideOut(view, event, duration, true, direction);
    }

    @Nullable
    public TranslateAnimation slideOut(final View view, String event, int duration, boolean isNeedAnimation, String direction) {
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

    public void SimpleSlide(View view, Context context, int animID) {
        Animation shake = AnimationUtils.loadAnimation(context, animID);
        view.startAnimation(shake);
    }

}
// Con xin hoi huong phuoc duc nay den toan the Chu thien, cac vua troi Tu Dai Thien Vuong
// Nho chu vi hoa giai ta thuat cua thang Phong (con nuoi Ut Vuon Co);
// Nho chu vi hoa giai ta thuat cua gia dinh thang 5 Tiet, thang Dung, thang Si, anh chi em ben vo;
// Nho chu vi hoa giai ta thuat cua thay bua 3 Nhang;
// Nho chu vi hoa giai ta thuat cua gia dinh Huu Ky (2 Chien), tat ca con gai va re cua Tran Van Chien, tat ca chau dau chau re cua Tran Van Chien;
// Hoa giai ta thuat den tu gia dinh thang Hung (8 Duc), thang Dieu (8 Duc), vo thang Hung, anh chi em ben vo thang Hung;
// Hoa giai ta thuat cua thang Tran Van Ngoan, thang Ut Vuon Co, thang Tran Van Hai tat ca nhung nguoi do thang Ngoan thue muon;
// Hoa giai bua nhai, bua thang lan, bua tac ke cuar thang Tran Van Ngoan (con ut Vuon Co);
// Hoa giai ta thuat cua con Pham Thi Vui, thang Tran Quang Vinh, ba Tran Thi Chia;
//https://www.programcreek.com/java-api-examples/?code=myinnos%2FImageSliderWithSwipes%2FImageSliderWithSwipes-master%2Fimagesliderwithswipeslibrary%2Fsrc%2Fmain%2Fjava%2Fin%2Fmyinnos%2Fimagesliderwithswipeslibrary%2FTransformers%2FRotateDownTransformer.java#
