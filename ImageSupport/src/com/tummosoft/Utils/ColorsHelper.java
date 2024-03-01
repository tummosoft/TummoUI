package com.tummosoft.Utils;

import android.content.Context;
import android.content.res.ColorStateList;
import anywheresoftware.b4a.BA;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import java.util.Random;

@BA.ShortName("ColorsHelper")
public class ColorsHelper {
     private static Context baContext;

    public void initialize(final BA ba) {
        baContext = ba.context;
    }
    
    public static int setColorAlpha(@ColorInt int color, float alpha) {
        return setColorAlpha(color, alpha, true);
    }

    public static int setColorAlpha(@ColorInt int color, float alpha, boolean override) {
        int origin = override ? 0xff : (color >> 24) & 0xff;
        return color & 0x00ffffff | (int) (alpha * origin) << 24;
    }

    public static int computeColor(@ColorInt int fromColor, @ColorInt int toColor, float fraction) {
        fraction = Math.max(Math.min(fraction, 1), 0);

        int minColorA = Color.alpha(fromColor);
        int maxColorA = Color.alpha(toColor);
        int resultA = (int) ((maxColorA - minColorA) * fraction) + minColorA;

        int minColorR = Color.red(fromColor);
        int maxColorR = Color.red(toColor);
        int resultR = (int) ((maxColorR - minColorR) * fraction) + minColorR;

        int minColorG = Color.green(fromColor);
        int maxColorG = Color.green(toColor);
        int resultG = (int) ((maxColorG - minColorG) * fraction) + minColorG;

        int minColorB = Color.blue(fromColor);
        int maxColorB = Color.blue(toColor);
        int resultB = (int) ((maxColorB - minColorB) * fraction) + minColorB;

        return Color.argb(resultA, resultR, resultG, resultB);
    }

    public static ColorStateList textSelector(int normalColor, int pressedColor) {
        return new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{android.R.attr.state_activated},
                        new int[]{android.R.attr.state_selected},
                        new int[]{}
                },
                new int[]{
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        pressedColor,
                        normalColor
                }
        );
    }
   
    public static String colorToString(@ColorInt int color) {
        return String.format("#%08X", color);
    }

    public static int ColorStringToINT(String hexcolor) {
        return Color.parseColor(hexcolor);
    }
    
    public static int darker(int color) {
        return darker(color, 0.8F);
    }
       
    public static int darker(int color, float factor) {
        return Color.argb(Color.alpha(color), Math.max((int) (Color.red(color) * factor), 0),
                Math.max((int) (Color.green(color) * factor), 0),
                Math.max((int) (Color.blue(color) * factor), 0));
    }

    public static int lighter(int color) {
        return lighter(color, 0.8F);
    }

    public static int lighter(int color, float factor) {
        int red = (int) ((Color.red(color) * (1 - factor) / 255 + factor) * 255);
        int green = (int) ((Color.green(color) * (1 - factor) / 255 + factor) * 255);
        int blue = (int) ((Color.blue(color) * (1 - factor) / 255 + factor) * 255);
        return Color.argb(Color.alpha(color), red, green, blue);
    }

    public static boolean isColorDark(@ColorInt int color) {
        return isColorDark(color, 0.5F);
    }


    public static boolean isColorDark(@ColorInt int color, double factor) {
        double darkness =
                1
                        - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color))
                        / 255;
        return darkness >= factor;
    }

    public static int getRandomColor() {
        return new RandomColor(255, 0, 255).getColor();
    }

    private static class RandomColor {
        int alpha;
        int lower;
        int upper;

        RandomColor(int alpha, int lower, int upper) {
            if (upper <= lower) {
                throw new IllegalArgumentException("must be lower < upper");
            }
            setAlpha(alpha);
            setLower(lower);
            setUpper(upper);
        }

        public int getColor() {          
            int red = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            int green = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            int blue = getLower() + new Random().nextInt(getUpper() - getLower() + 1);
            return Color.argb(getAlpha(), red, green, blue);
        }

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            if (alpha > 255) {
                alpha = 255;
            }
            if (alpha < 0) {
                alpha = 0;
            }
            this.alpha = alpha;
        }

        int getLower() {
            return lower;
        }

        void setLower(int lower) {
            if (lower < 0) {
                lower = 0;
            }
            this.lower = lower;
        }

        int getUpper() {
            return upper;
        }

        void setUpper(int upper) {
            if (upper > 255) {
                upper = 255;
            }
            this.upper = upper;
        }
    }
}
