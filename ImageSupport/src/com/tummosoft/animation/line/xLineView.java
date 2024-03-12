package com.tummosoft.animation.line;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.PanelWrapper;

@BA.ShortName("xLineView")
public class xLineView  extends AbsObjectWrapper<LineView> {
    private static Context baContext;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private BA _ba;
    private int idx = 0;
    
    public void initialize(BA ba, int strokeHeight) {
        _ba = ba;
        baContext = _ba.context;
        setObject(new LineView(baContext));
        getObject().setId(View.generateViewId());        
        getObject().setStrokeHeight(strokeHeight);
    }
    
    public View getView() {
        return getObject();
    }
}
