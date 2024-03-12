package com.tummosoft.android.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
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
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcelable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.CardView;
import android.text.Editable.Factory;
import android.text.InputFilter;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.util.TypedValue;
import android.view.ActionMode.Callback;
import android.view.ContentInfo;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
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
import android.widget.Scroller;
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
import anywheresoftware.b4a.AbsObjectWrapper;
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
import java.util.Locale;
import java.util.Map;

@BA.ShortName("xTextView")
@BA.Events(values = {"Click()"})
public class xTextView extends AbsObjectWrapper<TextView> {

    private String event;

    public void initialize(BA ba, String event) {
        setObject(new TextView(ba.context));
        this.event = event.toLowerCase();
    }

    public TextView GetView() {
        return getObject();
    }

    public void addExtraDataToAccessibilityNodeInfo(AccessibilityNodeInfo info, String extraDataKey, Bundle arguments) {

        getObject().addExtraDataToAccessibilityNodeInfo(info, extraDataKey, arguments);
    }

    public void addTextChangedListener(TextWatcher watcher) {

        getObject().addTextChangedListener(watcher);
    }

    public void append(CharSequence text) {
        getObject().append(text);
    }

    public void append(CharSequence text, int start, int end) {
        getObject().append(text, start, end);
    }

    public void autofill(AutofillValue value) {

        getObject().autofill(value);
    }

    public boolean bringPointIntoView(int offset) {
        return getObject().bringPointIntoView(offset);
    }

    public void debug(int depth) {

        getObject().debug(depth);
    }

    public void drawableHotspotChanged(float x, float y) {

        getObject().drawableHotspotChanged(x, y);
    }

    public boolean extractText(ExtractedTextRequest request, ExtractedText outText) {
        return getObject().extractText(request, outText);
    }

    public void findViewsWithText(ArrayList<View> outViews, CharSequence searched, int flags) {

        getObject().findViewsWithText(outViews, searched, flags);
    }

    public void getFocusedRect(Rect r) {

        getObject().getFocusedRect(r);
    }

    public Bundle getInputExtras(boolean create) {
        return getObject().getInputExtras(create);
    }

    public int getLineBounds(int line, Rect bounds) {
        return getObject().getLineBounds(line, bounds);
    }

    public int getOffsetForPosition(float x, float y) {
        return getObject().getOffsetForPosition(x, y);
    }

    public void invalidateDrawable(Drawable drawable) {

        getObject().invalidateDrawable(drawable);
    }

    public void onCommitCompletion(CompletionInfo text) {

        getObject().onCommitCompletion(text);
    }

    public void onCommitCorrection(CorrectionInfo info) {

        getObject().onCommitCorrection(info);
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return getObject().onCreateInputConnection(outAttrs);
    }

    public boolean onDragEvent(DragEvent event) {
        return getObject().onDragEvent(event);
    }

    public void onEditorAction(int actionCode) {
        getObject().onEditorAction(actionCode);
    }

