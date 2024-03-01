package com.tummosoft.layout;

import android.content.Context;
import android.graphics.Color;
import android.widget.TableLayout;
import androidx.constraintlayout.motion.widget.MotionLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@BA.ActivityObject
@BA.ShortName("xMotionLayout")
@BA.Events(values = {"CellClick(id as int, row as int, col as int)"})
@Hide
public class xMotionLayout extends AbsObjectWrapper<MotionLayout> implements Common.DesignerCustomView {
     private static Context baContext;
    private String _eventName;    
    private BA _ba;
    private TableLayout.LayoutParams laywrapper;
    private int[] _width;
    private int _height = 0;
    private int _id = 0;
    private int _row = 0;
    private int _col = 0;

    @Override
    @BA.Hide
    public void _initialize(BA ba, Object o, String event) {
        _ba = ba;
        baContext = ba.context;
        _eventName = event.toLowerCase();
    }

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new MotionLayout(baContext));
        laywrapper = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        getObject().setLayoutParams(laywrapper);
        getObject().setBackgroundColor(Color.DKGRAY);
        base.RemoveAllViews();
        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
    }
    
    public void AddLayout(int id) {
        
    }
}
