package com.tummosoft.android.layout;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.core.view.MotionEventCompat;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ActivityObject;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.List;

import anywheresoftware.b4a.objects.collections.Map;

@ActivityObject
@ShortName("xTableLayout")
@BA.Events(values = {"CellClick(id as int, row as int, col as int)"})
public class xTableLayout extends AbsObjectWrapper<TableLayout> implements Common.DesignerCustomView {

    public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;
    public static final int ACTION_UP = MotionEvent.ACTION_UP;
    public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;

    private static Context baContext;
    private String _eventName;    
    private BA _ba;
    private TableLayout.LayoutParams tableParams;
    private int[] _width;
    private int _height = 0;
    private int _id = 0;
    private int _row = 0;
    private int _col = 0;

    @Override
    @Hide
    public void _initialize(BA ba, Object o, String event) {
        _ba = ba;
        baContext = ba.context;
        _eventName = event.toLowerCase();
    }

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new TableLayout(baContext));
        tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
        getObject().setLayoutParams(tableParams);
        getObject().setBackgroundColor(Color.DKGRAY);
        base.RemoveAllViews();
        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
    }
    
    public void setColWidth(int[] value) {
        _width = value;
    }
    
    public void setColHeight(int value) {
        _height = value;
    }
    
    public void setPadding(int value) {
        getObject().setPadding(value, value, value, value);
    }
    
    private int _rowcolor;
    public void setRowColor(int color) {        
        _rowcolor = color;
    }
    
    private int _viewcolor;
    public void setViewColor(int color) {
        _viewcolor = color;
    }
    
     public void RemoveViewAt(int index) {
        getObject().removeViewAt(index);
    }
    
    public void RemoveView(View view) {
        getObject().removeView(view);
    }
    
    public void AddRows(List items) {
        getObject().setStretchAllColumns(true);
        getObject().setShrinkAllColumns(true);

        TableRow rowitems = new TableRow(baContext);
       // TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        rowitems.setLayoutParams(tableParams);                
        rowitems.setPadding(_padding, _padding, _padding, _padding);
        rowitems.setGravity(Gravity.LEFT);
        rowitems.setBackgroundColor(_rowcolor);
        _row = _row + 1;    
        _col = 0;
        for (int i = 0; i < items.getSize(); i++) {
            View view = (View) items.Get(i);
            view.setId(_id);              
            _id = _id + 1;
            _col = _col + 1;
            TableRow.LayoutParams rowParams;
            if (_height > 0) {
                rowParams = new TableRow.LayoutParams( _width[i], _height);  
            } else {
                rowParams = new TableRow.LayoutParams( _width[i], TableRow.LayoutParams.WRAP_CONTENT);  
            }                     
            view.setLayoutParams(rowParams);
            
             view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                int action = MotionEventCompat.getActionMasked(ev);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):                      
                        _ba.raiseEventFromUI(v, _eventName + "_cellclick", _id, _row, _col);
                        return true;
                    case (MotionEvent.ACTION_MOVE):
                        return true;
                    case (MotionEvent.ACTION_UP):                        
                        return true;
                    case (MotionEvent.ACTION_CANCEL):

                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):

                        return true;
                }

                return true;
            }
        });
            
           rowitems.addView(view, i);                       
        }
        getObject().addView(rowitems);
    }

    private int _padding = 0;

    public void RowPadding(int value) {
        _padding = value;
    }
    
    public void AddRow(View view) {
        getObject().setStretchAllColumns(true);
        getObject().setShrinkAllColumns(true);
        getObject().addView(view);
    }

    public int getHeight() {
        return getObject().getHeight();
    }

    public int getWidth() {
        return getObject().getWidth();
    }

    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }

}
// Con xin hoi huong phuoc duc nay den toan the Chu thien, cac vua troi Tu Dai Thien Vuong
// Nho chu vi hoa giai ta thuat cua thang Phong (con nuoi Ut Vuon Co);
// Hoa giai ta thuat cua thang Ngoan, thang Ut Vuon Co, thang Hai;
// Hoa giai ta thuat den tu thang Tung (5 Y);
// Hoa giai ta thuat cua con Pham Thi Vui;
// Hoa giai ta thuat den tu gia dinh 2 Chien;
// Hoa giai ta thuat den tu gia dinh thang Hung (8 Duc), thang Dieu (8 Duc), vo thang Hung, anh chi em ben vo thang Hung;
// Hoa giai tat ca ta thuat den tu thang thay bua o khu vuc gan nha ong Ut On;
