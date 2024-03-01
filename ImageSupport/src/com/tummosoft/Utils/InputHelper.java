package com.tummosoft.Utils;

import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import anywheresoftware.b4a.BA;

@BA.ShortName("InputHelper")
public class InputHelper {
    private static boolean isWhiteSpaces(String s) {
        return s != null && s.matches("\\s+");
    }

    public static boolean isEmpty(String text) {
        return text == null || TextUtils.isEmpty(text) || isWhiteSpaces(text);
    }

    public static boolean isEmpty(Object text) {
        return text == null || TextUtils.isEmpty(text.toString()) || isWhiteSpaces(text.toString());
    }

    public static boolean isEmpty(EditText text) {
        return text == null || isEmpty(text.getText().toString());
    }

    public static String toString(EditText editText) {
        return editText.getText().toString();
    }

    public static String toString(TextView editText) {
        return editText.getText().toString();
    }

   
    @NonNull public static String getTwoLetters(@NonNull String value) {
        return value.length() > 1 ? (String.valueOf(value.charAt(0)) + String.valueOf(value.charAt(1))) : String.valueOf(value.charAt(0));
    }
}