package com.tummosoft.android.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import com.xuexiang.xui.utils.XToastUtils;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheetItemView;
import com.xuexiang.xui.widget.popupwindow.bar.CookieBar;

@BA.ShortName("xBottomSheet")
@BA.Events(values = {"onShow (di as xDialogInterface)", "OnKeyListener (di as xDialogInterface)"})
public class xBottomSheet extends AbsObjectWrapper< BottomSheet.BottomGridSheetBuilder> {
     private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";
    final int TAG_SHARE_WECHAT_FRIEND = 0;
    final int TAG_SHARE_WECHAT_MOMENT = 1;
    final int TAG_SHARE_WEIBO = 2;
    final int TAG_SHARE_CHAT = 3;
    final int TAG_SHARE_LOCAL = 4;
        
    public void initialize(final BA ba, String event) {
        setObject(new BottomSheet.BottomGridSheetBuilder(ba.activity));
        _ba = ba;        
        this.eventname = event.toLowerCase();       
            
             getObject().setOnSheetItemClickListener(new OnSheetItemClickListener() {
            @Override
            public void onClick(BottomSheet bs, BottomSheetItemView bsiv) {
                
            }
            
        });
            
             
    }
    
    public void addView(View view) {        
        getObject().addItem(view, TAG_SHARE_CHAT);
         
    }
    
    public void Show() {    
       CookieBar.builder(_ba.activity)
                        .setTitle("Hello world")
                        .setMessage("Thank alot")
                        .show();
    }
    
   
   
}
