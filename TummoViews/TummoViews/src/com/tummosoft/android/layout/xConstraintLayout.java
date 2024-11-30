package com.tummosoft.android.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;

@BA.ShortName("xConstraintLayout")
public class xConstraintLayout extends AbsObjectWrapper<ConstraintLayout> {

    public static final int ConstraintSet_TOP = ConstraintSet.TOP;
    public static final int ConstraintSet_MATCH_CONSTRAINT_SPREAD = ConstraintSet.MATCH_CONSTRAINT_SPREAD;
    public static final int ConstraintSet_BOTTOM = ConstraintSet.BOTTOM;
    public static final int ConstraintSet_PARENT_ID = ConstraintSet.PARENT_ID;
    public static final int ConstraintSet_RIGHT = ConstraintSet.RIGHT;
    public static final int ConstraintSet_LEFT = ConstraintSet.LEFT;

    public static final int ConstraintSet_BASELINE = ConstraintSet.BASELINE;
    public static final int ConstraintSet_CHAIN_PACKED = ConstraintSet.CHAIN_PACKED;
    public static final int ConstraintSet_CHAIN_SPREAD = ConstraintSet.CHAIN_SPREAD;
    public static final int ConstraintSet_CHAIN_SPREAD_INSIDE = ConstraintSet.CHAIN_SPREAD_INSIDE;
    public static final int ConstraintSet_CIRCLE_REFERENCE = ConstraintSet.CIRCLE_REFERENCE;
    public static final int ConstraintSet_END = ConstraintSet.END;
    public static final int ConstraintSet_GONE = ConstraintSet.GONE;
    public static final int ConstraintSet_HORIZONTAL = ConstraintSet.HORIZONTAL;
    public static final int ConstraintSet_HORIZONTAL_GUIDELINE = ConstraintSet.HORIZONTAL_GUIDELINE;
    public static final int ConstraintSet_INVISIBLE = ConstraintSet.INVISIBLE;
    public static final int ConstraintSet_MATCH_CONSTRAINT = ConstraintSet.MATCH_CONSTRAINT;
    public static final int ConstraintSet_MATCH_CONSTRAINT_PERCENT = ConstraintSet.MATCH_CONSTRAINT_PERCENT;
    public static final int ConstraintSet_MATCH_CONSTRAINT_WRAP = ConstraintSet.MATCH_CONSTRAINT_WRAP;
    public static final int ConstraintSet_ROTATE_LEFT_OF_PORTRATE = ConstraintSet.ROTATE_LEFT_OF_PORTRATE;
    public static final int ConstraintSet_START = ConstraintSet.START;
    public static final int ConstraintSet_UNSET = ConstraintSet.UNSET;
    public static final int ConstraintSet_VERTICAL = ConstraintSet.VERTICAL;
    public static final int ConstraintSet_VERTICAL_GUIDELINE = ConstraintSet.VERTICAL_GUIDELINE;
    public static final int ConstraintSet_VISIBILITY_MODE_IGNORE = ConstraintSet.VISIBILITY_MODE_IGNORE;
    public static final int ConstraintSet_VISIBILITY_MODE_NORMAL = ConstraintSet.VISIBILITY_MODE_NORMAL;
    public static final int ConstraintSet_WRAP_CONTENT = ConstraintSet.WRAP_CONTENT;

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
        setObject(new ConstraintLayout(baContext));
        getObject().setId(View.generateViewId());
        ConstraintLayout.LayoutParams constraintLayoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getObject().setLayoutParams(constraintLayoutParams);
          int count = getObject().getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getObject().getChildAt(i);
            if (view.getId() == -1) {
                view.setId(View.generateViewId());
            }
        }
    }

    public int getID() {
        return getObject().getId();
    }
    
    public int getChildCount() {
        return getObject().getChildCount();
    }
    
     public void AddView(View view) {
        getObject().addView(view);        
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
// Phuoc duc nay cung hoa giai bua meo den, meo muop, mao vang
