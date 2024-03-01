package com.tummosoft.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by Kosh on 17/12/15 10:25 PM
 */
public class TypeFaceHelper {

    private static Typeface arabicTypeFace;

    public static void generateTypeface(Context context) {
        arabicTypeFace = Typeface.createFromAsset(context.getAssets(), "fonts/app_font.ttf");
    }

    public static void applyTypeface(TextView textView) {
        textView.setTypeface(arabicTypeFace);
    }

    public static Typeface getTypeface() {
        return arabicTypeFace;
    }
}
