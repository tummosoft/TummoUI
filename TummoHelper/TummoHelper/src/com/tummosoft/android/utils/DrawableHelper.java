package com.tummosoft.android.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.graphics.drawable.VectorDrawable;
import android.media.ExifInterface;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import androidx.annotation.AnimRes;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.StyleableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.streams.File;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import static com.tummosoft.android.utils.BitmapHelper.bitmap2Bytes;
import static com.tummosoft.android.utils.BitmapHelper.drawable2Bitmap;
import static com.tummosoft.android.utils.ResourcesHelper.getResources;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@BA.ShortName("DrawableHelper")
public class DrawableHelper {
    private static Context baContext;
    private String _eventName;        
    private BA _ba;
    private int idx = 0;

    public void initialize(BA ba, String event) {
        _ba = ba;
        baContext = _ba.context;
        _eventName = event.toLowerCase();
    }
    
     public static byte[] drawable2Bytes(Drawable drawable, CompressFormat format) {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
    }

    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(res, bitmap);
    }
    
     public static GradientDrawable createRectangleDrawable(int color, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
     
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createRectangleDrawable(int[] colors, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }
        
    public static GradientDrawable createOvalDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
       
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createOvalDrawable(int[] colors) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }
    
     public static Drawable getDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return baContext.getDrawable(resId);
        }
        return AppCompatResources.getDrawable(baContext, resId);
    }
     
       public static StateListDrawable getStateListDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        StateListDrawable drawable = (StateListDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }
       
        public static LevelListDrawable getLevelListDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        LevelListDrawable drawable = (LevelListDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static LayerDrawable getLayerDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        LayerDrawable drawable = (LayerDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static TransitionDrawable getTransitionDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        TransitionDrawable drawable = (TransitionDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static RippleDrawable getRippleDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        RippleDrawable drawable = (RippleDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static ScaleDrawable getScaleDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        ScaleDrawable drawable = (ScaleDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static ClipDrawable getClipDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        ClipDrawable drawable = (ClipDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }

    public static RotateDrawable getRotateDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        RotateDrawable drawable = (RotateDrawable) AppCompatResources.getDrawable(baContext, resId);

        return drawable;
    }

    public static NinePatchDrawable getNinePatchDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        NinePatchDrawable drawable = (NinePatchDrawable) AppCompatResources.getDrawable(baContext, resId);

        return drawable;
    }

    public static InsetDrawable getInsetDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        InsetDrawable drawable = (InsetDrawable) AppCompatResources.getDrawable(baContext, resId);

        return drawable;
    }

    public static VectorDrawable getVectorDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return (VectorDrawable) baContext.getDrawable(resId);
        }
        return (VectorDrawable) AppCompatResources.getDrawable(baContext, resId);
    }

    public static Drawable getDrawableAttrRes(TypedArray typedArray, @StyleableRes int styleableResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return typedArray.getDrawable(styleableResId);
        } else {
            int resourceId = typedArray.getResourceId(styleableResId, -1);
            if (resourceId != -1) {
                return AppCompatResources.getDrawable(baContext, resourceId);
            }
        }
        return null;
    }

    public static ColorStateList getColorStateListAttrRes(TypedArray typedArray, @StyleableRes int styleableResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return typedArray.getColorStateList(styleableResId);
        } else {
            int resourceId = typedArray.getResourceId(styleableResId, -1);
            if (resourceId != -1) {
                return AppCompatResources.getColorStateList(baContext, resourceId);
            }
        }
        return null;
    }
    
    public static Drawable[] getDrawableArray(@ArrayRes int resId) {
        TypedArray ta = getResources().obtainTypedArray(resId);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(baContext, id);
            }
        }
        ta.recycle();
        return icons;
    }
}
