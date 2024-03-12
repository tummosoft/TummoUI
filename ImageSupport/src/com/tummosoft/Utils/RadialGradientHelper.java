package com.tummosoft.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import androidx.constraintlayout.widget.ConstraintLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;

public class RadialGradientHelper extends AbsObjectWrapper<GradientDrawable> {

    private static Context baContext;
    private BA _ba;
    private float[] radius;
    private int strokeWidth;
    private String strokeColor;

    public void initialize(BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        setObject(new GradientDrawable());
    }
    //{mTopLeftRadius, mTopLeftRadius, mTopRightRadius, mTopRightRadius, mBottomRightRadius, mBottomRightRadius, mBottomLeftRadius, mBottomLeftRadius}

    public void setRadius(float[] radius) {
        this.radius = radius;
        getObject().setCornerRadii(radius);
    }
    
     public void setGradientCenter(int x, int y) {
        getObject().setGradientCenter(x, y);
    }

    public void setColors(String[] arrayColors) {
        int[] itemColor = {};
        for (int i = 0; i < arrayColors.length; i++) {
            itemColor[i] = Color.parseColor(arrayColors[i]);
        }
        getObject().setColors(itemColor);         
    }

    public void setStroke(int strokeWidth, String strokeColor) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        getObject().setStroke(strokeWidth, Color.parseColor(strokeColor));
    }

    public final static int Orientation_TOP_BOTTOM = 0;
    public final static int Orientation_BL_TR = 1;
    public final static int Orientation_BOTTOM_TOP = 2;
    public final static int Orientation_BR_TL = 3;
    public final static int Orientation_LEFT_RIGHT = 4;
    public final static int Orientation_RIGHT_LEFT = 5;
    public final static int Orientation_TR_BL = 6;
    public final static int Orientation_TL_BR = 7;

    public void setOrientation(int orient) {
        GradientDrawable.Orientation op = null;

        switch (orient) {
            case 0:
                op = GradientDrawable.Orientation.TOP_BOTTOM;
            case 1:
                op = GradientDrawable.Orientation.BL_TR;
            case 2:
                op = GradientDrawable.Orientation.BOTTOM_TOP;
            case 3:
                op = GradientDrawable.Orientation.BR_TL;
            case 4:
                op = GradientDrawable.Orientation.LEFT_RIGHT;
            case 5:
                op = GradientDrawable.Orientation.RIGHT_LEFT;
            case 6:
                op = GradientDrawable.Orientation.TR_BL;
            case 7:
                op = GradientDrawable.Orientation.TL_BR;

        }

        getObject().setOrientation(op);
    }
    
    public GradientDrawable getColor() {
        return getObject();
    }
}
