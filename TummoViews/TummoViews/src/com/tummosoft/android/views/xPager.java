package com.tummosoft.android.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xPager")
@BA.Events(values = {"onTouch(Action As Int, X As Float, Y As Float, MotionEvent As Object) As Boolean", "Click(v as View)"})
public class xPager extends AbsObjectWrapper<Pager> {
      private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";
   private Pager pager;
      
    public void initialize(final BA ba, String event, int width, int height) {
        pager = new Pager(ba.context, width, height);     
        setObject(pager);
        _ba = ba;
        this.eventname = event.toLowerCase();
        getObject().setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int action = event.getAction();
                boolean IsHandled = true;
                if (_ba.subExists(eventname + "_ontouch")) {
                    Object Result = _ba.raiseEvent(xPager.this, eventname + "_ontouch", new Object[] {action, event.getX(), event.getY(), event});
                    if (Result instanceof Boolean)
                    IsHandled = (boolean) Result;
                }
                return IsHandled;
            }
        });
    }
    
    @BA.ShortName("xMotionEvent")
    public static class xMotionEvent extends AbsObjectWrapper<MotionEvent> {
        public static int ACTION_MOVE = MotionEvent.ACTION_MOVE;
        public static int ACTION_UP = MotionEvent.ACTION_UP;
        public static int ACTION_DOWN = MotionEvent.ACTION_DOWN;
        
        public xMotionEvent() {}
        public void initialize(final BA ba, String event, int width, int height) {
            
        }
        
        public float getX() {
            return getObject().getX();
        }
        
        public float getY() {
            return getObject().getY();
        }
        
         public int getAction() {
            return getObject().getAction();
        }
    }
    
    public void calcCornerXY(float x, float y) {
        getObject().calcCornerXY(x, y);
    }
    
    public void postInvalidate() {
        getObject().postInvalidate();
    }
    
    
    public void abortAnimation() {
        getObject().abortAnimation();
    }

    public boolean doTouchEvent(MotionEvent event) {
       return getObject().doTouchEvent(event);
    }

    /**
     * Solve the coordinates of the intersection of lines P1P2 and P3P4
     */
    public PointF getCross(PointF P1, PointF P2, PointF P3, PointF P4) {
        return getObject().getCross(P1, P2, P3, P4);        
    }
    
    public void setBitmaps(Bitmap bm1, Bitmap bm2) {
       getObject().setBitmaps(bm1, bm2);
    }

    public void setScreen(int w, int h) {
        getObject().setScreen(w, h);
    }

   
    public void drawCurrentPageShadow(Canvas canvas) {
       getObject().drawCurrentPageShadow(canvas);
    }
    
    public void computeScroll() {
        getObject().computeScroll();
    }

  
    public boolean canDragOver() {
        return getObject().canDragOver();
    }

  /**
* Whether to flip from left to right
*/
    public boolean DragToRight() {
        return  getObject().DragToRight();
    }
}
