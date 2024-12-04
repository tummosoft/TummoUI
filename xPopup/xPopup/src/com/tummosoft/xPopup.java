package com.tummosoft;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import com.lxj.xpopup.core.HorizontalAttachPopupView;

@BA.ShortName("xImageView")
public class xPopup extends HorizontalAttachPopupView {
     private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";
    private View view = null;
    private boolean ShadowBg = false;
    private String[] listItem = {};
            
    public void initialize(final BA ba, String event) {
        _ba = ba;
         this.eventname = event.toLowerCase();
        
        
    }
    
   public xPopup(Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_zan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XPopupApp.context, "赞", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
        findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(XPopupApp.context, "评论", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
    }
}
