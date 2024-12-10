package com.tummosoft.android.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.objects.PanelWrapper;
import java.util.ArrayList;
import java.util.List;

@Version(1.64f)
@BA.ShortName("xRelativeLayout")
public class xRelativeLayout extends AbsObjectWrapper<RelativeLayout> {

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
        setObject(new RelativeLayout(baContext));
        LinearLayout.LayoutParams LinearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayoutParams.weight = 1.0f;
        getObject().setLayoutParams(LinearLayoutParams);
    }

    public void setBackground(Drawable background) {
        getObject().setBackground(background);
    }

    public void setAlpha(float value) {
        getObject().setAlpha(value);
    }

    private final List<View> views = new ArrayList<>();

    public void AddView(View view, int leftMargin, int topMargin, int rightMargin, int bottomMargin, int width, int height) {
        LinearLayout.LayoutParams LinearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setId(View.generateViewId());
        LinearLayoutParams.topMargin = topMargin;
        LinearLayoutParams.bottomMargin = bottomMargin;
        LinearLayoutParams.leftMargin = leftMargin;
        LinearLayoutParams.rightMargin = rightMargin;
        LinearLayoutParams.width = width;
        LinearLayoutParams.height = height;
        getObject().addView(view);
    }

     public void RemoveViewAt(int index) {
        getObject().removeViewAt(index);
    }
    
    public void RemoveView(View view) {
        getObject().removeView(view);
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
        anim.setAnimationListener(new Animation.AnimationListener() {
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
// Con xin hoi huong phuoc duc nay den toan the Chu thien, cac vua troi Tu Dai Thien Vuong
// Nho chu vi hoa giai ta thuat cua thang Phong (con nuoi Ut Vuon Co);
// Nho chu vi hoa giai ta thuat cua gia dinh thang 5 Tiet, thang Dung, thang Si, anh chi em ben vo;
// Nho chu vi hoa giai ta thuat cua thay bua 3 Nhang;
// Nho chu vi hoa giai ta thuat cua gia dinh Huu Ky (2 Chien), tat ca con gai va re cua Tran Van Chien, tat ca chau dau chau re cua Tran Van Chien;
// Hoa giai ta thuat den tu gia dinh thang Hung (8 Duc), thang Dieu (8 Duc), vo thang Hung, anh chi em ben vo thang Hung;
// Hoa giai ta thuat cua thang Tran Van Ngoan, thang Ut Vuon Co, thang Tran Van Hai tat ca nhung nguoi do thang Ngoan thue muon;
// Hoa giai ta thuat cua con Pham Thi Vui, thang Tran Quang Vinh, ba Tran Thi Chia;
//https://www.programcreek.com/java-api-examples/?code=myinnos%2FImageSliderWithSwipes%2FImageSliderWithSwipes-master%2Fimagesliderwithswipeslibrary%2Fsrc%2Fmain%2Fjava%2Fin%2Fmyinnos%2Fimagesliderwithswipeslibrary%2FTransformers%2FRotateDownTransformer.java#

