package com.tummosoft.control.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import androidx.core.view.MotionEventCompat;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.DesignerProperties;
import anywheresoftware.b4a.BA.Property;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import com.tummosoft.Utils.ResourcesHelper;


@DesignerProperties(values = {
    @Property(key = "assetIcon", displayName = "Asset Icon", fieldType = "String", defaultValue = "null"),    
    @Property(key = "mScaleType", displayName = "Scale Type", fieldType = "String", defaultValue = "MATRIX", list = "MATRIX|CENTER_CROP|CENTER_INSIDE|FIT_CENTER|FIT_END|FIT_START|FIT_XY"),    
    @Property(key = "iRadius", displayName = "Border Radius", fieldType = "int", defaultValue = "0"),
    @Property(key = "mPadding", displayName = "Padding", fieldType = "int", defaultValue = "0"),
    @Property(key = "mDrawable", displayName = "Drawable Color", fieldType = "color", defaultValue = "0xFF630DF7"),
    @Property(key = "mClicked", displayName = "Clicked Color", fieldType = "color", defaultValue = "0xFFEF3947"),
})

@Version(1.60f)
@BA.ShortName("xButton")
@BA.Events(values = {"Click()"})
public class xButton extends AbsObjectWrapper<ImageButton> implements DesignerCustomView {

    private static Context baContext;
    private ImageButton tummoImageButton;
    private String _eventName;
    private PanelWrapper _base;
    private int _height;
    private int _width;
    private BA _ba;
    private Drawable _baseBackground = null;
    private Drawable _bgclick = null;

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new ImageButton(baContext));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(base.getWidth(), base.getHeight());
        getObject().setLayoutParams(layout);        
        _base = base;
        int radius = ConvertToDP((int) props.Get("iRadius"));
        
        int drawcolor = (int) props.Get("mDrawable");
        _baseBackground = setBackgroundRadius(drawcolor, radius);        
        int mClicked = (int) props.Get("mClicked");                
        _bgclick = setBackgroundRadius(mClicked, radius);
        
        String _align = (String) props.Get("mScaleType");        
        ScaleType style = null;
        
        switch (_align) {
            case "MATRIX":
                style = ScaleType.MATRIX;
                break;
            case "CENTER_CROP":
                style = ScaleType.CENTER_CROP;
                break;
             case "CENTER_INSIDE":
                style = ScaleType.CENTER_INSIDE;
                break;
            case "FIT_CENTER":
                style = ScaleType.FIT_CENTER;
                break;
             case "FIT_END":
                style = ScaleType.FIT_END;
                break;
             case "FIT_START":
                style = ScaleType.FIT_START;
                break;
              case "FIT_XY":
                style = ScaleType.FIT_START;
                break;
        }
                
        String bf = (String) props.Get("assetIcon");
        Bitmap mBitmapSource = ResourcesHelper.OpenImage(bf);
        
        int padding = (int) props.Get("mPadding");
        
        getObject().setScaleType(style);
        getObject().setImageBitmap(mBitmapSource);
        getObject().setBackground(_baseBackground);
        getObject().setPadding(padding, padding, padding, padding);
        getObject().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                int action = MotionEventCompat.getActionMasked(ev);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        
                        _ba.raiseEventFromUI(xButton.this, _eventName + "_click", null);
                        return true;
                    case (MotionEvent.ACTION_MOVE):

                        return true;
                    case (MotionEvent.ACTION_UP):
                        getObject().setBackground(_baseBackground);
                        return true;
                    case (MotionEvent.ACTION_CANCEL):

                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):

                        return true;
                }

                return getObject().onTouchEvent(ev);
            }
        });
        
        base.setColor(Color.TRANSPARENT);
        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
    }

     public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }
    
    private GradientDrawable setBackgroundRadius(int color, int cornerRadius) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(cornerRadius);
        bg.setColor(color);
        
        return bg;
    }

    public void setClickedBackground(Drawable color) {
        _bgclick = color;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setWidth(int width) {
        _base.setWidth(width);
    }

    public void Padding(int left, int top, int right, int bottom) {
        getObject().setPadding(left, top, right, bottom);
    }

    public void setHeight(int height) {
        _base.setHeight(height);
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
        baContext = ba.context;
        _eventName = event;
        _ba = ba;
    }

    public ImageButton getButton() {
        return getObject();
    }

    public void setButton(ImageButton btn) {
        setObject(btn);
    }

    public void setTextAlignment(int value) {
        getObject().setTextAlignment(value);
    }

    public void setText(int value) {

    }

    public void setImageBitmap(Bitmap bm) {
        getObject().setImageBitmap(bm);
    }

    public void setImageDrawable(Drawable drawable) {
        getObject().setImageDrawable(drawable);

    }

    public void setResourcesID(int id) {
        getObject().setBackgroundResource(id);
    }

    public void setDrawableColor(Drawable drawable) {
        getObject().setBackground(drawable);
        _baseBackground = drawable;
    }

    public void setBackgroundColor(int id) {
        getObject().setBackgroundColor(id);
    }

    public void setEnable(boolean value) {
        getObject().setEnabled(value);
    }

    public void setElevation(int value) {
        getObject().setElevation(value);
    }

    public void setAnimation(Animation value) {
         getObject().setAnimation(value);
    }

    public void setId(int id) {
        getObject().setId(id);
    }

    public void setTag(String tag) {
        getObject().setTag(tag);
    }

    public void setImageLevel(int level) {
        getObject().setImageLevel(level);
    }

    public void setImageAlign(String align) {
        getObject().setScaleType(ScaleType.valueOf(align));
    }

}
// phuoc duc nay xin duoc chia se den tat ca cac vi chu thien cu ngu o chua nam tong, bo de dao trang;
// phuoc duc nay xin hoa giai tat ca ta thuat danh ma quy vo nguoi;
// hoa giai ta thuat gay dau don cho nnt
// hoa giai ta thuat cua dong ho 3 doi cua thang chay may xe
// hoa giai ta thuat cua tran quang vinh, 6 chia, pham thi vui, ut vuon co
// hoa giai ta thuat cua dong ho ba doi thang tu lam, 7 khanh
// hoa giai tat ca ta thuat gay hai cho chau nguyen phuoc thinh
