package com.tummosoft.Utils;

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
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.AnimRes;
import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.StyleableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.objects.streams.File;
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

    private static Context baContext;

    public void initialize(final BA ba) {
        baContext = ba.context;
    }

    @Hide
    public static Resources getResources() {
        return baContext.getResources();
    }

    private static Map<String, Typeface> cachedFontMap = new HashMap<String, Typeface>();

    public static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static Bitmap bytes2Bitmap(byte[] bytes) {
        return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(format, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }

    public static Bitmap getBitmap(byte[] data, int offset) {
        if (data.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(data, offset, data.length);
    }

    public static Bitmap getBitmapResources(int id) {
        return BitmapFactory.decodeResource(baContext.getResources(), id);
    }

    public static Bitmap scale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle) {
        Matrix matrix = new Matrix();
        matrix.setScale(scaleWidth, scaleHeight);
        Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap scale(Bitmap src, int newWidth, int newHeight) {
        return scale(src, newWidth, newHeight, false);
    }

    public static Bitmap clip(Bitmap src, int x, int y, int width, int height) {
        return clip(src, x, y, width, height, false);
    }

    public static Bitmap clip(Bitmap src, int x, int y, int width, int height, boolean recycle) {
        Bitmap ret = Bitmap.createBitmap(src, x, y, width, height);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap skew2(Bitmap src, float kx, float ky, boolean recycle) {
        return skew(src, kx, ky, 0, 0, recycle);
    }

    public static Bitmap skew3(Bitmap src, float kx, float ky, float px, float py) {
        return skew(src, kx, ky, px, py, false);
    }

    public static Bitmap skew(Bitmap src, float kx, float ky, float px, float py, boolean recycle) {
        Matrix matrix = new Matrix();
        matrix.setSkew(kx, ky, px, py);
        Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap rotate2(Bitmap src, int degrees, float px, float py) {
        return rotate(src, degrees, px, py, false);
    }

    public static Bitmap rotate(Bitmap src, int degrees, float px, float py, boolean recycle) {

        if (degrees == 0) {
            return src;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(degrees, px, py);
        Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static int getRotateDegree(String filePath) {
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

    public static Bitmap toRound2(Bitmap src) {
        return toRound(src, false);
    }

    public static Bitmap toRound(Bitmap src, boolean recycle) {

        int width = src.getWidth();
        int height = src.getHeight();
        int radius = Math.min(width, height) >> 1;
        Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(ret);
        Rect rect = new Rect(0, 0, width, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(width >> 1, height >> 1, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, rect, rect, paint);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap toRoundCorner2(Bitmap src, float radius) {
        return toRoundCorner(src, radius, false);
    }

    public static Bitmap toRoundCorner(Bitmap src, float radius, boolean recycle) {
        if (null == src) {
            return null;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(ret);
        Rect rect = new Rect(0, 0, width, height);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(new RectF(rect), radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, rect, rect, paint);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap fastBlur2(Bitmap src,
            @FloatRange(from = 0, to = 1, fromInclusive = false) float scale,
            @FloatRange(from = 0, to = 25, fromInclusive = false) float radius) {
        return fastBlur(src, scale, radius, false);
    }

    public static Bitmap fastBlur(Bitmap src,
            @FloatRange(from = 0, to = 1, fromInclusive = false) float scale,
            @FloatRange(from = 0, to = 25, fromInclusive = false) float radius,
            boolean recycle) {
        int width = src.getWidth();
        int height = src.getHeight();
        int scaleWidth = (int) (width * scale + 0.5f);
        int scaleHeight = (int) (height * scale + 0.5f);
        if (scaleWidth == 0 || scaleHeight == 0) {
            return null;
        }
        Bitmap scaleBitmap = Bitmap.createScaledBitmap(src, scaleWidth, scaleHeight, true);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.ANTI_ALIAS_FLAG);
        Canvas canvas = new Canvas();
        PorterDuffColorFilter filter = new PorterDuffColorFilter(
                Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP);
        paint.setColorFilter(filter);
        canvas.scale(scale, scale);
        canvas.drawBitmap(scaleBitmap, 0, 0, paint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            scaleBitmap = renderScriptBlur(scaleBitmap, radius);
        } else {
            scaleBitmap = stackBlur(scaleBitmap, (int) radius, recycle);
        }
        if (scale == 1) {
            return scaleBitmap;
        }
        Bitmap ret = Bitmap.createScaledBitmap(scaleBitmap, width, height, true);
        if (scaleBitmap != null && !scaleBitmap.isRecycled()) {
            scaleBitmap.recycle();
        }
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap renderScriptBlur(Bitmap src, @FloatRange(from = 0, to = 25, fromInclusive = false) float radius) {

        RenderScript rs = null;
        try {
            rs = RenderScript.create(baContext);
            rs.setMessageHandler(new RenderScript.RSMessageHandler());
            Allocation input = Allocation.createFromBitmap(rs, src, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            blurScript.setInput(input);
            blurScript.setRadius(radius);
            blurScript.forEach(output);
            output.copyTo(src);
        } finally {
            if (rs != null) {
                rs.destroy();
            }
        }
        return src;
    }

    public static Bitmap stackBlur(Bitmap src, int radius, boolean recycle) {
        Bitmap ret;
        if (recycle) {
            ret = src;
        } else {
            ret = src.copy(src.getConfig(), true);
        }

        if (radius < 1) {
            return null;
        }

        int w = ret.getWidth();
        int h = ret.getHeight();

        int[] pix = new int[w * h];
        ret.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }
        ret.setPixels(pix, 0, w, 0, 0, w, h);
        return ret;
    }

    public static Bitmap addFrame2(Bitmap src, int borderWidth, int color) {
        return addFrame(src, borderWidth, color, false);
    }

    public static Bitmap addFrame(Bitmap src, int borderWidth, int color, boolean recycle) {
        int doubleBorder = borderWidth << 1;
        int newWidth = src.getWidth() + doubleBorder;
        int newHeight = src.getHeight() + doubleBorder;
        Bitmap ret = Bitmap.createBitmap(newWidth, newHeight, src.getConfig());
        Canvas canvas = new Canvas(ret);
        Rect rect = new Rect(0, 0, newWidth, newHeight);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(doubleBorder);
        canvas.drawRect(rect, paint);
        canvas.drawBitmap(src, borderWidth, borderWidth, null);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap addReflection2(Bitmap src, int reflectionHeight) {
        return addReflection(src, reflectionHeight, false);
    }

    public static Bitmap addReflection(Bitmap src, int reflectionHeight, boolean recycle) {
        final int REFLECTION_GAP = 0;
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionBitmap = Bitmap.createBitmap(src, 0, srcHeight - reflectionHeight,
                srcWidth, reflectionHeight, matrix, false);
        Bitmap ret = Bitmap.createBitmap(srcWidth, srcHeight + reflectionHeight, src.getConfig());
        Canvas canvas = new Canvas(ret);
        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        LinearGradient shader = new LinearGradient(0, srcHeight,
                0, ret.getHeight() + REFLECTION_GAP,
                0x70FFFFFF, 0x00FFFFFF, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, srcHeight + REFLECTION_GAP,
                srcWidth, ret.getHeight(), paint);
        if (!reflectionBitmap.isRecycled()) {
            reflectionBitmap.recycle();
        }
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap toAlpha2(Bitmap src) {
        return toAlpha(src, false);
    }

    public static Bitmap toAlpha(Bitmap src, Boolean recycle) {

        Bitmap ret = src.extractAlpha();
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return ret;
    }

    public static Bitmap toGray2(Bitmap src) {
        return toGray(src, false);
    }

    public static Bitmap toGray(Bitmap src, boolean recycle) {
        Bitmap grayBitmap = Bitmap.createBitmap(src.getWidth(),
                src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(grayBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(src, 0, 0, paint);
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return grayBitmap;
    }

    public static Bitmap compressByScale3(Bitmap src, int newWidth, int newHeight) {
        return scale(src, newWidth, newHeight, false);
    }

    public static Bitmap compressByScale2(Bitmap src, int newWidth, int newHeight, boolean recycle) {
        return scale(src, newWidth, newHeight, recycle);
    }

    public static Bitmap compressByScale1(Bitmap src, float scaleWidth, float scaleHeight) {
        return scale(src, scaleWidth, scaleHeight, false);
    }

    public static Bitmap compressByScale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle) {
        return scale(src, scaleWidth, scaleHeight, recycle);
    }

    public static Bitmap compressByQuality3(Bitmap src, @IntRange(from = 0, to = 100) int quality) {
        return compressByQuality(src, quality, false);
    }

    public static Bitmap compressByQuality2(Bitmap src, @IntRange(from = 0, to = 100) int quality, boolean recycle) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap compressByQuality1(Bitmap src, long maxByteSize) {
        return compressByQuality(src, maxByteSize, false);
    }

    public static Bitmap compressByQuality(Bitmap src, long maxByteSize, boolean recycle) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int quality = 100;
        src.compress(CompressFormat.JPEG, quality, baos);
        while (baos.toByteArray().length > maxByteSize && quality > 0) {
            baos.reset();
            src.compress(CompressFormat.JPEG, quality -= 5, baos);
        }
        if (quality < 0) {
            return null;
        }
        byte[] bytes = baos.toByteArray();
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap compressBySampleSize1(Bitmap src, int sampleSize) {
        return compressBySampleSize(src, sampleSize, false);
    }

    public static Bitmap compressBySampleSize(Bitmap src, int sampleSize, boolean recycle) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        if (recycle && !src.isRecycled()) {
            src.recycle();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    private static boolean isSpace(String s) {
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

    public static byte[] drawable2Bytes(Drawable drawable, CompressFormat format) {
        return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
    }

    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(res, bitmap);
    }

    public static Typeface findFont(Context context, String fontPath, String defaultFontPath) {

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

    public static int getResourceId(String pVariableName, String pResourcename) {
        try {
            return baContext.getResources().getIdentifier(pVariableName, pResourcename, baContext.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    static public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }

    private FrameLayout.LayoutParams setParams(int width, int height, int gravity) {
        FrameLayout.LayoutParams result = new FrameLayout.LayoutParams(
                width, height, gravity);
        return result;
    }

    static public Bitmap OpenImage(String filename) {
        Bitmap bmp = null;
        try {
            File.InputStreamWrapper inputStream = File.OpenInput(File.getDirAssets(), filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream.getObject());
            bmp = BitmapFactory.decodeStream(bufferedInputStream);
        } catch (IOException ex) {

        }
        return bmp;
    }
    
    static public String OpenString(String filename) {
        String result = "";
        try {
            result = File.ReadString(File.getDirAssets(), filename);
        } catch (IOException ex) {

        }
        return result;
    }

    public static String getString(@StringRes int resId) {
        return baContext.getResources().getString(resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return baContext.getDrawable(resId);
        }
        return AppCompatResources.getDrawable(baContext, resId);
    }

    public static AnimationDrawable getDrawableAnimation(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        AnimationDrawable drawable = (AnimationDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }
    
    public static StateListDrawable getStateListDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        StateListDrawable drawable = (StateListDrawable) AppCompatResources.getDrawable(baContext, resId);
        return drawable;
    }
    
    public static AnimatedStateListDrawable getAnimatedStateListDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        AnimatedStateListDrawable drawable = (AnimatedStateListDrawable) AppCompatResources.getDrawable(baContext, resId);
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
        NinePatchDrawable  drawable = (NinePatchDrawable) AppCompatResources.getDrawable(baContext, resId);
        
        return drawable;
    }
     
      public static InsetDrawable getInsetDrawable(@DrawableRes int resId) {
        Resources res = baContext.getResources();
        InsetDrawable  drawable = (InsetDrawable) AppCompatResources.getDrawable(baContext, resId);
        
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

    public static float getDimens(@DimenRes int resId) {
        return baContext.getResources().getDimension(resId);
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(baContext, resId);
    }

    public static ColorStateList getColors(@ColorRes int resId) {
        return ContextCompat.getColorStateList(baContext, resId);
    }

    public static int getDimensionPixelOffset(@DimenRes int resId) {
        return baContext.getResources().getDimensionPixelOffset(resId);
    }

    public static int getDimensionPixelSize(@DimenRes int resId) {
        return baContext.getResources().getDimensionPixelSize(resId);
    }

    public static String[] getStringArray(@ArrayRes int resId) {
        return baContext.getResources().getStringArray(resId);
    }

    @NonNull
    public static List<String> getStringList(int resId) {
        return getStringList(baContext, resId, 0);
    }

    @NonNull
    public static List<String> getStringList(@NonNull Context context, int resId, int emptyId) {
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

    public static int[] getIntArray(@ArrayRes int resId) {
        return baContext.getResources().getIntArray(resId);
    }

    public static Animation getAnim(@AnimRes int resId) {
        return AnimationUtils.loadAnimation(baContext, resId);
    }

    @Hide
    public static boolean isRtl(@NonNull Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
                && baContext.getResources().getConfiguration().getLayoutDirection() == View.LAYOUT_DIRECTION_RTL;
    }

    @Hide
    public static int darker(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                Math.max((int) (Color.green(color) * factor), 0),
                Math.max((int) (Color.blue(color) * factor), 0));
    }

    @Hide
    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

}
