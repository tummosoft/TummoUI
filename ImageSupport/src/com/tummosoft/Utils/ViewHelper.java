package com.tummosoft.Utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@BA.ShortName("ViewHelper")
public class ViewHelper {
         
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    
    private static Application sContext;
    private static boolean sIsTabletChecked;
    private static int sScreenType;
    private static String sDefaultFontAssetPath;
    
    public static void init(Application context) {
        sContext = context;
    }
    
    public static View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
    }

    public static void requestApplyInsets(Window window) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            window.getDecorView().requestFitSystemWindows();
        } else if (Build.VERSION.SDK_INT >= 21) {
            window.getDecorView().requestApplyInsets();
        }
    }
    
     public static int getListViewHeightBasedOnChildren(ListView view) {
        int height = getAbsListViewHeightBasedOnChildren(view);
        ListAdapter adapter;
        int adapterCount;
        if (view != null && (adapter = view.getAdapter()) != null
                && (adapterCount = adapter.getCount()) > 0) {
            height += view.getDividerHeight() * (adapterCount - 1);
        }
        return height;
    }

    /**
     * get AbsListView height according to every children
     *
     * @param view
     * @return
     */
    public static int getAbsListViewHeightBasedOnChildren(AbsListView view) {
        ListAdapter adapter;
        if (view == null || (adapter = view.getAdapter()) == null) {
            return 0;
        }

        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, view);
            if (item instanceof ViewGroup) {
                item.setLayoutParams(new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += view.getPaddingTop() + view.getPaddingBottom();
        return height;
    }
    
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundKeepingPadding(View view, Drawable drawable) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        setBackground(view, drawable);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public static void setBackgroundKeepingPadding(View view, int backgroundResId) {
        setBackgroundKeepingPadding(view, ContextCompat.getDrawable(view.getContext(), backgroundResId));
    }

    public static void setBackgroundColorKeepPadding(View view, @ColorInt int color) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        view.setBackgroundColor(color);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
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
        setBackgroundKeepingPadding(v, bgDrawable);

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
                setBackgroundKeepingPadding(v, oldBgDrawable);
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
                setBackgroundKeepingPadding(v, oldBgDrawable);
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

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            for (; ; ) {
                final int result = ATOMIC_INTEGER.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) {
                    newValue = 1; // Roll over to 1, not 0.
                }
                if (ATOMIC_INTEGER.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
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
       
    public static Rect calculateViewScreenLocation(View view) {
        int[] location = new int[2];        
        view.getLocationOnScreen(location);
        return new Rect(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }
       
    @Nullable
    public static TranslateAnimation slideIn(final View view, int duration, final Animation.AnimationListener listener, Direction direction) {
        return slideIn(view, duration, listener, true, direction);
    }

    @Nullable
    public static TranslateAnimation slideIn(final View view, int duration, final Animation.AnimationListener listener, boolean isNeedAnimation, Direction direction) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case LEFT_TO_RIGHT:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case TOP_TO_BOTTOM:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case RIGHT_TO_LEFT:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case BOTTOM_TO_TOP:
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
            if (listener != null) {
                translate.setAnimationListener(listener);
            }
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
    public static TranslateAnimation slideOut(final View view, int duration, final Animation.AnimationListener listener, Direction direction) {
        return slideOut(view, duration, listener, true, direction);
    }

    @Nullable
    public static TranslateAnimation slideOut(final View view, int duration, final Animation.AnimationListener listener, boolean isNeedAnimation, Direction direction) {
        if (view == null) {
            return null;
        }
        if (isNeedAnimation) {
            TranslateAnimation translate = null;
            switch (direction) {
                case LEFT_TO_RIGHT:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case TOP_TO_BOTTOM:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f
                    );
                    break;
                case RIGHT_TO_LEFT:
                    translate = new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1f,
                            Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f
                    );
                    break;
                case BOTTOM_TO_TOP:
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
            view.startAnimation(translate);
            return translate;
        } else {
            view.clearAnimation();
            view.setVisibility(View.GONE);
            return null;
        }
    }

    public static void setVisibility(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public static void setVisibility(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public static void setEnabled(View view, boolean enabled) {
        if (view != null) {
            view.setEnabled(enabled);
            if (view instanceof EditText) {
                view.setFocusable(enabled);
                view.setFocusableInTouchMode(enabled);
            }
        }
    }

    public static void setText(TextView view, String text) {
        if (view != null) {
            view.setText(text);
        }
    }

    public static void setText(TextView view, @StringRes int textId) {
        if (view != null) {
            view.setText(textId);
        }
    }
      
    public static void textColorId(TextView view, @ColorRes int colorId) {
        if (view != null) {
            view.setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        }
    }

    public static void setImageResource(ImageView view, @DrawableRes int imageId) {
        if (view != null) {
            view.setImageResource(imageId);
        }
    }
        
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        if (view != null) {
            view.setImageDrawable(drawable);
        }
    }

    public static void setImageLevel(ImageView view, int level) {
        if (view != null) {
            view.setImageLevel(level);
        }
    }

    public static void setImageTint(ImageView view, ColorStateList tint) {
        if (view != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setImageTintList(tint);
            }
        }
    }

    public static void setChecked(CompoundButton view, boolean isCheck) {
        if (view != null) {
            view.setChecked(isCheck);
        }
    }

    public static void setOnCheckedChangeListener(CompoundButton view, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        if (view != null) {
            view.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    public static void setCheckedSilent(CompoundButton view, boolean isCheck, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        if (view != null) {
            view.setOnCheckedChangeListener(null);
            view.setChecked(isCheck);
            view.setOnCheckedChangeListener(checkedChangeListener);
        }
    }
    
    @ShortName("expendTouchArea")
    public static void expendTouchArea(final View view, final int expendSize) {
        if (view != null) {
            final View parentView = (View) view.getParent();

            parentView.post(new Runnable() {
                @Override
                public void run() {                    
                    Rect rect = new Rect();
                    view.getHitRect(rect);
                    rect.left -= expendSize;
                    rect.top -= expendSize;
                    rect.right += expendSize;
                    rect.bottom += expendSize;
                    parentView.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
    }

    public static void setPadding(View view, int padding) {
        if (view != null) {
            view.setPadding(padding, padding, padding, padding);
        }
    }

    public static void setPaddingLeft(View view, int value) {
        if (value != view.getPaddingLeft()) {
            view.setPadding(value, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingStart(View view, int value) {
        if (value != view.getPaddingStart()) {
            view.setPaddingRelative(value, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    public static void setPaddingTop(View view, int value) {
        if (value != view.getPaddingTop()) {
            view.setPadding(view.getPaddingLeft(), value, view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingRight(View view, int value) {
        if (value != view.getPaddingRight()) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), value, view.getPaddingBottom());
        }
    }

    public static void setPaddingEnd(View view, int value) {
        if (value != view.getPaddingEnd()) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), value, view.getPaddingBottom());
        }
    }

    public static void setPaddingBottom(View view, int value) {
        if (value != view.getPaddingBottom()) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), value);
        }
    }

    public static boolean getIsLastLineSpacingExtraError() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }
    
    public static ImageButton findButton(View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        ImageButton view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = (ImageButton) vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
                
        return view;
    }

    public static View findViewFromViewStub(View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
        return view;
    }
    
    @SuppressLint("ResourceType")
    public static View findViewFromViewStub2(View parentView, int viewStubId, int inflatedViewId, int inflateLayoutResId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            if (vs.getLayoutResource() < 1 && inflateLayoutResId > 0) {
                vs.setLayoutResource(inflateLayoutResId);
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
        return view;
    }

     
    @SuppressLint("ResourceType")
    public static  View findViewFromViewStub3(final BA ba, String event, View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
        
         view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 ba.raiseEventFromUI(view, event.toLowerCase() + "_click",  null);       
            }
        });
                
        return view;
    }
     
       
    
    public static void safeSetImageViewSelected(ImageView imageView, boolean selected) {       
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return;
        }
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        imageView.setSelected(selected);
        if (drawable.getIntrinsicWidth() != drawableWidth || drawable.getIntrinsicHeight() != drawableHeight) {
            imageView.requestLayout();
        }
    }


    public static ColorFilter setImageViewTintColor(ImageView imageView, @ColorInt int tintColor) {
        LightingColorFilter colorFilter = new LightingColorFilter(Color.argb(255, 0, 0, 0), tintColor);
        imageView.setColorFilter(colorFilter);
        return colorFilter;
    }
        
    public static boolean isListViewAlreadyAtBottom(ListView listView) {
        if (listView.getAdapter() == null || listView.getHeight() == 0) {
            return false;
        }

        if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1) {
            View lastItemView = listView.getChildAt(listView.getChildCount() - 1);
            return lastItemView != null && lastItemView.getBottom() == listView.getHeight();
        }
        return false;
    }

       
    public static void getDescendantRect(ViewGroup parent, View descendant, Rect out) {
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        ViewGroupHelper.offsetDescendantRect(parent, descendant, out);
    }

    @ShortName("ViewGroupHelper")
    private static class ViewGroupHelper {
        private static final ThreadLocal<Matrix> MATRIX_THREAD_LOCAL = new ThreadLocal<>();
        private static final ThreadLocal<RectF> RECT_F_THREAD_LOCAL = new ThreadLocal<>();

        public static void offsetDescendantRect(ViewGroup group, View child, Rect rect) {
            Matrix m = MATRIX_THREAD_LOCAL.get();
            if (m == null) {
                m = new Matrix();
                MATRIX_THREAD_LOCAL.set(m);
            } else {
                m.reset();
            }

            offsetDescendantMatrix(group, child, m);

            RectF rectF = RECT_F_THREAD_LOCAL.get();
            if (rectF == null) {
                rectF = new RectF();
                RECT_F_THREAD_LOCAL.set(rectF);
            }
            rectF.set(rect);
            m.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f),
                    (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
        }

        static void offsetDescendantMatrix(ViewParent target, View view, Matrix m) {
            final ViewParent parent = view.getParent();
            if (parent instanceof View && parent != target) {
                final View vp = (View) parent;
                offsetDescendantMatrix(target, vp, m);
                m.preTranslate(-vp.getScrollX(), -vp.getScrollY());
            }

            m.preTranslate(view.getLeft(), view.getTop());

            if (!view.getMatrix().isIdentity()) {
                m.preConcat(view.getMatrix());
            }
        }
    }
       
    public static void setViewTextFont(View view, Typeface typeface) {
        if (view == null || typeface == null) {
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(typeface);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setViewTextFont(viewGroup.getChildAt(i), typeface);
            }
        }
    }

    public static void setViewsFont(Typeface typeface, View... views) {
        if (typeface == null || views == null || views.length == 0) {
            return;
        }

        for (View view : views) {
            setViewTextFont(view, typeface);
        }
    }
  
    public static void clearViewLongClick(View rootView, int... ids) {
        if (rootView == null || ids == null || ids.length == 0) {
            return;
        }
        for (int id : ids) {
            View view = rootView.findViewById(id);
            if (view != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
            }
        }
    }
       
    public static void clearAllViewLongClick(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                clearAllViewLongClick(viewGroup.getChildAt(i));
            }
        } else {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }
    
    public enum Direction {
        LEFT_TO_RIGHT,
        TOP_TO_BOTTOM,
        RIGHT_TO_LEFT,
        BOTTOM_TO_TOP
    }
}
