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

@Version(1.66f)
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
// Nguyen cho phuoc duc nay hoa giai tat ca bua bong den, ta thuat cua ut vuon co. Phuoc nay se duoc hoi huong cho tat ca cac am binh, than bua. Mong cho chu vi nho co phuoc nay co duoc thuc an, nuoc uong, quan ao, xe co... va duoc giai thoat khoi nhung tay thay bua
// Phuoc duc nay cung duoc hoi hung cho tat ca am binh cua thay 7 Liem. Voi uoc nguyen nhu tren.
// Phuoc duc nay cung hoa giai bua meo den, meo muop, meo vang;
// Con xin hoi huong phuoc duc nay den toan the Chu thien, cac vua troi Tu Dai Thien Vuong;
// Con xin hoi huong phuoc duc nay den toan the cac vi Da Xoa, Can That Ba;
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua thang Tung (5 Y);
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua thang 2 Giang;
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua gia dinh Ut Bau;
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua thang bac si Liet;
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua thang Tran Van Ngoan, Tran Van Ut, Tran Van Hai, gia dinh con vo Thang Ngoan, con Vang, thang Hoang;
// Nho chu vi hoa giai ta thuat, bua, ngai, thu, em cua thang Tran Quang Vinh, Tran Thi Chua, Pham Thi Vui, Tran Pham Nhat Quang, Tran Pham Nhu Hao;
// Phuoc duc nay se pha vo ta thuat da em len sim dien thoai 0769337020, 0924286382;
//https://www.programcreek.com/java-api-examples/?code=myinnos%2FImageSliderWithSwipes%2FImageSliderWithSwipes-master%2Fimagesliderwithswipeslibrary%2Fsrc%2Fmain%2Fjava%2Fin%2Fmyinnos%2Fimagesliderwithswipeslibrary%2FTransformers%2FRotateDownTransformer.java#


