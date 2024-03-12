package com.tummosoft.control.button;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.view.MotionEventCompat;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ActivityObject;
import anywheresoftware.b4a.BA.DesignerProperties;
import anywheresoftware.b4a.BA.Property;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import com.tummosoft.Utils.ResourcesHelper;
import mehdi.sakout.fancybuttons.FancyButton;

@DesignerProperties(values = {
    @Property(key = "assetIcon", displayName = "Asset Bitmap", fieldType = "String", defaultValue = "root.png"),
    @Property(key = "mIconPosition", displayName = "Icon Position", fieldType = "String", defaultValue = "TOP", list = "TOP|LEFT|BOTTOM|RIGHT"),
    @Property(key = "mBackgroundColor", displayName = "Background Color", fieldType = "color", defaultValue = "0xFF630DF7"),
    @Property(key = "mFocusBackgroundColor", displayName = "Focus Background", fieldType = "color", defaultValue = "0xFFEF3948"),
    @Property(key = "mDisabledBackgroundColor", displayName = "Disabled Background", fieldType = "color", defaultValue = "0xFF5D5959"),
    @Property(key = "mDisabledTextColor", displayName = "Disabled Text Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @Property(key = "mDisabledBorderColor", displayName = "Disabled Border Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @Property(key = "mDefaultTextColor", displayName = "Default Text Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @Property(key = "mDefaultIconColor", displayName = "Default Icon Color", fieldType = "color", defaultValue = "0xFFFFFFFF"),
    @Property(key = "mBorderColor", displayName = "Border Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @Property(key = "mBorderWidth", displayName = "Border Width", fieldType = "int", defaultValue = "0"),
    @Property(key = "mFontIconSize", displayName = "Font Icon Size", fieldType = "int", defaultValue = "18"),
    @Property(key = "mIconPaddingLeft", displayName = "Icon Padding Left", fieldType = "int", defaultValue = "0"),
    @Property(key = "mIconPaddingRight", displayName = "Icon Padding Right", fieldType = "int", defaultValue = "0"),
    @Property(key = "mIconPaddingTop", displayName = "Icon Padding Top", fieldType = "int", defaultValue = "0"),
    @Property(key = "mIconPaddingBottom", displayName = "Icon Padding Bottom", fieldType = "int", defaultValue = "0"),
    @Property(key = "mEnabled", displayName = "Enabled", fieldType = "boolean", defaultValue = "true"),
    @Property(key = "mFontIcon", displayName = "Symbol Icon", fieldType = "String", defaultValue = "-"),
    @Property(key = "mText", displayName = "Button Text", fieldType = "String", defaultValue = "Tummo Button"),
    @Property(key = "iRadius", displayName = "Border Radius", fieldType = "int", defaultValue = "0"),
    @Property(key = "mClicked", displayName = "Clicked Color", fieldType = "color", defaultValue = "0xFFEF3947"),})
@BA.ShortName("xButtonImageText")
@ActivityObject
@BA.Events(values = {"Click()"})
public class xButtonImageText extends AbsObjectWrapper<FancyButton> implements DesignerCustomView {

    /**
     * Nguy?n v?i phu?c d?c này h?i hu?ng d?n v?n pháp chúng sinh, d?ng th?i vô
     * hi?u hoá tà thu?t c?a gia dình 6 Chia - 2 V?nh d?i v?i Nguy?n Phúc Th?nh,
     * Nguy?n Ng?c Tho, Nguy?n Van Hi?n, Tr?n Th? Ch?, Nguy?n Vi?t Xô * Hoá gi?i
     * tà thu?t c?a dôi v? ch?ng hay di xu?ng du?i sông, ? g?n d?p ông Tà Hoá
     * gi?i tà thu?t nuôi binh trong ngu?i c?a Tr?n Quang V?nh; Hoá gi?i tà
     * thu?t bóng den ng?i thi?n;
     */
    public static final String TAG = xButtonImageText.class.getSimpleName();

    private Context mContext;

    // # Background Attributes
    private Drawable mDefaultBackgroundColor;
    private int mFocusBackgroundColor = 0;
    private int mDisabledBackgroundColor;
    private int mDisabledTextColor;
    private int mDisabledBorderColor;
    private int mDefaultTextColor;
    private int mDefaultIconColor;
    private int mTextPosition = 1;
    private float mDefaultTextSize;
    private int mDefaultTextGravity = 0x11; // Gravity.CENTER

    private Drawable mIconResource = null;
    private int mFontIconSize;
    private String mFontIcon = "EMPTY";
    private String mText = null;

    private String mIconPosition = "TOP";

    private int mIconPaddingLeft = 10;
    private int mIconPaddingRight = 10;
    private int mIconPaddingTop = 0;
    private int mIconPaddingBottom = 0;

    private int mBorderColor = Color.TRANSPARENT;
    private int mBorderWidth = 0;

    private int mRadiusTopLeft = 0;
    private int mRadiusTopRight = 0;
    private int mRadiusBottomLeft = 0;
    private int mRadiusBottomRight = 0;

    private boolean mEnabled = true;

    private boolean mTextAllCaps = false;

    private Typeface mTextTypeFace = null;
    private Typeface mIconTypeFace = null;
    private int textStyle;
    private boolean isInEditMode = false;

    static private Bitmap mBitmapSource;
    private int mRadius;
    private boolean mGhost = false; // Default is a solid button !
    private boolean mUseSystemFont = false; // Default is using robotoregular.ttf
    private boolean mUseRippleEffect = true;
    private int mIconHeight;
    private int mIconWidth;
    private BA mBA;
    private String _event;

    private Drawable _bgclick = null;
    FancyButton xButton = null;

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        setObject(new FancyButton(mContext));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(base.getWidth(), base.getHeight());
        getObject().setLayoutParams(layout);
        getObject().setClickable(true);
        
        mRadius = (int) props.Get("iRadius");
        getObject().setRadius(mRadius);

        if ((String) props.Get("mFontIcon") != null) {
            mFontIcon = (String) props.Get("mFontIcon");
        }

        mText = ((String) props.Get("mText"));

        int mDefaultBackground = ((int) props.Get("mBackgroundColor"));
        getObject().setBackgroundColor(mDefaultBackground);

        mFocusBackgroundColor = ((int) props.Get("mFocusBackgroundColor"));
        getObject().setFocusBackgroundColor(mFocusBackgroundColor);

        mDisabledBackgroundColor = ((int) props.Get("mFocusBackgroundColor"));
        getObject().setDisableBackgroundColor(mDisabledBackgroundColor);

        mEnabled = (boolean) props.Get("mEnabled");
        getObject().setEnabled(mEnabled);

        mDisabledTextColor = ((int) props.Get("mDisabledTextColor"));
        getObject().setDisableTextColor(mDisabledTextColor);

        mDisabledBorderColor = ((int) props.Get("mDisabledBorderColor"));
        getObject().setDisableBorderColor(mDisabledBorderColor);

        mDefaultTextColor = ((int) props.Get("mDefaultTextColor"));
        getObject().setTextColor(mDefaultTextColor);

        mDefaultIconColor = ((int) props.Get("mDefaultIconColor"));
        getObject().setIconColor(mDefaultIconColor);

        mDefaultTextSize = Math.round(lw.getTextSize());
        getObject().setTextSize((int) mDefaultTextSize);

        mDefaultTextGravity = lw.getGravity();

        mBorderColor = (int) props.Get("mBorderColor");
        xButton.setBackgroundColor(mBorderColor);
        mBorderWidth = (int) props.Get("mBorderWidth");
        getObject().setBorderWidth(mBorderWidth);

        mFontIconSize = (int) props.Get("mFontIconSize");

        mIconPaddingLeft = (int) props.Get("mIconPaddingLeft");
        mIconPaddingRight = (int) props.Get("mIconPaddingRight");
        mIconPaddingTop = (int) props.Get("mIconPaddingTop");
        mIconPaddingBottom = (int) props.Get("mIconPaddingBottom");
        xButton.setIconPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);

        mIconHeight = (int) props.Get("mIconHeight");
        mIconWidth = (int) props.Get("mIconWidth");

        int mClicked = (int) props.Get("mClicked");
        _bgclick = CreateBackgroundRadius(mClicked, mRadiusTopLeft);

        String bf = (String) props.Get("assetIcon");
        mBitmapSource = ResourcesHelper.OpenImage(bf);

        if (mIconPaddingLeft == mIconPaddingTop) {
            mRadius = mIconPaddingTop;
        }

        mIconPosition = (String) props.Get("mIconPosition");

        if ((mFontIcon.contains("EMPTY") == false) && (mFontIcon.contains("-") == false)) {
            getObject().setFontIconSize(mFontIconSize);
            getObject().setIconResource(mFontIcon);
        }

        if (mBitmapSource != null) {
            Drawable drawable = new BitmapDrawable(mContext.getResources(), mBitmapSource);
            getObject().setIconResource(drawable);
        }
        if (mText != null) {
            getObject().setText(mText);
        } else {
            getObject().setText("TUMMO BUTTON");
        }

        getObject().setFocusBackgroundColor(mFocusBackgroundColor);

        if (mIconPosition.contains("LEFT")) {
            getObject().setIconPosition(FancyButton.POSITION_LEFT);
        } else if (mIconPosition.contains("TOP")) {
            getObject().setIconPosition(FancyButton.POSITION_TOP);
        } else if (mIconPosition.contains("RIGHT")) {
            getObject().setIconPosition(FancyButton.POSITION_RIGHT);
        } else if (mIconPosition.contains("BOTTOM")) {
            getObject().setIconPosition(FancyButton.POSITION_BOTTOM);
        }

        base.AddView(getObject(), 0, 0, base.getWidth(), base.getHeight());
        base.setColor(Color.TRANSPARENT);

        getObject().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                int action = MotionEventCompat.getActionMasked(ev);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):                        
                        mBA.raiseEventFromUI(v, _event + "_click", null);
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

                return xButton.onTouchEvent(ev);
            }
        });
    }

    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, mContext.getResources().getDisplayMetrics());
    }

    private GradientDrawable CreateBackgroundRadius(int color, int cornerRadius) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(cornerRadius);
        bg.setColor(color);

        return bg;
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
        mContext = ba.context;
        mBA = ba;
        _event = event.toLowerCase();
    }

    public void setCustomTextFont(Typeface font) {
        getObject().setCustomTextFont(font);
    }

    public void setSymbolFont(Typeface font) {
        getObject().setCustomIconFont(font);
    }
}
