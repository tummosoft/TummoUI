package com.tummosoft.android.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteCollectionItems;
import android.widget.RemoteViews.RemoteResponse;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;

@BA.ShortName("xRemoteView")
public class xRemoteView extends AbsObjectWrapper<RemoteViews> {

    RemoteViews remoteViews = null;

    public void initialize(BA ba, String event, int layoutRes) {
        remoteViews = new RemoteViews(BA.packageName, layoutRes);
        setObject(remoteViews);
    }
    
    public void setOnCheckedChangeResponse(int viewId,  RemoteResponse response) {
        getObject().setOnCheckedChangeResponse(viewId,  response);        
    }
          
    public void setOnClickPendingIntent(int viewId, PendingIntent pendingIntent) {
        getObject().setOnClickPendingIntent(viewId, pendingIntent);
    }
    
    // class -----------------
    // Pedding intent
    @BA.ShortName("xPendingIntent")
    public static class xPendingIntent extends AbsObjectWrapper<PendingIntent> {
         public void initialize(BA ba, String events) {            
            
         }  
         
         public xPendingIntent() {
             
         }
    }
    
     
    public void setImageViewResource(int viewId, int srcId) {
        getObject().setImageViewResource(viewId, viewId);
    }
    
    public void addStableView(int viewId, RemoteViews nestedView, int stableId) {
        getObject(). addStableView(viewId, nestedView, stableId);
    }

    public void addView(int viewId, RemoteViews nestedView) {
        getObject().addView(viewId, nestedView);
    }
    
    public void apply(Context context, ViewGroup parent) {
        getObject().apply(context, parent);
    }
    
    public int getLayoutId() {
        return getObject().getLayoutId();
    }
    
    public void getNotify() {
        getObject().notify();
    }
    
    public void reapply(Context context, View v) {
        getObject().reapply(context, v);
    }
    
    public void removeAllViews(int viewID) {
        getObject().removeAllViews(viewID);
    }
    
    public void removeAllViews(int viewId, int nextId) {
        getObject().setAccessibilityTraversalAfter(viewId, nextId);
    }
    
    public void setAccessibilityTraversalBefore(int viewId, int nextId) {
        getObject().setAccessibilityTraversalBefore(viewId, nextId);
    }
    
    public void setViewVisibility(int viewId, int visibility) {
        getObject().setViewVisibility(viewId, visibility);
    }
    
    public void setViewPadding(int viewId, int left, int top, int right, int bottom) {
        getObject().setViewPadding(viewId, left, top, right, bottom);
    }
    
    public void setViewOutlinePreferredRadiusDimen(int viewId, int resID) {
        getObject().setViewOutlinePreferredRadiusDimen(viewId, resID);
    }
    
    public void setViewOutlinePreferredRadiusAttr(int viewId, int attrId) {
        getObject().setViewOutlinePreferredRadiusAttr(viewId, attrId);
    }
    
    public void setViewOutlinePreferredRadius(int viewId, float width, int units) {
        getObject().setViewLayoutWidth(viewId, width, units);
    }
    
    public void setViewLayoutMarginDimen(int viewId, int type, int dimen) {
        getObject().setViewLayoutMarginDimen(viewId, type, dimen);
    }
    
    public void setViewLayoutHeight(int viewId, float height, int units) {
        getObject().setViewLayoutHeight(viewId, height, units);
    }
    
    public void setViewLayoutWidth(int viewId, float width, int units) {
        getObject().setViewLayoutWidth(viewId, width, units);
    }
    
    public void setUri(int viewId, String methodName, String uri) {
        getObject().setUri(viewId, methodName, Uri.parse(uri));
    }
    
    public void setTextViewTextSize(int viewId, int units, float size) {
        getObject().setTextViewTextSize(viewId, units, size);
    }
    
    public void setTextViewText(int viewId, CharSequence text) {
        getObject().setTextViewText(viewId, text);
    }
    
    public void setTextViewCompoundDrawablesRelative(int viewId,int start,int top,int end,int bottom) {
        getObject().setTextViewCompoundDrawables(viewId, start, top, end, bottom);
    }
    
    public void setTextViewCompoundDrawables(int viewId, int left, int top, int right, int bottom) {
        getObject().setTextViewCompoundDrawables(viewId, left, top, right, bottom);
    }
    
    public void setTextColor(int viewId, String color) {
        getObject().setTextColor(viewId, Color.parseColor(color));
    }
    
    public void setString(int viewId,String methodName,String value) {
        getObject().setString(viewId,methodName,value);
    }
    
    public void setColor(int viewId,String methodName,int colorResource) {
        getObject().setColor(viewId, methodName, colorResource);
    }
    
    public void setCompoundButtonChecked(int viewId, boolean checked) {
        getObject().setCompoundButtonChecked(viewId, checked);
    }
    
    public void setEmptyView(int viewId, int emptyViewId) {
        getObject().setEmptyView(viewId, emptyViewId);
    }
    
    public void setRemoteAdapter(int viewId, Intent intent) {
        getObject().setRemoteAdapter(viewId, intent);
    }
    
    public void setRemoteAdapter2(int viewId, RemoteCollectionItems items) {
        getObject().setRemoteAdapter(viewId, items);
    }
    
    public void setRelativeScrollPosition(int viewId, int offset) {
        getObject().setRelativeScrollPosition(viewId, offset);
    }
    
    public void setDouble(int viewId, String methodName, double value) {
        getObject().setDouble(viewId, methodName, value);
    }
    
    public void setFloat(int viewId,String methodName,float value) {
        getObject().setFloat(viewId, methodName, value);
    }
    
     public void setLong(int viewId,String methodName,long value) {
        getObject().setLong(viewId, methodName, viewId);
    }
     
     public void setLong(int layoutID) {
        getObject().setLightBackgroundLayoutId(layoutID);
    }
     
     public void setRadioGroupChecked(int viewId, int checkedId) {
        getObject().setRadioGroupChecked(viewId, checkedId);
    }
     
     public void setProgressBar(int viewId, int maxValue, int progress, boolean indeterminate) {
        getObject().setProgressBar(viewId, maxValue, progress, indeterminate);
    }
    
}
