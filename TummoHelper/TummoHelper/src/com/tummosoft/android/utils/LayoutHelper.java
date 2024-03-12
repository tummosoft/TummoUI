package com.tummosoft.android.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import anywheresoftware.b4a.BA;

@BA.ShortName("LayoutHelper")
public class LayoutHelper {

    private Context baContext;
    private String _tag;
    private BA baa;

    public void initialize(final BA ba) {
        baContext = ba.context;
        baa = ba;
    }

    public void CustomLayout(ViewGroup root, int id) {
        ViewGroup viewGroup = root;
        LayoutInflater inflater = LayoutInflater.from(baContext);
        View view = inflater.inflate(id, viewGroup, false);
        viewGroup.addView(view);
    }
    
    public void AddConstraintLayout(ConstraintLayout root, int id) {
        ConstraintLayout viewGroup = root;
        viewGroup.setId(id);
        LayoutInflater inflater = LayoutInflater.from(baContext);
        inflater.inflate(id, viewGroup, true);
    }

//    public void setTag(String tag) {
//        _tag = tag;
//    }
//
//    public String getTag() {
//        return _tag;
//    }

    
}
