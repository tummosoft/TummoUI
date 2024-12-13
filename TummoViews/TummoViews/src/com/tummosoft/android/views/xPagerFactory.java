package com.tummosoft.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import anywheresoftware.b4a.AbsObjectWrapper;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;

@BA.ShortName("xPagerFactory")
public class xPagerFactory extends AbsObjectWrapper<PagerFactory> {

    public void initialize(final BA ba, String event) {
        setObject(new PagerFactory(ba.context));
    }
    
    public void onDraw(CanvasWrapper c) {
        getObject().onDraw(c.canvas);
    }
    
    public void onDraw2(CanvasWrapper c, Bitmap bitmap) {
        getObject().onDraw(c.canvas, bitmap);
    }
    
    public void setBgBitmap(Bitmap BG) {
        getObject().setBgBitmap(BG);
    }
}
