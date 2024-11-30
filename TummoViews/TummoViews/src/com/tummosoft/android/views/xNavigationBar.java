package com.tummosoft.android.views;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import com.tummosoft.android.layout.ShadowProperty;
import com.tummosoft.android.layout.ShadowViewDrawable;
import java.util.ArrayList;
import java.util.List;

@BA.ShortName("xNavigationBar")
@BA.Events(values = {"Click (view as Object)", "OnTouch(action as int),OnlongClick(v as View)"})
public class xNavigationBar extends AbsObjectWrapper<android.view.View> {

    private Menu menu;
    private LinearLayout bottomNavBar = null;
    private String eventname = "";
    private BA _ba;
    private List<View> viewItem = null;

    public void initialize(final BA ba, String BAevent) {
        _ba = ba;
        eventname = BAevent.toLowerCase();
        viewItem = new ArrayList<View>();
        setObject(new View(ba.context));

        FrameLayout rootLayout = new FrameLayout(ba.context);
        rootLayout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));

        FrameLayout mainContent = new FrameLayout(ba.context);
        FrameLayout.LayoutParams mainContentParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        mainContentParams.bottomMargin = 120;
        mainContent.setLayoutParams(mainContentParams);
        rootLayout.addView(mainContent);

        bottomNavBar = new LinearLayout(ba.context);
        FrameLayout.LayoutParams bottomNavBarParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                120
        );
        bottomNavBarParams.gravity = Gravity.BOTTOM;
        bottomNavBar.setLayoutParams(bottomNavBarParams);
        bottomNavBar.setOrientation(LinearLayout.HORIZONTAL);

        rootLayout.addView(bottomNavBar);

        this.setObject(rootLayout);
    }

    public void setBackgroundColor(String color) {
        bottomNavBar.setBackgroundColor(android.graphics.Color.parseColor(color));
    }

    public void setBackgroundDrawable(Drawable value) {
        bottomNavBar.setBackground(value);
    
    }

    public void setBackgroundResource(int ResourceID) {    
         bottomNavBar.setBackgroundResource(ResourceID);
    }
    
    public void setPadding(int left, int top, int right, int bottom) {
        bottomNavBar.setPadding(left, top, right, bottom);
    }
    
    private int marginLeft;
    private int marginTop;
    private int marginRight;
    private int marginBottom;
    
    public void setMargins(int left, int top, int right, int bottom) {
       marginLeft = left;
       marginTop = top;
       marginRight = right;
       marginBottom = bottom;
    }

    public void AddView(View view) {
        viewItem.add(view);
    }
    
    public void setShadow(int Dy, int Radius) {
       ShadowProperty sp = new ShadowProperty()
                .setShadowColor(0x77000000)
                .setShadowDy(Dy)
                .setShadowRadius(Radius)
                .setShadowSide(ShadowProperty.ALL);
        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.BLUE, 0, 0);
        getObject().setBackground(sd);
        getObject().setLayerType(marginTop, new Paint());
    }

    public void Build() {
        for (View item : viewItem) {
            View button = item;
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f
                );
            buttonParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
            button.setLayoutParams(buttonParams);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _ba.raiseEventFromUI(xNavigationBar.this, eventname + "_click", v);
                }
            });
            
            bottomNavBar.addView(button);
        }

    }

    public void AddMenu(int id, String name, Drawable icon) {
        this.menu.add(Menu.NONE, 1, Menu.NONE, name).setIcon(icon);
    }

}