    public boolean onGenericMotionEvent(MotionEvent event) {
        return getObject().onGenericMotionEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return getObject().onKeyDown(keyCode, event);
    }

    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return getObject().onKeyMultiple(keyCode, repeatCount, event);
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return getObject().onKeyPreIme(keyCode, event);
    }

    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        return getObject().onKeyShortcut(keyCode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return getObject().onKeyUp(keyCode, event);
    }

    public PointerIcon onResolvePointerIcon(MotionEvent event, int pointerIndex) {
        return getObject().onResolvePointerIcon(event, pointerIndex);
    }

    public void onRestoreInstanceState(Parcelable state) {

        getObject().onRestoreInstanceState(state);
    }

    public void onRtlPropertiesChanged(int layoutDirection) {

        getObject().onRtlPropertiesChanged(layoutDirection);
    }

    public void onScreenStateChanged(int screenState) {

        getObject().onScreenStateChanged(screenState);
    }

    public boolean onTextContextMenuItem(int id) {
        return getObject().onTextContextMenuItem(id);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return getObject().onTouchEvent(event);
    }

    public boolean onTrackballEvent(MotionEvent event) {
        return getObject().onTrackballEvent(event);
    }

    public void onVisibilityAggregated(boolean isVisible) {

        getObject().onVisibilityAggregated(isVisible);
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {

        getObject().onWindowFocusChanged(hasWindowFocus);
    }

    public void removeTextChangedListener(TextWatcher watcher) {

        getObject().removeTextChangedListener(watcher);
    }

    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {

        getObject().sendAccessibilityEventUnchecked(event);
    }

    public void setAllCaps(boolean allCaps) {

        getObject().setAllCaps(allCaps);
    }

    public void setAutoLinkMask(int mask) {

        getObject().setAutoLinkMask(mask);
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) {

        getObject().setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] presetSizes, int unit) {

        getObject().setAutoSizeTextTypeUniformWithPresetSizes(presetSizes, unit);
    }

    public void setAutoSizeTextTypeWithDefaults(int autoSizeTextType) {

        getObject().setAutoSizeTextTypeWithDefaults(autoSizeTextType);
    }

    public void setBreakStrategy(int breakStrategy) {

        getObject().setBreakStrategy(breakStrategy);
    }

    public void setCompoundDrawablePadding(int pad) {

        getObject().setCompoundDrawablePadding(pad);
    }

    public void setCompoundDrawableTintBlendMode(BlendMode blendMode) {

        getObject().setCompoundDrawableTintBlendMode(blendMode);
    }

    public void setCompoundDrawableTintList(ColorStateList tint) {

        getObject().setCompoundDrawableTintList(tint);
    }

    public void setCompoundDrawableTintMode(PorterDuff.Mode tintMode) {

        getObject().setCompoundDrawableTintMode(tintMode);
    }

    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {

        getObject().setCompoundDrawables(left, top, right, bottom);
    }

    public void setCompoundDrawablesRelative(Drawable start, Drawable top, Drawable end, Drawable bottom) {

        getObject().setCompoundDrawablesRelative(start, top, end, bottom);
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable start, Drawable top, Drawable end, Drawable bottom) {

        getObject().setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {

        getObject().setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {

        getObject().setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {

        getObject().setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    public void setCursorVisible(boolean visible) {

        getObject().setCursorVisible(visible);
    }

    public void setCustomInsertionActionModeCallback(Callback actionModeCallback) {

        getObject().setCustomInsertionActionModeCallback(actionModeCallback);
    }

    public void setCustomSelectionActionModeCallback(Callback actionModeCallback) {

        getObject().setCustomSelectionActionModeCallback(actionModeCallback);
    }

    public void setEditableFactory(Factory factory) {

        getObject().setEditableFactory(factory);
    }

    public void setElegantTextHeight(boolean elegant) {

        getObject().setElegantTextHeight(elegant);
    }

    public void setEllipsize(TextUtils.TruncateAt where) {

        getObject().setEllipsize(where);
    }

    public void setEms(int ems) {

        getObject().setEms(ems);
    }

    public void setEnabled(boolean enabled) {

        getObject().setEnabled(enabled);
    }

    public void setError(CharSequence error) {

        getObject().setError(error);
    }

    public void setError(CharSequence error, Drawable icon) {

        getObject().setError(error, icon);
    }

    public void setExtractedText(ExtractedText text) {

        getObject().setExtractedText(text);
    }

    public void setFallbackLineSpacing(boolean enabled) {

        getObject().setFallbackLineSpacing(enabled);
    }

    public void setFilters(InputFilter[] filters) {

        getObject().setFilters(filters);
    }

    public void setFirstBaselineToTopHeight(int firstBaselineToTopHeight) {

        getObject().setFirstBaselineToTopHeight(firstBaselineToTopHeight);
    }

    public void setFontFeatureSettings(String fontFeatureSettings) {

        getObject().setFontFeatureSettings(fontFeatureSettings);
    }

    public boolean setFontVariationSettings(String fontVariationSettings) {
        return getObject().setFontVariationSettings(fontVariationSettings);
    }

    public void setFreezesText(boolean freezesText) {

        getObject().setFreezesText(freezesText);
    }

    public void setGravity(int gravity) {

        getObject().setGravity(gravity);
    }

    public void setHeight(int pixels) {

        getObject().setHeight(pixels);
    }

    public void setHighlightColor(int color) {

        getObject().setHighlightColor(color);
    }

    public void setHint(CharSequence hint) {

        getObject().setHint(hint);
    }

    public void setHint(int resid) {

        getObject().setHint(resid);
    }

    public void setHintTextColor(ColorStateList colors) {

        getObject().setHintTextColor(colors);
    }

    public void setHintTextColor(int color) {

        getObject().setHintTextColor(color);
    }

    public void setHorizontallyScrolling(boolean whether) {

        getObject().setHorizontallyScrolling(whether);
    }

    public void setHyphenationFrequency(int hyphenationFrequency) {

        getObject().setHyphenationFrequency(hyphenationFrequency);
    }

    public void setImeActionLabel(CharSequence label, int actionId) {

        getObject().setImeActionLabel(label, actionId);
    }

    public void setImeOptions(int imeOptions) {
        getObject().setImeOptions(imeOptions);
    }

    public void setIncludeFontPadding(boolean includepad) {

        getObject().setIncludeFontPadding(includepad);
    }

    public void setInputType(int type) {

        getObject().setInputType(type);
    }

    public void setJustificationMode(int justificationMode) {

        getObject().setJustificationMode(justificationMode);
    }

    public void setLastBaselineToBottomHeight(int lastBaselineToBottomHeight) {

        getObject().setLastBaselineToBottomHeight(lastBaselineToBottomHeight);
    }

    public void setLetterSpacing(float letterSpacing) {

        getObject().setLetterSpacing(letterSpacing);
    }

    public void setLineBreakStyle(int lineBreakStyle) {

        getObject().setLineBreakStyle(lineBreakStyle);
    }

    public void setLineBreakWordStyle(int lineBreakWordStyle) {

        getObject().setLineBreakWordStyle(lineBreakWordStyle);
    }

    public void setLineHeight(int lineHeight) {

        getObject().setLineHeight(lineHeight);
    }

    public void setLineSpacing(float add, float mult) {

        getObject().setLineSpacing(add, mult);
    }

    public void setLines(int lines) {

        getObject().setLines(lines);
    }

    public void setLinkTextColor(ColorStateList colors) {

        getObject().setLinkTextColor(colors);
    }

    public void setLinkTextColor(int color) {

        getObject().setLinkTextColor(color);
    }

    public void setLinksClickable(boolean whether) {

        getObject().setLinksClickable(whether);
    }

    public void setMarqueeRepeatLimit(int marqueeLimit) {

        getObject().setMarqueeRepeatLimit(marqueeLimit);
    }

    public void setMaxEms(int maxEms) {

        getObject().setMaxEms(maxEms);
    }

    public void setMaxHeight(int maxPixels) {

        getObject().setMaxHeight(maxPixels);
    }

    public void setMaxLines(int maxLines) {

        getObject().setMaxLines(maxLines);
    }

    public void setMaxWidth(int maxPixels) {

        getObject().setMaxWidth(maxPixels);
    }

    public void setMinEms(int minEms) {

        getObject().setMinEms(minEms);
    }

    public void setMinHeight(int minPixels) {

        getObject().setMinHeight(minPixels);
    }

    public void setMinLines(int minLines) {

        getObject().setMinLines(minLines);
    }

    public void setMinWidth(int minPixels) {

        getObject().setMinWidth(minPixels);
    }

    public void setMovementMethod(MovementMethod movement) {

        getObject().setMovementMethod(movement);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener l) {

        getObject().setOnEditorActionListener(l);
    }

    public void setPadding(int left, int top, int right, int bottom) {

        getObject().setPadding(left, top, right, bottom);
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {

        getObject().setPaddingRelative(start, top, end, bottom);
    }

    public void setPaintFlags(int flags) {

        getObject().setPaintFlags(flags);
    }

    public void setPrivateImeOptions(String type) {

        getObject().setPrivateImeOptions(type);
    }

    public void setRawInputType(int type) {

        getObject().setRawInputType(type);
    }

    public void setScroller(Scroller s) {

        getObject().setScroller(s);
    }

    public void setSelectAllOnFocus(boolean selectAllOnFocus) {

        getObject().setSelectAllOnFocus(selectAllOnFocus);
    }

    public void setSelected(boolean selected) {

        getObject().setSelected(selected);
    }

    public void setShadowLayer(float radius, float dx, float dy, int color) {

        getObject().setShadowLayer(radius, dx, dy, color);
    }

    public void setShowSoftInputOnFocus(boolean show) {

        getObject().setShowSoftInputOnFocus(show);
    }

    public void setSingleLine(boolean singleLine) {

        getObject().setSingleLine(singleLine);
    }

    public void setSpannableFactory(Spannable.Factory factory) {

        getObject().setSpannableFactory(factory);
    }

    public void setText(int resid) {

        getObject().setText(resid);
    }

    public void setText(CharSequence text) {

        getObject().setText(text);
    }

    public void setText(CharSequence text, TextView.BufferType type) {

        getObject().setText(text, type);
    }

    public void setText(int resid, TextView.BufferType type) {

        getObject().setText(resid, type);
    }

    public void setText(char[] text, int start, int len) {

        getObject().setText(text, start, len);
    }

    public void setTextAppearance(Context context, int resId) {

        getObject().setTextAppearance(context, resId);
    }

    public void setTextAppearance(int resId) {

        getObject().setTextAppearance(resId);
    }

    public void setTextClassifier(TextClassifier textClassifier) {

        getObject().setTextClassifier(textClassifier);
    }

    public void setTextColor(int color) {

        getObject().setTextColor(color);
    }

    public void setTextColor(ColorStateList colors) {

        getObject().setTextColor(colors);
    }

    public void setTextCursorDrawable(Drawable textCursorDrawable) {

        getObject().setTextCursorDrawable(textCursorDrawable);
    }

    public void setTextCursorDrawable(int textCursorDrawable) {

        getObject().setTextCursorDrawable(textCursorDrawable);
    }

    public void setTextIsSelectable(boolean selectable) {

        getObject().setTextIsSelectable(selectable);
    }

    public void setTextKeepState(CharSequence text) {

        getObject().setTextKeepState(text);
    }

    public void setTextKeepState(CharSequence text, TextView.BufferType type) {

        getObject().setTextKeepState(text, type);
    }

    public void setTextLocale(Locale locale) {

        getObject().setTextLocale(locale);
    }

    public void setTextLocales(LocaleList locales) {

        getObject().setTextLocales(locales);
    }

    public void setTextMetricsParams(PrecomputedText.Params params) {

        getObject().setTextMetricsParams(params);
    }

    public void setTextScaleX(float size) {

        getObject().setTextScaleX(size);
    }

    public void setTextSelectHandle(int textSelectHandle) {

        getObject().setTextSelectHandle(textSelectHandle);
    }

    public void setTextSelectHandle(Drawable textSelectHandle) {

        getObject().setTextSelectHandle(textSelectHandle);
    }

    public void setTextSelectHandleLeft(int textSelectHandleLeft) {

        getObject().setTextSelectHandleLeft(textSelectHandleLeft);
    }

    public void setTextSelectHandleLeft(Drawable textSelectHandleLeft) {

        getObject().setTextSelectHandleLeft(textSelectHandleLeft);
    }

    public void setTextSelectHandleRight(Drawable textSelectHandleRight) {

        getObject().setTextSelectHandleRight(textSelectHandleRight);
    }

    public void setTextSelectHandleRight(int textSelectHandleRight) {

        getObject().setTextSelectHandleRight(textSelectHandleRight);
    }

    public void setTextSize(int unit, float size) {

        getObject().setTextSize(unit, size);
    }

    public void setTextSize(float size) {

        getObject().setTextSize(size);
    }

    public void setTransformationMethod(TransformationMethod method) {

        getObject().setTransformationMethod(method);
    }

    public void setTypeface(Typeface tf) {

        getObject().setTypeface(tf);
    }

    public void setTypeface(Typeface tf, int style) {

        getObject().setTypeface(tf, style);
    }

    public void setWidth(int pixels) {

        getObject().setWidth(pixels);
    }

    public boolean showContextMenu(float x, float y) {
        return getObject().showContextMenu(x, y);
    }
}
