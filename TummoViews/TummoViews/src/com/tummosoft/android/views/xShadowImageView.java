/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tummosoft.android.views;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowImageView;

@BA.ShortName("xShadowImageView")
@BA.Events(values = {"Click (view as Object)", "LongClick (view as Object)"})
public class xShadowImageView extends AbsObjectWrapper<ShadowImageView> {
    private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";

    public void initialize(final BA ba, String event) {
        setObject(new ShadowImageView(ba.context));   
        
        _ba = ba;
        this.eventname = event.toLowerCase();
      
        getObject().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _ba.raiseEventFromUI(xShadowImageView.this, eventname + "_click", v);
            }
        });

        getObject().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {
                _ba.raiseEventFromUI(xShadowImageView.this, eventname + "_touch", view);
                return false;
            }

        });

        getObject().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                _ba.raiseEventFromUI(xShadowImageView.this, eventname + "_longclick", view);
                return false;
            }
        });
    }
    
      public ShadowImageView GetView() {
        return getObject();
    }
    
    public void setPressedColor(String value) {       
        getObject().setPressedColor(Color.parseColor(value));
        
    }    
    
    public void setBackgroundDrawable(Drawable background) {       
        getObject().setBackgroundDrawable(background);
    }    
    
    public void setAlpha(float value) {       
        getObject().setAlpha(value);        
    }
    
    public void setScaleType(String value) {       
        value = value.toUpperCase();
        if (value.contains("MATRIX")) { 
            getObject().setScaleType(ImageView.ScaleType.MATRIX);
        } else if (value.contains("CENTER_CROP")) {
            getObject().setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else if (value.contains("CENTER_INSIDE")) {
            getObject().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        } else if (value.contains("FIT_CENTER")) {
            getObject().setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else if (value.contains("FIT_END")) {
            getObject().setScaleType(ImageView.ScaleType.FIT_END);
        } else if (value.contains("FIT_START")) {
            getObject().setScaleType(ImageView.ScaleType.FIT_START);
        } else if (value.contains("FIT_XY")) {
            getObject().setScaleType(ImageView.ScaleType.FIT_XY);
        }        
    }
    
    public void setElevation(float value) {       
        getObject().setElevation(value); 
        
    }
    
    public Bitmap getBitmap() {               
        return getObject().getDrawingCache();
    }
    
    public void setBackgroundResource(int value) {           
        getObject().setBackgroundResource(value);
    }
    
    public void setForegroundGravity(int value) {           
        getObject().setForegroundGravity(value);
    }
    
    public void setDuration(int value) {       
        getObject().getAnimation().setDuration(value);
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
        getObject().setImageAlpha(0);
    }
    
    public void setImageAlpha(int value) {                                    
        getObject().setImageAlpha(value);
    }
    
    public void setSaveEnabled(boolean value) {                                    
        getObject().setSaveEnabled(value);
    }
    
    public void setSelected(boolean value) {                                    
        getObject().setSelected(value);
    }
    
    public void setRotationX(int value) {                                    
        getObject().setRotationX(value);
    }
    
    public void setRotationY(int value) {                                    
        getObject().setRotationY(value);
    }
    
    public void setOutlineSpotShadowColor(int value) {                                    
        getObject().setOutlineSpotShadowColor(value);
    }
    
    public void setOutlineAmbientShadowColor(String value) {                                    
        getObject().setOutlineAmbientShadowColor(Color.parseColor(value));
    }
    
    public void setBaseline(int value) {                                    
        getObject().setBaseline(value);
    }
    
    public void setBaselineAlignBottom(boolean value) {                                    
        getObject().setBaselineAlignBottom(value);
    }
    
    public void setColorFilter(String value) {                                    
        getObject().setColorFilter(Color.parseColor(value));
    }
    
    public void setCropToPadding(boolean value) {                                    
        getObject().setCropToPadding(value);
    }
    
    public void setImageBitmap(Bitmap value) {                                    
        getObject().setImageBitmap(value);
    }
    
    public void setImageURI(String value) {                                    
        getObject().setImageURI(Uri.parse(value));
    }
    
    public void setPadding(int left, int top, int right, int bottom) {
        getObject().setPadding(left, top, right, bottom);
    }
}
