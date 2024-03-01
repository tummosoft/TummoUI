package com.tummosoft.control.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.TransitionDrawable;
import android.util.TypedValue;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import com.tummosoft.Utils.ResourcesHelper;
import com.tummosoft.vector.DrawableHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import mehdi.sakout.fancybuttons.FancyButton;
import org.xml.sax.SAXException;

@BA.DesignerProperties(values = {
    @BA.Property(key = "mBitmap", displayName = "Asset Bitmap", fieldType = "String", defaultValue = "none"),
    @BA.Property(key = "mResourceID", displayName = "Drawable ID", fieldType = "String", defaultValue = "none"),
    @BA.Property(key = "mIconPosition", displayName = "Icon Position", fieldType = "String", defaultValue = "LEFT", list = "LEFT|BOTTOM|RIGHT|FULL|CENTER|CENTER_CROP|TOPLEFT"),
    @BA.Property(key = "mBackgroundColor", displayName = "Background Color", fieldType = "color", defaultValue = "0xFF630DF7"),    
    @BA.Property(key = "mDisabledBackgroundColor", displayName = "Disabled Background", fieldType = "color", defaultValue = "0xFF5D5959"),    
    @BA.Property(key = "mDisabledBorderColor", displayName = "Disabled Border Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),    
    @BA.Property(key = "mBorderColor", displayName = "Border Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @BA.Property(key = "mBorderWidth", displayName = "Border Width", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mEnabled", displayName = "Enabled", fieldType = "boolean", defaultValue = "true"),
    @BA.Property(key = "mFontIcon", displayName = "Symbol Icon", fieldType = "String", defaultValue = "-"),
    @BA.Property(key = "mText", displayName = "Button Text", fieldType = "String", defaultValue = "Tummo Button"),
    @BA.Property(key = "mBorderPadding", displayName = "Border Padding", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mRadius", displayName = "Radius", fieldType = "int", defaultValue = "0"),
   @BA.Property(key = "mCircular", displayName = "Circular", fieldType = "boolean", defaultValue = "false"),
    @BA.Property(key = "mRounded", displayName = "Rounded", fieldType = "boolean", defaultValue = "false"),
    @BA.Property(key = "mClicked", displayName = "Clicked Color", fieldType = "color", defaultValue = "0xFFEF3947"),})
@BA.ShortName("xImageView")
@BA.ActivityObject
@BA.Events(values = {"Click()"})
public class xImageView extends AbsObjectWrapper<AppCompatImageView> implements Common.DesignerCustomView {

    private static Context baContext;
    private String _eventName;
    private int _width = 0;
    private int _height = 0;
    private BA _ba;
    private float mRadius = 0;
    private AnimationDrawable animationDrawable;
    private Bitmap cacheBitmap = null;    
    private boolean mRoundedDrawable = false;
    private static final boolean SHADOW_ENABLED = false;
    private static final float SHADOW_RADIUS = 4f;
    private static final float SHADOW_DX = 0f;
    private static final float SHADOW_DY = 2f;
    private static final int SHADOW_COLOR = Color.BLACK;
    private BitmapShader shader;

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new AppCompatImageView(baContext));
        
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(base.getWidth(), base.getHeight());
        getObject().setLayoutParams(layout);
        ResourcesHelper rshelper = new ResourcesHelper();
        rshelper.initialize(_ba);

        String mResourceID = (String) props.Get("mResourceID");
        if ("none".equals(mResourceID)) {
            String assIcon = (String) props.Get("mBitmap");
            
            if (assIcon != null) {
                cacheBitmap = ResourcesHelper.OpenImage(assIcon);                
                if (cacheBitmap != null) {                    
                    getObject().setImageBitmap(cacheBitmap);
                }                
            }
        } else {
            if (!"none".equals(mResourceID)) {
                int rsid = rshelper.getResourceId(mResourceID, "drawable");
                Drawable draw = rshelper.getVectorDrawable(rsid);
                getObject().setBackgroundDrawable(draw);
            }
        }
        
         int mBorderWidth = (int) props.Get("mBorderWidth");
            int mBoderColor = (int) props.Get("mBorderColor");
            int mBackgroundColor = (int) props.Get("mBackgroundColor");
            int xpadding = 0; //(int) props.Get("mBorderPadding");
            int[] mBorderPadding = {xpadding,xpadding,xpadding,xpadding};
        
        String mIconPosition = (String) props.Get("mIconPosition");
        if (mIconPosition.contains("LEFT")) {
            getObject().setScaleType(ScaleType.FIT_START);
        } else if (mIconPosition.contains("RIGHT")) {
            getObject().setScaleType(ScaleType.FIT_END);
        } else if (mIconPosition.contains("CENTER")) {
            getObject().setScaleType(ScaleType.FIT_CENTER);
        } else if (mIconPosition.contains("FULL")) {
            getObject().setScaleType(ScaleType.FIT_XY);
        } else if (mIconPosition.contains("CENTER_CROP")) {
            getObject().setScaleType(ScaleType.CENTER_CROP);
        } else if (mIconPosition.contains("TOPLEFT")) {
            getObject().setScaleType(ScaleType.MATRIX);
        }
        
        mRadius = (int) props.Get("mRadius");
        boolean mRounded = (boolean) props.Get("mRounded");
        mCircular = (boolean) props.Get("mCircular");
        
        if (mRounded == true) {            
            RoundedDrawable(cacheBitmap);            
        } else if (mCircular == true) {  
            setCircular(true);
            DoCircular(cacheBitmap);
        } else if (mRadius > 0) {
            Bitmap bm = BorderImage(cacheBitmap);
            getObject().setImageBitmap(bm);
            
            BA.Log("RADIUS");
        }
        
       // animationDrawable = new AnimationDrawable();
       // getObject().setImageDrawable(animationDrawable);


        base.setColor(Color.TRANSPARENT);
        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
        getObject().setOnClickListener(new AppCompatImageView.OnClickListener() {
            @Override
            public void onClick(View view) {
                _ba.raiseEventFromUI(view, _eventName + "_click", null);
            }
        });
    }

    public Bitmap BorderImage(Bitmap bmCached) {
       mRadius = 20;
        int mBorderWidth = 5;
        int mPadding = 5;
        int newsize = _width + mBorderWidth + mPadding;
        Bitmap bmpWithBorder = Bitmap.createBitmap(newsize, newsize, bmCached.getConfig());
       
        Canvas canvas = new Canvas(bmpWithBorder);
        Paint mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setAntiAlias(true);
        
        int left = (mPadding + mBorderWidth);
        
        int width = _width - left;
        int height = _height - left;
        
        mpaint.setColor(Color.BLUE);
        mpaint.setStrokeWidth(mBorderWidth);
        mpaint.setStyle(Paint.Style.STROKE);
        RectF rectf = new RectF(left, left, width , height);
        canvas.drawRoundRect(rectf, mRadius, mRadius, mpaint);
        canvas.drawBitmap(bmCached, null, rectf, null);
                        
        BA.LogError("HAS CHANGED");
        return bmpWithBorder;
    }
    
    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
        baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;
    }
    
    public void setRoundedDrawable(boolean value) {
        mRoundedDrawable = value;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }

    public void setBitmap(Bitmap bm) {
        if (mRoundedDrawable == true) {
            RoundedDrawable(bm);
        } else if (mRadius > 0) {
            
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
