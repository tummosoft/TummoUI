package com.tummosoft.styles;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import anywheresoftware.b4a.BA;

@BA.ShortName("TUMMOStyleManager")
public final class StyleManager {

    ColorStateList colorBg;
    ColorStateList colorBorder;
    int borderWidth;
    boolean isRadiusAdjustBounds;
    int mRadius;
    int mRadiusTopLeft;
    int mRadiusTopRight;
    int mRadiusBottomLeft;
    int mRadiusBottomRight;
    int mColor;
    int mSelectedSolidColor;
    int mLeftTopRadius;
    int mRightTopRadius;
    int mRightBottomRadius;
    int mStrokeWidth;
    int mNormalTextColor;
    int mSelectedTextColor;
    boolean mAdjustBounds;
    int mBackgroundColor;
  
    static public int GetStyleColor(String value) {
        int baseColor = Color.parseColor("#390350");
        
        switch (value) {
            case "primary":
                baseColor = Color.parseColor("#390350");
                break;
            case "secondary":
                baseColor = Color.parseColor("#630DF7");
                break;
            case "tertiary":                
                baseColor = Color.parseColor("#570087");
                break;
            case "quaternary":
                baseColor = Color.parseColor("#EF3947");
                break;
            case "quinary":
                baseColor = Color.parseColor("#390350");
                break;
            case "senary":
                baseColor = Color.parseColor("#D2EEF7");
                break;
            case "septenary":
                baseColor = Color.parseColor("#DACDE6");
                break;
            case "octonary":
                baseColor = Color.parseColor("#390350");
                break;
            case "nonary":
                baseColor = Color.parseColor("#390350");
                break;
            case "denary":
                baseColor = Color.parseColor("#390350");                
                break;            
        }
                
        return baseColor;   
    }
    
    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
    }
       
    public int getSelectedTextColor() {
        return mSelectedTextColor;
    }

    public void setSelectedTextColor(int color) {
        mSelectedTextColor = color;
    }

    public int getNormalTextColor() {
        return mNormalTextColor;
    }

    public void setNormalTextColor(int color) {
        mNormalTextColor = color;
    }

    public int getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(int color) {
        mStrokeWidth = color;
    }

    public int getRightBottomRadius() {
        return mRightBottomRadius;
    }

    public void setRightBottomRadius(int color) {
        mRightBottomRadius = color;
    }

    public int getRightTopRadius() {
        return mRightTopRadius;
    }

    public void setRightTopRadius(int color) {
        mRightTopRadius = color;
    }

    public int getLeftTopRadius() {
        return mLeftTopRadius;
    }

    public void setLeftTopRadius(int color) {
        mLeftTopRadius = color;
    }

    public int getSelectedSolidColor() {
        return mSelectedSolidColor;
    }

    public void setSelectedSolidColor(int color) {
        mSelectedSolidColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public ColorStateList getColorBg() {
        return colorBg;
    }

    public void setColorBg(ColorStateList colorBg) {
        this.colorBg = colorBg;
    }

    public ColorStateList getColorBorder() {
        return colorBorder;
    }

    public void setColorBorder(ColorStateList colorBorder) {
        this.colorBorder = colorBorder;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean IsRadiusAdjustBounds() {
        return isRadiusAdjustBounds;
    }

    public void setRadiusAdjustBounds(boolean isRadiusAdjustBounds) {
        this.isRadiusAdjustBounds = isRadiusAdjustBounds;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getRadiusTopLeft() {
        return mRadiusTopLeft;
    }

    public void setRadiusTopLeft(int mRadiusTopLeft) {
        this.mRadiusTopLeft = mRadiusTopLeft;
    }

    public int getRadiusTopRight() {
        return mRadiusTopRight;
    }

    public void setRadiusTopRight(int mRadiusTopRight) {
        this.mRadiusTopRight = mRadiusTopRight;
    }

    public int getRadiusBottomLeft() {
        return mRadiusBottomLeft;
    }

    public void setRadiusBottomLeft(int mRadiusBottomLeft) {
        this.mRadiusBottomLeft = mRadiusBottomLeft;
    }

    public int getRadiusBottomRight() {
        return mRadiusBottomRight;
    }

    public void setRadiusBottomRight(int mRadiusBottomRight) {
        this.mRadiusBottomRight = mRadiusBottomRight;
    }
}
