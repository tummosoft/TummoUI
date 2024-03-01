package com.tummosoft.control.line;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DividerUtils {
    public static void addDividersTo(@NonNull View view, @NonNull DividerDrawable... dividerDrawables) {
        setBackground(view, addDividersTo(view.getBackground(), dividerDrawables));
    }

    public static Drawable addDividersTo(@Nullable Drawable sourceDrawable, @NonNull DividerDrawable... dividerDrawables) {
        return new CombinedDrawable(sourceDrawable, dividerDrawables);
    }
    
    public static void clearDividersWith(@NonNull View view) {
        setBackground(view, clearDividersWith(view.getBackground()));
    }

    public static Drawable clearDividersWith(@Nullable Drawable sourceDrawable) {
        if (sourceDrawable instanceof CombinedDrawable) {
            return clearDividersWith(((CombinedDrawable) sourceDrawable).origin);
        } else {
            return sourceDrawable;
        }
    }

    public static float dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dipValue * scale + 0.5f;
    }

    private static void setBackground(View view, Drawable drawable) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    public static class CombinedDrawable extends LayerDrawable {
        private Drawable origin;

        CombinedDrawable(@Nullable Drawable origin, @NonNull DividerDrawable[] divider) {
            super(combine(origin, divider));
            this.origin = origin;
        }

        static Drawable[] combine(Drawable origin, DividerDrawable[] dividers) {
            final int hasOrigin = origin != null ? 1 : 0;
            final Drawable[] layers = new Drawable[dividers.length + hasOrigin];
            if (hasOrigin > 0) {
                layers[0] = origin;
            }
            System.arraycopy(dividers, 0, layers, hasOrigin, dividers.length);
            return layers;
        }
    }
}
