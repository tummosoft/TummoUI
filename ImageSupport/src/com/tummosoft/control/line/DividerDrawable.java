package com.tummosoft.control.line;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DividerDrawable extends Drawable {
    public static final int DEFAULT_STORKE_WIDTH = 1;
    public static final int DEFAULT_COLOR = 0xFFCCCCCC;

    private final Paint paint;
    private DividerLayout layout;
    private int strokeWidth = DEFAULT_STORKE_WIDTH;
    private int wh[] = new int[2];
    private int layouted[];


    public DividerDrawable() {
        this(null);
    }

    public DividerDrawable(@Nullable DividerLayout layout) {
        paint = new Paint();
        paint.setColor(DEFAULT_COLOR);

        if (layout == null) {
            layout = new DividerLayout();
        }
        this.layout = layout;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    @NonNull
    public DividerDrawable setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    @NonNull
    public DividerDrawable setStrokeWidthDp(int strokeWidthDp) {
        this.strokeWidth = (int) DividerUtils.dp2px(strokeWidthDp);
        return this;
    }

    public int getColor() {
        return paint.getColor();
    }

    @NonNull
    public DividerDrawable setColor(int color) {
        paint.setColor(color);
        return this;
    }

    @NonNull
    public DividerLayout getLayout() {
        return layout;
    }

    public void setLayout(@NonNull DividerLayout layout) {
        this.layout = layout;
        notifyLayoutChanged();
    }

    public void notifyLayoutChanged() {
        layouted = null;
        invalidateSelf();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final int w = canvas.getWidth();
        final int h = canvas.getHeight();
        if (layouted == null || wh[0] != w || wh[1] != h) {
            layouted = layout.layout(w, h, strokeWidth);
            wh[0] = w;
            wh[1] = h;
        }
        canvas.drawRect(layouted[0], layouted[1], layouted[2], layouted[3], paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
