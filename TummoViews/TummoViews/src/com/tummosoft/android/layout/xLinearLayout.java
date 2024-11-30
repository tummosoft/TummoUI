package com.tummosoft.android.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;
import java.util.ArrayList;
import java.util.List;

@BA.ShortName("xLinearLayout")
@BA.Events(values = {"onAnimationUpdate(angle as float),onAnimationStart(),onAnimationEnd(),onAnimationRepeat()"})
public class xLinearLayout extends AbsObjectWrapper<LinearLayout> {

    private static Context baContext;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private BA _ba;
    private int idx = 0;

    public static final int ROW_VERTICAL = LinearLayout.VERTICAL;
    public static final int ROW_HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int COL_CENTER_VERTICAL = Gravity.CENTER_VERTICAL;
    public static final int COLUMN_BOTTOM = Gravity.BOTTOM;
    public static final int COLUMN_LEFT = Gravity.LEFT;
    public static final int COLUMN_RIGHT = Gravity.RIGHT;
    public static final int COLUMN_TOP = Gravity.TOP;
    public static final int GRAVITY_CENTER = Gravity.CENTER;
    public static final int GRAVITY_BOTTOM = Gravity.BOTTOM;
    public static final int GRAVITY_LEFT = Gravity.LEFT;
    public static final int GRAVITY_FILL = Gravity.FILL;
    public static final int GRAVITY_CENTER_HORIZONTAL = Gravity.CENTER_HORIZONTAL;
    public static final int GRAVITY_CENTER_VERTICAL = Gravity.CENTER_VERTICAL;
    public static final int GRAVITY_START = Gravity.START;
    public static final int GRAVITY_TOP = Gravity.TOP;
    public static final int SIZE_MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int SIZE_WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    public void initialize(BA ba, String event, int rowDirection, int columnDirection, int alignItems, int width, int height) {
        _ba = ba;
        baContext = _ba.context;
        setObject(new LinearLayout(baContext));
        LinearLayout.LayoutParams LinearLayoutParams = new LinearLayout.LayoutParams(width, height);
        LinearLayoutParams.weight = 1.0f;
        getObject().setLayoutParams(LinearLayoutParams);
        getObject().setOrientation(columnDirection);
        getObject().setVerticalGravity(alignItems);
        getObject().setLayoutAnimationListener(new Animation.AnimationListener() {
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

    private boolean hasAnimation = false;
    private int mANIM;
    private int mDuration;

    public void setElevation(float value) {
        getObject().setElevation(value);

    }

    public void Animation(int ANIM, int Duration) {
        hasAnimation = true;
        mANIM = ANIM;
    }

    public void setBackground(Drawable background) {
        getObject().setBackground(background);
        getObject().setDividerDrawable(background);
    }

    public void setAlpha(float value) {
        getObject().setAlpha(value);
    }

    private final List<View> views = new ArrayList<>();

    public void AddView(View view, int leftMargin, int topMargin, int rightMargin, int bottomMargin, int width, int height) {
        LinearLayout.LayoutParams LinearLayoutParams = new LinearLayout.LayoutParams(width, height);
        view.setId(View.generateViewId());
        if (hasAnimation == true) {
            StartAnim(mDuration, mANIM);
        }
        LinearLayoutParams.topMargin = topMargin;
        LinearLayoutParams.bottomMargin = bottomMargin;
        LinearLayoutParams.leftMargin = leftMargin;
        LinearLayoutParams.rightMargin = rightMargin;
        
        LinearLayoutParams.width = width;
        LinearLayoutParams.height = height;
        view.setLayoutParams(LinearLayoutParams);
        getObject().addView(view);
    }
    
    public int getViewID(int index) {
        int vid = 0;
        for (int i = 0; i < views.size(); i++) {
            if (i == index) {
                vid = views.get(i).getId();
            }
        }
        return vid;
    }

    public View getView() {
        for (int i = 0; i < views.size(); i++) {
            getObject().addView(views.get(i));
        }
        return getObject();
    }

    public static final int ANIM_NONE = -1;
    public static final int ANIM_BOTTOM_TOP = 0;
    public static final int ANIM_TO_RIGHT = 1;
    public static final int ANIM_FROM_LET = 2;

    private void StartAnim(int Duration, int ani) {
        Animation anim = null;
        if (ani == 0) {
            anim = AnimationUtils.makeOutAnimation(baContext, true);
        } else if (ani == 1) {
            anim = AnimationUtils.makeInAnimation(baContext, visible);
        } else if (ani == 1) {
            anim = AnimationUtils.makeInAnimation(baContext, visible);
        }

        anim.setDuration(Duration);
        getObject().startAnimation(anim);
        anim.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        });

    }

    private boolean visible = true;

    public void setEnabled(boolean value) {
        getObject().setEnabled(value);
    }

    public void stopAnimation() {
        getObject().clearAnimation();
    }

    public void setColor(int color) {
        getObject().setBackgroundColor(color);
    }

    public int getID() {
        return getObject().getId();
    }

    public int getChildCount() {
        return getObject().getChildCount();
    }

    public View getViewAt(int index) {
        return getObject().getChildAt(index);
    }

    public void GenerateViewAllId() {
        int count = getObject().getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getObject().getChildAt(i);
            if (view.getId() == -1) {
                view.setId(View.generateViewId());
            }
        }
    }

    public void ClearView(int viewID) {
        int count = getObject().getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getObject().getChildAt(i);
            if (view.getId() == viewID) {
                getObject().removeViewAt(i);
            }
        }
    }

}
