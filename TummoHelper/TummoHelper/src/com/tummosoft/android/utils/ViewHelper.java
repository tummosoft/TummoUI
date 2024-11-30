package com.tummosoft.android.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@BA.ShortName("ViewHelper")
@Version(1.63f)
public class ViewHelper {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);

    private static Application app;
    private static boolean sIsTabletChecked;
    private static int sScreenType;
    private static String sDefaultFontAssetPath;
    private static Context _context;
    private static BA _ba;
    private static ViewStub viewStub;

    public void initialize(final BA ba) {        
        _ba = ba;
        //viewStub = new ViewStub(getApplication());
    }

    public int GetInfaltedId() {
        return viewStub.getInflatedId();
    }
    
    public static int ConvertToDP(int value, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }

    public static View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
    }
    
    public static void setOutlineAmbientShadowColor(View view, String color) {
        view.setOutlineAmbientShadowColor(Color.parseColor(color));
    }

    public static void requestApplyInsets(Window window) {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            window.getDecorView().requestFitSystemWindows();
        } else if (Build.VERSION.SDK_INT >= 21) {
            window.getDecorView().requestApplyInsets();
        }
    }

    public static int getListViewHeightBasedOnChildren(ListView view) {
        int height = getAbsListViewHeightBasedOnChildren(view);
        ListAdapter adapter;
        int adapterCount;
        if (view != null && (adapter = view.getAdapter()) != null
                && (adapterCount = adapter.getCount()) > 0) {
            height += view.getDividerHeight() * (adapterCount - 1);
        }
        return height;
    }
    
    public static int getAbsListViewHeightBasedOnChildren(AbsListView view) {
        ListAdapter adapter;
        if (view == null || (adapter = view.getAdapter()) == null) {
            return 0;
        }

        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, view);
            if (item instanceof ViewGroup) {
                item.setLayoutParams(new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += view.getPaddingTop() + view.getPaddingBottom();
        return height;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundKeepingPadding(View view, Drawable drawable) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        setBackground(view, drawable);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public static void setBackgroundKeepingPadding(View view, int backgroundResId) {
        setBackgroundKeepingPadding(view, ContextCompat.getDrawable(view.getContext(), backgroundResId));
    }
    
    public static void setViewPadding(View view, int[] padding) {
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public static void setBackgroundColorKeepPadding(View view, @ColorInt int color) {
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        view.setBackgroundColor(color);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            for (;;) {
                final int result = ATOMIC_INTEGER.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) {
                    newValue = 1; // Roll over to 1, not 0.
                }
                if (ATOMIC_INTEGER.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }

    public static Rect calculateViewScreenLocation(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Rect(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }

    public static void setVisibility(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public static void setVisibility(View view, int visibility) {
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    public static void setEnabled(View view, boolean enabled) {
        if (view != null) {
            view.setEnabled(enabled);
            if (view instanceof EditText) {
                view.setFocusable(enabled);
                view.setFocusableInTouchMode(enabled);
            }
        }
    }

    public static void setText(TextView view, String text) {
        if (view != null) {
            view.setText(text);
        }
    }

    public static void setText(TextView view, @StringRes int textId) {
        if (view != null) {
            view.setText(textId);
        }
    }

    public static void textColorId(TextView view, @ColorRes int colorId) {
        if (view != null) {
            view.setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        }
    }

    public static void setImageResource(ImageView view, @DrawableRes int imageId) {
        if (view != null) {
            view.setImageResource(imageId);
        }
    }

    public static void setImageDrawable(ImageView view, Drawable drawable) {
        if (view != null) {
            view.setImageDrawable(drawable);
        }
    }

    public static void setImageLevel(ImageView view, int level) {
        if (view != null) {
            view.setImageLevel(level);
        }
    }

    public static void setImageTint(ImageView view, ColorStateList tint) {
        if (view != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setImageTintList(tint);
            }
        }
    }

    public static void setChecked(CompoundButton view, boolean isCheck) {
        if (view != null) {
            view.setChecked(isCheck);
        }
    }

    public static void setOnCheckedChangeListener(CompoundButton view, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        if (view != null) {
            view.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    public static void setCheckedSilent(CompoundButton view, boolean isCheck, CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        if (view != null) {
            view.setOnCheckedChangeListener(null);
            view.setChecked(isCheck);
            view.setOnCheckedChangeListener(checkedChangeListener);
        }
    }

    @ShortName("expendTouchArea")
    public static void expendTouchArea(final View view, final int expendSize) {
        if (view != null) {
            final View parentView = (View) view.getParent();

            parentView.post(new Runnable() {
                @Override
                public void run() {
                    Rect rect = new Rect();
                    view.getHitRect(rect);
                    rect.left -= expendSize;
                    rect.top -= expendSize;
                    rect.right += expendSize;
                    rect.bottom += expendSize;
                    parentView.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        }
    }

    public static void setPadding(View view, int padding) {
        if (view != null) {
            view.setPadding(padding, padding, padding, padding);
        }
    }

    public static void setPaddingLeft(View view, int value) {
        if (value != view.getPaddingLeft()) {
            view.setPadding(value, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingStart(View view, int value) {
        if (value != view.getPaddingStart()) {
            view.setPaddingRelative(value, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
        }
    }

    public static void setPaddingTop(View view, int value) {
        if (value != view.getPaddingTop()) {
            view.setPadding(view.getPaddingLeft(), value, view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    public static void setPaddingRight(View view, int value) {
        if (value != view.getPaddingRight()) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), value, view.getPaddingBottom());
        }
    }

    public static void setPaddingEnd(View view, int value) {
        if (value != view.getPaddingEnd()) {
            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), value, view.getPaddingBottom());
        }
    }

    public static void setPaddingBottom(View view, int value) {
        if (value != view.getPaddingBottom()) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), value);
        }
    }

    public static boolean getIsLastLineSpacingExtraError() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    public static ImageButton findButton(View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        ImageButton view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = (ImageButton) vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }

        return view;
    }

    public static View findViewFromViewStub(View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
        return view;
    }

    @SuppressLint("ResourceType")
    public static View findViewFromViewStub2(View parentView, int viewStubId, int inflatedViewId, int inflateLayoutResId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            if (vs.getLayoutResource() < 1 && inflateLayoutResId > 0) {
                vs.setLayoutResource(inflateLayoutResId);
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }
        return view;
    }

    @SuppressLint("ResourceType")
    public static View findViewFromViewStub3(final BA ba, String event, View parentView, int viewStubId, int inflatedViewId) {
        if (null == parentView) {
            return null;
        }
        View view = parentView.findViewById(inflatedViewId);
        if (null == view) {
            ViewStub vs = parentView.findViewById(viewStubId);
            if (null == vs) {
                return null;
            }
            view = vs.inflate();
            if (null != view) {
                view = view.findViewById(inflatedViewId);
            }
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ba.raiseEventFromUI(view, event.toLowerCase() + "_click", null);
            }
        });

        return view;
    }

    public static void safeSetImageViewSelected(ImageView imageView, boolean selected) {
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return;
        }
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        imageView.setSelected(selected);
        if (drawable.getIntrinsicWidth() != drawableWidth || drawable.getIntrinsicHeight() != drawableHeight) {
            imageView.requestLayout();
        }
    }

    public static ColorFilter setImageViewTintColor(ImageView imageView, @ColorInt int tintColor) {
        LightingColorFilter colorFilter = new LightingColorFilter(Color.argb(255, 0, 0, 0), tintColor);
        imageView.setColorFilter(colorFilter);
        return colorFilter;
    }
   
    public static boolean isListViewAlreadyAtBottom(ListView listView) {
        if (listView.getAdapter() == null || listView.getHeight() == 0) {
            return false;
        }

        if (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1) {
            View lastItemView = listView.getChildAt(listView.getChildCount() - 1);            
            return lastItemView != null && lastItemView.getBottom() == listView.getHeight();
        }
        return false;
    }

    public static void getDescendantRect(ViewGroup parent, View descendant, Rect out) {
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        ViewGroupHelper.offsetDescendantRect(parent, descendant, out);
    }

    @ShortName("ViewGroupHelper")
    private static class ViewGroupHelper {

        private static final ThreadLocal<Matrix> MATRIX_THREAD_LOCAL = new ThreadLocal<>();
        private static final ThreadLocal<RectF> RECT_F_THREAD_LOCAL = new ThreadLocal<>();

        public static void offsetDescendantRect(ViewGroup group, View child, Rect rect) {
            Matrix m = MATRIX_THREAD_LOCAL.get();
            if (m == null) {
                m = new Matrix();
                MATRIX_THREAD_LOCAL.set(m);
            } else {
                m.reset();
            }

            offsetDescendantMatrix(group, child, m);

            RectF rectF = RECT_F_THREAD_LOCAL.get();
            if (rectF == null) {
                rectF = new RectF();
                RECT_F_THREAD_LOCAL.set(rectF);
            }
            rectF.set(rect);
            m.mapRect(rectF);
            rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f),
                    (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
        }

        static void offsetDescendantMatrix(ViewParent target, View view, Matrix m) {
            final ViewParent parent = view.getParent();
            if (parent instanceof View && parent != target) {
                final View vp = (View) parent;
                offsetDescendantMatrix(target, vp, m);
                m.preTranslate(-vp.getScrollX(), -vp.getScrollY());
            }

            m.preTranslate(view.getLeft(), view.getTop());

            if (!view.getMatrix().isIdentity()) {
                m.preConcat(view.getMatrix());
            }
        }
    }

    public static void setViewTextFont(View view, String assetFont) {
        Typeface typeface = Typeface.createFromFile(assetFont);
        if (view == null || typeface == null) {
            return;
        }
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(typeface);
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                setViewTextFont(viewGroup.getChildAt(i), assetFont);
            }
        }
    }

    public static void setViewsFont(String assetFont, View... views) {
        if (views == null || views.length == 0) {
            return;
        }

        for (View view : views) {
            setViewTextFont(view, assetFont);
        }
    }

    public static void clearViewLongClick(View rootView, int... ids) {
        if (rootView == null || ids == null || ids.length == 0) {
            return;
        }
        for (int id : ids) {
            View view = rootView.findViewById(id);
            if (view != null) {
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
            }
        }
    }

    public static void clearAllViewLongClick(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                clearAllViewLongClick(viewGroup.getChildAt(i));
            }
        } else {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }

    public View getView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        View textV = _ba.activity.findViewById(id);

        return textV;
    }

    public TextView getTextView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        TextView textV = (TextView) _ba.activity.findViewById(id);

        return textV;
    }

    public ImageButton getImageButton(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ImageButton textV = _ba.activity.findViewById(id);

        return textV;
    }

    public Button getButton(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        Button textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ImageView getImageView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ImageView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public Switch getSwitch(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        Switch textV = _ba.activity.findViewById(id);

        return textV;
    }

    public EditText getEditText(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        EditText textV = _ba.activity.findViewById(id);

        return textV;
    }

    public AutoCompleteTextView getAutoCompleteTextView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        AutoCompleteTextView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public MultiAutoCompleteTextView getMultiAutoCompleteTextView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        MultiAutoCompleteTextView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public CheckedTextView getCheckedTextView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        CheckedTextView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public com.google.android.material.textfield.TextInputLayout getTextInputLayout(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        com.google.android.material.textfield.TextInputLayout textV = _ba.activity.findViewById(id);

        return textV;
    }

    public com.google.android.material.chip.ChipGroup getChipGroup(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        com.google.android.material.chip.ChipGroup textV = _ba.activity.findViewById(id);

        return textV;
    }

    public com.google.android.material.chip.Chip getChip(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        com.google.android.material.chip.Chip textV = _ba.activity.findViewById(id);

        return textV;
    }

    public CheckBox getCheckBox(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        CheckBox textV = _ba.activity.findViewById(id);

        return textV;
    }

    public RadioGroup getRadioGroup(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        RadioGroup textV = _ba.activity.findViewById(id);

        return textV;
    }

    public RadioButton getRadioButton(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        RadioButton textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ToggleButton getToggleButton(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ToggleButton textV = _ba.activity.findViewById(id);

        return textV;
    }

    public Switch getWitch(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        Switch textV = _ba.activity.findViewById(id);

        return textV;
    }

    public VideoView getVideoView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        VideoView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public CalendarView getCalendarView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        CalendarView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public TextClock getTextClock(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        TextClock textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ProgressBar getProgressBar(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ProgressBar textV = _ba.activity.findViewById(id);

        return textV;
    }

    public SeekBar getSeekBar(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        SeekBar textV = _ba.activity.findViewById(id);

        return textV;
    }

    public RatingBar getRatingBar(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        RatingBar textV = _ba.activity.findViewById(id);

        return textV;
    }

    public SearchView getSearchView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        SearchView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public TextureView getTextureView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        TextureView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public SurfaceView getSurfaceView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        SurfaceView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public Space getSpace(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        Space textV = _ba.activity.findViewById(id);

        return textV;
    }

    public Spinner getSpinner(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        Spinner textV = _ba.activity.findViewById(id);

        return textV;
    }

    public RecyclerView getRecyclerView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        RecyclerView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ScrollView getScrollView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ScrollView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public HorizontalScrollView getHorizontalScrollView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        HorizontalScrollView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public CardView getCardView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        CardView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public NavigationView getNavigationView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        NavigationView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public BottomNavigationView getBottomNavigationView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        BottomNavigationView textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ViewStub getViewStub(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ViewStub textV = _ba.activity.findViewById(id);

        return textV;
    }

    public ListView getListView(String viewID) {
        int id = _context.getResources().getIdentifier(viewID, "id", _context.getPackageName());
        ListView textV = _ba.activity.findViewById(id);
        return textV;
    }
    
    public enum Direction {
        LEFT_TO_RIGHT,
        TOP_TO_BOTTOM,
        RIGHT_TO_LEFT,
        BOTTOM_TO_TOP
    }

    static public void setEvent(BA ba, String BAevent, View view) {
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
