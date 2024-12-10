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
import androidx.annotation.FontRes;
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
import anywheresoftware.b4a.objects.streams.File;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@BA.ShortName("ResourcesHelper")
public class ResourcesHelper {

    private Context _context;
    private BA _ba;

    public void initialize(final BA ba) {
        _context = ba.context;
        _ba = ba;
    }

    @Hide
    public Resources getResources() {
        return _context.getResources();
    }

    private Map<String, Typeface> cachedFontMap = new HashMap<String, Typeface>();

    public int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public Bitmap getBitmapResources(int id) {
        return BitmapFactory.decodeResource(_context.getResources(), id);
    }

    public Typeface findFont(String fontPath, String defaultFontPath) {
        if (fontPath == null) {
            return Typeface.DEFAULT;
        }

        String fontName = new java.io.File(fontPath).getName();
        String defaultFontName = "";
        if (!TextUtils.isEmpty(defaultFontPath)) {
            defaultFontName = new java.io.File(defaultFontPath).getName();
        }

        if (cachedFontMap.containsKey(fontName)) {
            return cachedFontMap.get(fontName);
        } else {
            try {
                AssetManager assets = _context.getResources().getAssets();

                if (Arrays.asList(assets.list("")).contains(fontPath)) {
                    Typeface typeface = Typeface.createFromAsset(_context.getAssets(), fontName);
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (Arrays.asList(assets.list("fonts")).contains(fontName)) {
                    Typeface typeface = Typeface.createFromAsset(_context.getAssets(), String.format("fonts/%s", fontName));
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (Arrays.asList(assets.list("iconfonts")).contains(fontName)) {
                    Typeface typeface = Typeface.createFromAsset(_context.getAssets(), String.format("iconfonts/%s", fontName));
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (!TextUtils.isEmpty(defaultFontPath) && Arrays.asList(assets.list("")).contains(defaultFontPath)) {
                    Typeface typeface = Typeface.createFromAsset(_context.getAssets(), defaultFontPath);
                    cachedFontMap.put(defaultFontName, typeface);
                    return typeface;
                } else {
                    throw new Exception("Font not Found");
                }

            } catch (Exception e) {
                cachedFontMap.put(fontName, Typeface.DEFAULT);
                return Typeface.DEFAULT;
            }
        }
    }

    private int getRotateDegree(String filePath) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(filePath);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                default:
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private boolean isSpace(String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public Typeface findFont(Context context, String fontPath, String defaultFontPath) {

        if (fontPath == null) {
            return Typeface.DEFAULT;
        }

        String fontName = new java.io.File(fontPath).getName();
        String defaultFontName = "";
        if (!TextUtils.isEmpty(defaultFontPath)) {
            defaultFontName = new java.io.File(defaultFontPath).getName();
        }

        if (cachedFontMap.containsKey(fontName)) {
            return cachedFontMap.get(fontName);
        } else {
            try {
                AssetManager assets = context.getResources().getAssets();

                if (Arrays.asList(assets.list("")).contains(fontPath)) {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), fontName);
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (Arrays.asList(assets.list("fonts")).contains(fontName)) {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", fontName));
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (Arrays.asList(assets.list("iconfonts")).contains(fontName)) {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), String.format("iconfonts/%s", fontName));
                    cachedFontMap.put(fontName, typeface);
                    return typeface;
                } else if (!TextUtils.isEmpty(defaultFontPath) && Arrays.asList(assets.list("")).contains(defaultFontPath)) {
                    Typeface typeface = Typeface.createFromAsset(context.getAssets(), defaultFontPath);
                    cachedFontMap.put(defaultFontName, typeface);
                    return typeface;
                } else {
                    throw new Exception("Font not Found");
                }

            } catch (Exception e) {
                cachedFontMap.put(fontName, Typeface.DEFAULT);
                return Typeface.DEFAULT;
            }
        }
    }

    public int getResourceId(String pVariableName, String pResourcename) {
        try {
            int rsid = _context.getResources().getIdentifier(pVariableName, pResourcename, _context.getPackageName());
            return rsid;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, _context.getResources().getDisplayMetrics());
    }

    private FrameLayout.LayoutParams setParams(int width, int height, int gravity) {
        FrameLayout.LayoutParams result = new FrameLayout.LayoutParams(
                width, height, gravity);
        return result;
    }

    public String OpenString(String filename) {
        String result = "";
        try {
            result = File.ReadString(File.getDirAssets(), filename);
        } catch (IOException ex) {

        }
        return result;
    }

    public String getString(@StringRes int resId) {
        return _context.getResources().getString(resId);
    }

    public Typeface getFont(@FontRes int resId) {
        return _context.getResources().getFont(resId);
    }

    public Drawable getDrawable(@DrawableRes int resId) {
        return _context.getResources().getDrawable(resId, null);
    }

    public float getDimens(@DimenRes int resId) {
        return _context.getResources().getDimension(resId);
    }

    public int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(_context, resId);
    }

    public ColorStateList getColors(@ColorRes int resId) {
        return ContextCompat.getColorStateList(_context, resId);
    }

    public int getDimensionPixelOffset(@DimenRes int resId) {
        return _context.getResources().getDimensionPixelOffset(resId);
    }

    public int getDimensionPixelSize(@DimenRes int resId) {
        return _context.getResources().getDimensionPixelSize(resId);
    }

    public String[] getStringArray(@ArrayRes int resId) {
        return _context.getResources().getStringArray(resId);
    }

    @NonNull
    public List<String> getStringList(int resId) {
        return getStringList(_context, resId, 0);
    }

    @NonNull
    public List<String> getStringList(@NonNull Context context, int resId, int emptyId) {
        List<String> data = new ArrayList<>();
        if (resId == emptyId) {
            return data;
        }
        String[] array = context.getResources().getStringArray(resId);
        if (array.length > 0) {
            data.addAll(Arrays.asList(array));
        }
        return data;
    }

    public int[] getIntArray(@ArrayRes int resId) {
        return _context.getResources().getIntArray(resId);
    }

    public ListView getListView(@LayoutRes int resId, ViewGroup root, String event) {
        ListView textv = (ListView) ListView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public TextView getTextView(@LayoutRes int resId, ViewGroup root, String event) {
        TextView textv = (TextView) TextView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ViewStub getViewStub(@LayoutRes int resId, ViewGroup root, String event) {
        ViewStub textv = (ViewStub) ViewStub.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public BottomNavigationView getBottomNavigationView(@LayoutRes int resId, ViewGroup root, String event) {
        BottomNavigationView textv = (BottomNavigationView) BottomNavigationView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public NavigationView getNavigationView(@LayoutRes int resId, ViewGroup root, String event) {
        NavigationView textv = (NavigationView) NavigationView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public CardView getCardView(@LayoutRes int resId, ViewGroup root, String event) {
        CardView textv = (CardView) CardView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public HorizontalScrollView getHorizontalScrollView(@LayoutRes int resId, ViewGroup root, String event) {
        HorizontalScrollView textv = (HorizontalScrollView) HorizontalScrollView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ScrollView getScrollView(@LayoutRes int resId, ViewGroup root, String event) {
        ScrollView textv = (ScrollView) ScrollView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public Spinner getSpinner(@LayoutRes int resId, ViewGroup root, String event) {
        Spinner textv = (Spinner) Spinner.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public RecyclerView getRecyclerView(@LayoutRes int resId, ViewGroup root, String event) {
        RecyclerView textv = (RecyclerView) RecyclerView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public Space getSpace(@LayoutRes int resId, ViewGroup root, String event) {
        Space textv = (Space) Space.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public SurfaceView getSurfaceView(@LayoutRes int resId, ViewGroup root, String event) {
        SurfaceView textv = (SurfaceView) SurfaceView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public TextureView getTextureView(@LayoutRes int resId, ViewGroup root, String event) {
        TextureView textv = (TextureView) TextureView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public SearchView getSearchView(@LayoutRes int resId, ViewGroup root, String event) {
        SearchView textv = (SearchView) SearchView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public SeekBar getSeekBar(@LayoutRes int resId, ViewGroup root, String event) {
        SeekBar textv = (SeekBar) SeekBar.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public RatingBar getRatingBar(@LayoutRes int resId, ViewGroup root, String event) {
        RatingBar textv = (RatingBar) SearchView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ProgressBar getProgressBar(@LayoutRes int resId, ViewGroup root, String event) {
        ProgressBar textv = (ProgressBar) ProgressBar.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public TextClock getTextClock(@LayoutRes int resId, ViewGroup root, String event) {
        TextClock textv = (TextClock) TextClock.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public CalendarView getCalendarView(@LayoutRes int resId, ViewGroup root, String event) {
        CalendarView textv = (CalendarView) CalendarView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public VideoView getVideoView(@LayoutRes int resId, ViewGroup root, String event) {
        VideoView textv = (VideoView) VideoView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public Switch getSwitch(@LayoutRes int resId, ViewGroup root, String event) {
        Switch textv = (Switch) Switch.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ToggleButton getToggleButton(@LayoutRes int resId, ViewGroup root, String event) {
        ToggleButton textv = (ToggleButton) ToggleButton.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public RadioButton getRadioButton(@LayoutRes int resId, ViewGroup root, String event) {
        RadioButton textv = (RadioButton) RadioButton.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public RadioGroup getRadioGroup(@LayoutRes int resId, ViewGroup root, String event) {
        RadioGroup textv = (RadioGroup) RadioGroup.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public CheckBox getCheckBox(@LayoutRes int resId, ViewGroup root, String event) {
        CheckBox textv = (CheckBox) CheckBox.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public Chip getChip(@LayoutRes int resId, ViewGroup root, String event) {
        Chip textv = (Chip) Chip.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ChipGroup getChipGroup(@LayoutRes int resId, ViewGroup root, String event) {
        ChipGroup textv = (ChipGroup) ChipGroup.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public TextInputLayout getTextInputLayout(@LayoutRes int resId, ViewGroup root, String event) {
        TextInputLayout textv = (TextInputLayout) TextInputLayout.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public MultiAutoCompleteTextView getMultiAutoCompleteTextView(@LayoutRes int resId, ViewGroup root, String event) {
        MultiAutoCompleteTextView textv = (MultiAutoCompleteTextView) MultiAutoCompleteTextView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public AutoCompleteTextView getAutoCompleteTextView(@LayoutRes int resId, ViewGroup root, String event) {
        AutoCompleteTextView textv = (AutoCompleteTextView) AutoCompleteTextView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public EditText getEditText(@LayoutRes int resId, ViewGroup root, String event) {
        EditText textv = (EditText) EditText.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ImageView getImageView(@LayoutRes int resId, ViewGroup root, String event) {
        ImageView textv = (ImageView) ImageView.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public Button getButton(@LayoutRes int resId, ViewGroup root, String event) {
        Button textv = (Button) Button.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public ImageButton getImageButton(@LayoutRes int resId, ViewGroup root, String event) {
        ImageButton textv = (ImageButton) ImageButton.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }

    public TabHost getTabHost(@LayoutRes int resId, ViewGroup root, String event) {
        TabHost textv = (TabHost) TabHost.inflate(_context, resId, root);
        setEvent(_ba, event, textv);
        return textv;
    }
    // ********************
    // EVENTS

    public void setEvent(BA ba, String BAevent, View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ba.raiseEventFromUI(view, BAevent + "_click", null);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ba.raiseEventFromUI(v, BAevent + "_onlongclick", v);
                return true;
            }
        });
    }

}
