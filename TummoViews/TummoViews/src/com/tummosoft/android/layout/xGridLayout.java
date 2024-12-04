package com.tummosoft.android.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.List;
import anywheresoftware.b4a.objects.collections.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@BA.DesignerProperties(values = {
    @BA.Property(key = "mPadding", displayName = "Padding", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mDrawable", displayName = "Background", fieldType = "color", defaultValue = "0xFF630DF7"),})
@BA.ActivityObject
@BA.ShortName("xGridLayout")
@BA.Events(values = {"ItemClick(position as int, id as long)", "ItemSelected(position as int, id as long)"})
public class xGridLayout extends AbsObjectWrapper<GridView> implements Common.DesignerCustomView {

    private static Context baContext;
    private String _eventName;
    private int _width;
    private BA _ba;
    private GridView grid = null;

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        grid = new GridView(baContext);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(base.getWidth(), base.getHeight());
        grid.setLayoutParams(layout);
        grid.setColumnWidth(_width);
        int padding = ConvertToDP((int) props.Get("mPadding"));
        grid.setPadding(padding, padding, padding, padding);
        Drawable drawable = lw.getBackground();
        grid.setBackground(drawable);

        base.AddView(grid, 0, 0, base.getWidth(), base.getHeight());
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {                
                _ba.raiseEventFromUI(grid, _eventName + "_itemclick", position, id);
            }
        });

        grid.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {                
                _ba.raiseEventFromUI(grid, _eventName + "_itemselected", position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> av) {

            }
        });
    }

    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }

    public void setColumnWidth(int width) {
        grid.setColumnWidth(width);
        grid.setNumColumns(width);
    }

    public void setNumColumns(int num) {
        grid.setNumColumns(num);
    }

    public void setHorizontalSpacing(int space) {
        grid.setHorizontalSpacing(space);
    }

    public void setVerticalSpacing(int space) {
        grid.setVerticalSpacing(space);
    }

    public void setPadding(int space) {
        grid.setPadding(space, space, space, space);
    }

    public void setBackground(Drawable background) {
        grid.setBackground(background);
    }

    public View getChildAt(int index) {
        return grid.getChildAt(index);
    }

    public Object getItemAtPosition(int index) {
        return grid.getItemAtPosition(index);
    }

    public void setColor(int color) {
        grid.setBackgroundColor(color);
    }

    public void setStretchMode(int mode) {
        grid.setStretchMode(mode);
    }

    public void setItems(List items) {
        ArrayList<View> it = new ArrayList();
        for (int i = 0; i < items.getSize(); i++) {
            it.add((View) items.Get(i));
        }

        GridItems itemgrid = new GridItems(baContext, it);
        grid.setAdapter(itemgrid);
    }

    @Override
    public void _initialize(BA ba, Object o, String event) {
        baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;
    }

    @Hide
    static public class GridItems extends ArrayAdapter {

        private ArrayList<View> _items = null;

        public GridItems(@NonNull Context context, ArrayList<View> items) {
            super(context, 0, items);
            _items = items;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View dummyView = _items.get(position);

            return dummyView;
        }
    }
    
     public void RemoveViewAt(int index) {
        getObject().removeViewAt(index);
    }
    
    public void RemoveView(View view) {
        getObject().removeView(view);
    }
//     nguyen cho phuoc duc nay hoa giai tat ca ta bua thu, bua meo, bua thang lan, bua tac ke, bua bong den
}

