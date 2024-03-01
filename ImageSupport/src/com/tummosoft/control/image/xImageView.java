package com.tummosoft.control.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.TransitionDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;

@BA.ShortName("xImageView")
@BA.ActivityObject
@BA.Events(values = {"Click()"})
public class xImageView extends AbsObjectWrapper<AppCompatImageView> implements Common.DesignerCustomView {
      private static Context baContext;
    private String _eventName;
    private int _width;
    private BA _ba;
    private float mRadius = 0;
           
    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new AppCompatImageView(baContext));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(base.getWidth(), base.getHeight());
        getObject().setLayoutParams(layout);
        
        base.setColor(Color.TRANSPARENT);
        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
        getObject().setOnClickListener(new AppCompatImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                _ba.raiseEventFromUI(view, _eventName + "_click", null);
            }
        });
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
         baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;        
    }
    
    private void SetImage(Bitmap bitmap, float cornerRadius) {
        if (getObject().getWidth() == 0 || getObject().getHeight() == 0) {            
            getObject().setImageBitmap(bitmap);
        } else {
            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory
                    .create(getObject().getResources(), bitmap);

            float scaleX = getObject().getWidth() / getObject().getWidth();
            float scaleY = getObject().getHeight() / getObject().getHeight();
            drawable.setCornerRadius(Math.min(scaleX, scaleY) * cornerRadius);

            getObject().setImageDrawable(drawable);
        }
    }
    
    private boolean mRoundedDrawable = false;
    public void setRoundedDrawable(boolean value) {
        mRoundedDrawable = value;
    }
    
    public void setRadius(float radius) {
        mRadius = radius;
    }
    
    public void setBitmap(Bitmap bm) {   
        if ( mRoundedDrawable == true) {
            RoundedDrawable(bm);
        } else if (mRadius > 0) {
            SetImage(bm, mRadius);    
        } else if (mCircular == true) {
            DoCircular(bm);   
        } else {
            getObject().setImageBitmap(bm);
        }        
    }
    
    private boolean mCircular = false;
    public void setCircular(boolean value) {
        mCircular = true;
    }
    
    public void setEnableSelected(boolean value) {
        
    }
    
    private void DoCircular(Bitmap bitmap) {
         RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(baContext.getResources(), bitmap);
         circularBitmapDrawable.setCircular(true);         
         getObject().setImageDrawable(circularBitmapDrawable); 
    }
    
    public void setDrawable(Drawable draw) {
        getObject().setBackgroundDrawable(draw);
    }
    
    private void RoundedDrawable(Bitmap bitmap) {        
        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(baContext.getResources(), bitmap);
        drawable.setAntiAlias(true);
        drawable.setCornerRadius(
                Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
        getObject().setImageDrawable(drawable);        
    }
    
}
