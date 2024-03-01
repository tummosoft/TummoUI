package com.tummosoft.control.popup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams;
import anywheresoftware.b4a.BA.Hide;
import com.tummosoft.Utils.ViewHelper;

@Hide
public class PopupView extends PopupWindow {
    private int mPopupWidth;
	private int mPopupHeight;
        private Drawable bg;

	public PopupView(View contentView) {
		this(contentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
        public PopupView(Context context, int layoutId) {
		this(context, layoutId, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}
	
	public PopupView(View contentView, int width, int height) {
		super(contentView, width, height, false);
		init(contentView.getContext());
	}
        
	public PopupView(Context context, int layoutId, int width, int height) {
		super();
		initContentView(context, layoutId, width, height);
		init(context);
	}
	
	private void initContentView(Context context, int layoutId, int width, int height) {
		View contentView = View.inflate(context, layoutId, null);
		setContentView(contentView);
		setWidth(width);
	    setHeight(height);
	}
        
        
        @Override
        public void setBackgroundDrawable(Drawable drawable) {
            bg = drawable;            
        }
        
	public void init(Context context) {
		setFocusable(true);
		setOutsideTouchable(true);		
		setBackgroundDrawable(bg);
		measurePopWindowSize();
	}

	public void measurePopWindowSize() {		
		getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		mPopupHeight = getContentView().getMeasuredHeight();
		mPopupWidth = getContentView().getMeasuredWidth();
	}

	public void onClick(View v) {
		if (isShowing()) {
			dismiss();
		} else {
			showAsDropDown(v);
		}
	}
        
	public void onClickUp2(View v) {
		if (isShowing()) {
			dismiss();
		} else {
			showUp2(v);
		}
	}

	public void onClickUp(View v) {
		if (isShowing()) {
			dismiss();
		} else {
			showUp(v);
		}
	}
	
	public void onClick(View v, int xoff, int yoff) {
		if (isShowing()) {
			dismiss();
		} else {
			showAsDropDown(v, xoff, yoff);
		}
	}
	
	public View findViewById(int resId) {
		return getContentView().findViewById(resId);
	}

    protected <T extends View> T findView(int resId) {
        return (T) getContentView().findViewById(resId);
    }

	public Context getContext() {
		return getContentView().getContext();
	}
	
	public static void dismissPopWindow(PopupView popWindow) {
		if (popWindow != null) {
			popWindow.dismiss();
		}
	}

	public void updatePopWindowHeight(ListView listView) {
		mPopupHeight = ViewHelper.getListViewHeightBasedOnChildren(listView);
	}

	public void showUp(View v) {	
		int[] location = new int[2];
		v.getLocationOnScreen(location);		
		showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - mPopupWidth / 2, location[1] - v.getHeight() / 2 - mPopupHeight);
	}

	public void showUp2(View v) {		
		int[] location = new int[2];
		v.getLocationOnScreen(location);		
		showAtLocation(v, Gravity.NO_GRAVITY, (location[0]) - mPopupWidth / 2, location[1] - mPopupHeight);
	}

}
