package com.tummosoft.Utils;

import static android.R.attr.name;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.objects.ViewWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    
    public void setTag(String tag) {
        _tag = tag;
    }
    
    public String getTag() {
        return _tag;
    }
    
    public View getView(String viewname) { 
        int id = baContext.getResources().getIdentifier(viewname, "id", baContext.getPackageName());        
        View textV = baa.activity.findViewById(id);
        
        return textV;
    }
    
     public TextView getTextView(String viewname) { 
        int id = baContext.getResources().getIdentifier(viewname, "id", baContext.getPackageName());        
        TextView textV = (TextView) baa.activity.findViewById(id);
        
        return textV;
    }
     
     public ImageButton getImageButton(String viewname) { 
        int id = baContext.getResources().getIdentifier(viewname, "id", baContext.getPackageName());        
        ImageButton textV = baa.activity.findViewById(id);
        
        return textV;
    }
}
