package com.tummosoft.android.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
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
import android.animation.ValueAnimator;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.animation.Transformation;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import anywheresoftware.b4a.AbsObjectWrapper;

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
    public final int MODE_INFINITE = ObjectAnimator.INFINITE;
    public final int MODE_RESTART = ObjectAnimator.RESTART;
    public final int MODE_REVERSE = ObjectAnimator.REVERSE;
    public final int MODE_DURATION_INFINITE = (int) ObjectAnimator.DURATION_INFINITE;
    private ValueAnimator valueAnimator = null;
    private LayoutTransition transition = null;
    private AnimationDrawable drawable = null;

    public void initialize(final BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        _eventName = event.toLowerCase();
    }

    private boolean isFrontVisible = true;

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        xTransformation t1 = new xTransformation(); //applyTransformation(interpolatedTime as float, t as Transformation)
        t1.setObject(t);
        _ba.raiseEventFromUI(this, _eventName.toLowerCase() + "_applytransformation", interpolatedTime, t1);
    }

    public void SetAnimationEvent(String _event,  AnimationHelper anim, long Duration, boolean FillAfter) {
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

    
    // xTransformation class
    // ***********************************
    @BA.ShortName("xTransformation")
    public static class xTransformation extends AbsObjectWrapper<Transformation> {

        public void initialize(final BA ba, String event) {
            setObject(new Transformation());
        }

        public void Clear() {
            getObject().clear();
        }

        public float getAlpha() {
            return getObject().getAlpha();
        }

        public Matrix getMatrix() {
            return getObject().getMatrix();
        }

        public int getTransformationType() {
            return getObject().getTransformationType();
        }

        public void setTransformationType(int value) {
            getObject().setTransformationType(value);
        }
    }

    // xMatrix class
    // ***********************************
    @BA.ShortName("xMatrix")
    public static class xMatrix extends AbsObjectWrapper<Matrix> {

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

    // *********************************************************
    // --- END CLASS ---
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

    public void setValueAnimatorMethod(String Interpolator) {
        Interpolator = Interpolator.toLowerCase();
        if (Interpolator.contains("linear")) {
            valueAnimator.setInterpolator(new LinearInterpolator());
        } else if (Interpolator.contains("accelerate")) {
            valueAnimator.setInterpolator(new AccelerateInterpolator());
        } else if (Interpolator.contains("decelerate")) {
            valueAnimator.setInterpolator(new DecelerateInterpolator());
        } else if (Interpolator.contains("acceleratedecelerate")) {
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        } else if (Interpolator.contains("anticipate")) {
            valueAnimator.setInterpolator(new AnticipateInterpolator());
        } else if (Interpolator.contains("bounce")) {
            valueAnimator.setInterpolator(new BounceInterpolator());
        } else if (Interpolator.contains("overshoot")) {
            valueAnimator.setInterpolator(new OvershootInterpolator());
        } else if (Interpolator.contains("anticipateovershoot")) {
            valueAnimator.setInterpolator(new AnticipateOvershootInterpolator());
        }
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
// Hoa giai ta thuat den tu gia dinh thang Hung (8 Duc), thang Dieu (8 Duc), vo thang Hung, anh chi em ben vo thang Hung;
// Hoa giai ta thuat cua thang Ngoan, thang Ut Vuon Co;
//https://www.programcreek.com/java-api-examples/?code=myinnos%2FImageSliderWithSwipes%2FImageSliderWithSwipes-master%2Fimagesliderwithswipeslibrary%2Fsrc%2Fmain%2Fjava%2Fin%2Fmyinnos%2Fimagesliderwithswipeslibrary%2FTransformers%2FRotateDownTransformer.java#
