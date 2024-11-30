package com.tummosoft.android.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import anywheresoftware.b4a.BA;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.calligraphy3.TypefaceUtils;
import io.github.inflationx.viewpump.ViewPump;
        
@BA.ShortName("AppHelper")
public class AppHelper {
    private static Application sContext;
    private static boolean sIsTabletChecked;
    private static int sScreenType;
    private static String sDefaultFontAssetPath;

    public static void init(Application context) {
        sContext = context;
    }
    
    public static void initFontStyle(String defaultFontAssetPath, int attr) {
        if (!TextUtils.isEmpty(defaultFontAssetPath)) {
            sDefaultFontAssetPath = defaultFontAssetPath;
            ViewPump.init(ViewPump.builder()
                    .addInterceptor(new CalligraphyInterceptor(
                            new CalligraphyConfig.Builder()
                                    .setDefaultFontPath(defaultFontAssetPath)
                                    .setFontAttrId(attr)
                                    .build()))
                    .build());
        }
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            BA.LogError("Null items");            
        }
    }


    public static void debug(String tag) {
        BA.Log(tag);
    }

 
    public static void debug(boolean isDebug) {
        BA.Log("DEBUG:" + isDebug);
    }


    public static void setViewsFont(View... views) {
        setViewsFont(views);
    }


    @Nullable
    public static Typeface getDefaultTypeface() {
        if (!TextUtils.isEmpty(sDefaultFontAssetPath)) {
            return TypefaceUtils.load(getContext().getAssets(), sDefaultFontAssetPath);
        }
        return null;
    }

 
    public static String getDefaultFontAssetPath() {
        return sDefaultFontAssetPath;
    }


    @Nullable
    public static Typeface getDefaultTypeface(String fontPath) {
        if (TextUtils.isEmpty(fontPath)) {
            fontPath = sDefaultFontAssetPath;
        }
        if (!TextUtils.isEmpty(fontPath)) {
            return TypefaceUtils.load(getContext().getAssets(), fontPath);
        }
        return null;
    }

 
    private static int checkScreenSize(Context context) {
        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        if (screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE) {            
            if (screenSize >= Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                return UIConsts.ScreenType.BIG_TABLET;
            } else {
                return UIConsts.ScreenType.SMALL_TABLET;
            }
        } else {
            return UIConsts.ScreenType.PHONE;
        }
    }

    public static int getScreenType() {
        if (sIsTabletChecked) {
            return sScreenType;
        }
        sScreenType = checkScreenSize(getContext());
        sIsTabletChecked = true;
        return sScreenType;
    }

    public static boolean isTablet() {
        return getScreenType() == UIConsts.ScreenType.SMALL_TABLET || getScreenType() == UIConsts.ScreenType.BIG_TABLET;
    }
    
    public static void setTheme(int resourceID, Activity activity) {
        activity.setTheme(resourceID);
    }
    
    @ColorInt
    public static int getMainThemeColor(Context context) {
        return getMainThemeColor(context);
    }
}
