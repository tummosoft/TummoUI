package com.tummosoft.android.views;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.button.shinebutton.ShineButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.validation.RegexpValidator;
import com.xuexiang.xui.widget.edittext.verify.VerifyCodeEditText;

import java.util.ArrayList;
import java.util.Locale;

@BA.ShortName("xShadowButton")
@BA.Events(values = {"Click (view as Object)", "LongClick (view as Object)"})
public class xShadowButton extends AbsObjectWrapper<ShadowButton> {
    
    private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";

    public void initialize(final BA ba, String event) {
        setObject(new ShadowButton(ba.context));   
                
        _ba = ba;
        this.eventname = event.toLowerCase();
      
        getObject().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ba.raiseEventFromUI(xShadowButton.this, eventname + "_click", v);
            }
        });
        
        getObject().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                _ba.raiseEventFromUI(xShadowButton.this, eventname + "_longclick", view);
                return false;
            }
        });
    }
    
      public ShadowButton GetView() {
        return getObject();
    }
      
    public void setRadius(int value) {       
        getObject().setRadius(value);        
    }
    
    public void setPressedColor(String value) {       
        getObject().setPressedColor(Color.parseColor(value));
    }    
    
    public void setForeground(Drawable foreground) {               
        getObject().setForeground(foreground);
        getObject().setForegroundGravity(0);
    }
    
    public void setShadowLayer(float radius, float dx, float dy, String color) {                       
        getObject().setShadowLayer(radius, dx, dy, Color.parseColor(color));
    }
    
    public void setAlpha(float value) {       
        getObject().setAlpha(value);    
        getObject().setEllipsize(TextUtils.TruncateAt.MIDDLE);
    }
    
    public void setShapeType(int value) {       
        getObject().setShapeType(value);
    }
    
    public void setUnpressedColor(String value) {       
        getObject().setUnpressedColor(Color.parseColor(value));
    }
    
    public void setElevation(float value) {       
        getObject().setElevation(value);        
    }
    
    public void setBackgroundResource(int value) {           
        getObject().setBackgroundResource(value);
    }
    
    public void setForegroundGravity(int value) {           
        getObject().setForegroundGravity(value);
    }
    
    public void setHeight(int value) {       
        getObject().setHeight(value);     
    }
    
    public void setWidth(int value) {       
        getObject().setWidth(value);
    }
    
    public void setGravity(int value) {       
        getObject().setGravity(value);         
    }
    
    public void setText(String value) {
        getObject().setText(value);        
    }
    
    public void setTextColor(String value) {
        getObject().setTextColor(Color.parseColor(value));
    }
    
     public void setTagName(String value) {
        getObject().setTag(value);
    }
     
    public String getTagName() {
        return (String) getObject().getTag();
    }

    public void setBackgroundColor(String color) {
        getObject().setBackgroundColor(android.graphics.Color.parseColor(color));
    }
    
    public void setImageResourceID(int ResourceID) {
        getObject().setBackgroundResource(ResourceID);
    }

    public void setBackgroundDrawable(Drawable value) {
        getObject().setBackground(value);
        getObject().setTextSize(0);
    }

    public void setTextSize(float value) {        
        getObject().setTextSize(value);
    }
    
    public void setTooltipText(String value) {                
        getObject().setTooltipText(value);
        getObject().setTextAlignment(0);
    }
    
    public void setTextAlignment(int value) {                    
        getObject().setTextAlignment(value);
        getObject().setSoundEffectsEnabled(hasfocus);
    }
    
    public void setSoundEffectsEnabled(boolean value) {                            
        getObject().setSoundEffectsEnabled(value);
        
    }
            
    public void setPressed(boolean value) {                            
        getObject().setPressed(value);
    }
    
    public void setSingleLine(boolean value) {                            
        getObject().setSingleLine(value);
    }
    
    public void setPadding(int left, int top, int right, int bottom) {
        getObject().setPadding(left, top, right, bottom);
    }
        
}
