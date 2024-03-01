package com.tummosoft.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import com.tummosoft.control.line.DividerDrawable;
import com.tummosoft.control.line.DividerLayout;
import com.tummosoft.control.line.DividerUtils;

public class xLineView implements Common.DesignerCustomView {
        private static Context baContext;
    private String _eventName;
    private int _width;
    private BA _ba;
    private float mRadius = 0;
           
    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        
        
        DividerDrawable dividerDrawable = new DividerDrawable();
        dividerDrawable
                .setStrokeWidth(10)
                .setColor(0xFFFFFFFF)
                .getLayout()
                .setCenter(DividerLayout.CENTER_VERTICAL);
        Drawable line = DividerUtils.addDividersTo(dividerDrawable);
        base.setBackground(line);
                
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setMarginLeftDp(50)
//                .setCenter(DividerLayout.CENTER_VERTICAL);
//        DividerUtils.addDividersTo(findViewById(R.id.linear2), dividerDrawable);
//
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
//                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
//                .setMarginTopDp(16)
//                .setMarginBottomDp(16);
//        DividerUtils.addDividersTo(findViewById(R.id.text_3_1), dividerDrawable);
//        DividerUtils.addDividersTo(findViewById(R.id.text_3_3), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
//                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
//                .setMarginTopDp(32)
//                .setMarginBottomDp(32);
//        DividerUtils.addDividersTo(findViewById(R.id.text_3_2), dividerDrawable);
//
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setMarginLeftDp(16)
//                .setLengthDp(100);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_1), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
//                .setCenter(DividerLayout.CENTER_VERTICAL)
//                .setLengthDp(46);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_1), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setCenter(DividerLayout.CENTER_HORIZONTAL)
//                .setLengthDp(100);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_2), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setOrientation(DividerLayout.ORIENTATION_VERTICAL)
//                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
//                .setCenter(DividerLayout.CENTER_VERTICAL)
//                .setLengthDp(46);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_2), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setAlign(DividerLayout.ALIGN_PARENT_RIGHT)
//                .setMarginRightDp(16)
//                .setLengthDp(100);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_3), dividerDrawable);
//        dividerDrawable = new DividerDrawable();
//        dividerDrawable
//                .setStrokeWidth(10)
//                .setColor(0xFFFFFFFF)
//                .getLayout()
//                .setCenter(DividerLayout.CENTER_HORIZONTAL)
//                .setLengthDp(300);
//        DividerUtils.addDividersTo(findViewById(R.id.text_4_4), dividerDrawable);
        
       // base.setColor(Color.TRANSPARENT);
        
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
         baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;        
    }
}
