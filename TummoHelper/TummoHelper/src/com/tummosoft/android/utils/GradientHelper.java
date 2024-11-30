package com.tummosoft.android.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import static android.graphics.drawable.GradientDrawable.LINE;
import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;
import static android.graphics.drawable.GradientDrawable.OVAL;
import static android.graphics.drawable.GradientDrawable.RADIAL_GRADIENT;
import static android.graphics.drawable.GradientDrawable.RECTANGLE;
import static android.graphics.drawable.GradientDrawable.RING;
import static android.graphics.drawable.GradientDrawable.SWEEP_GRADIENT;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.shapes.Shape;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import java.util.ArrayList;

@BA.ShortName("GradientHelper")
public class GradientHelper extends AbsObjectWrapper<GradientDrawable> {

    private static Context baContext;
    private BA _ba;
    private float[] radius;
    private int strokeWidth;
    private String strokeColor;
    public static int TYPE_SWEEP_GRADIENT = 0;
    public static int TYPE_RING = 1;
    public static int TYPE_RECTANGLE = 2;
    public static int TYPE_RADIAL_GRADIENT = 3;
    public static int TYPE_OVAL = 4;
    public static int TYPE_LINEAR_GRADIENT = 5;
    public static int TYPE_LINE = 6;

    public void initialize(BA ba, int gradientType) {
        _ba = ba;
        baContext = _ba.context;
        setObject(new GradientDrawable());
        //,,,,,
        switch (gradientType) {
            case 0:
                getObject().setGradientType(SWEEP_GRADIENT);
            case 1:
                getObject().setGradientType(RING);
            case 2:
                getObject().setGradientType(RECTANGLE);
            case 3:
                getObject().setGradientType(RADIAL_GRADIENT);
            case 4:
                getObject().setGradientType(OVAL);
            case 5:
                getObject().setGradientType(LINEAR_GRADIENT);
            case 6:
                getObject().setGradientType(LINE);
        }

    }
    //{mTopLeftRadius, mTopLeftRadius, mTopRightRadius, mTopRightRadius, mBottomRightRadius, mBottomRightRadius, mBottomLeftRadius, mBottomLeftRadius}

    public void setRadius(float[] radius) {
        this.radius = radius;
        getObject().setCornerRadii(radius);
    }

    public void setGradientCenter(int x, int y) {
        getObject().setGradientCenter(x, y);
    }

    public void setAlpha(int value) {
        getObject().setAlpha(value);
    }

    public void setColors(String[] arrayColors) {
        if (arrayColors.length > 0) {
            int[] itemColor = new int[arrayColors.length];
            for (int i = 0; i < arrayColors.length; i++) {
                itemColor[i] = Color.parseColor(arrayColors[i]);
            }
            getObject().setColors(itemColor);
        }        
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
    
    @BA.ShortName("xLayerDrawable")
    static public class xLayerDrawable extends AbsObjectWrapper<LayerDrawable> {

        private static Context _context;
        private static BA _ba;

        public void initialize(final BA ba, Drawable[] layers) {
            _context = ba.context;
            _ba = ba;
            setObject(new LayerDrawable(layers));

        }

        public void getLayerInsetLeft(int index, int[] padding) {
            getObject().setLayerInset(index, padding[0], padding[1], padding[2], padding[3]);
        }
        
         public void setLayerGravity(int index, int Gravity) {
            getObject().setLayerGravity(index, Gravity);            
        }
         
        public void getLayerInseRight(int index, int height) {
            getObject().setLayerHeight(index, height);
        }
        
        public void getLayerInseWidth(int index, int width) {
            getObject().setLayerWidth(index, width);
            getObject().setLayerSize(index, width, width);
        }
        
        public void setLayerSize(int index, int width, int height) {            
            getObject().setLayerSize(index, width, height);            
        }
        
        public void setDrawableByLayerId(int index, Drawable drawable) {                        
            getObject().setDrawableByLayerId(index, drawable);
        }
    }
}
//https://nextui.org/docs/customization/colors