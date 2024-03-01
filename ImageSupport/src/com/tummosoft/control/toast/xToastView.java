package com.tummosoft.control.toast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import com.tummosoft.Utils.ViewHelper;

@BA.ShortName("xToastView")
public class xToastView extends AbsObjectWrapper<Toast> {     
     private Context _context;
     private Toast toast;
     private TextView text;
     private LinearLayout myLinearLayout;
     private ImageView img;
     private int width = 200;
     private int height = 30;
     
     public void initialize(BA ba, int height, int width) {
         _context = ba.context;
         this.width = convertDpToPixel(width);
         this.height = convertDpToPixel(height);
        toast = new Toast(ba.context);                
         text = new TextView(ba.context); 
         img = new ImageView(ba.context);
        myLinearLayout = new LinearLayout(ba.context);
        
        int w = width - (height + 30); 
        myLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        myLinearLayout.setGravity(1);
    }
               
     public Toast Show(String msg, int length) {  
        text.setText(msg);
        toast.setDuration(length);        
        toast.show();        
        
        return toast;
    }
     
    public void setBackground(Drawable bg) {
        myLinearLayout.setBackground(bg);
    }
     
    public void BitmapIcon(Bitmap bm, int width, int height) {
        img.setImageBitmap(bm);  
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);       
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width,height);  
        imageParams.leftMargin = 10;
        imageParams.rightMargin = 20;
        imageParams.gravity = Gravity.CENTER;
        img.setLayoutParams(imageParams);
    }
     
    public void setTextSize(int size) {
        text.setTextSize(size);
    }
    
    public void setTextColor(int color) {
        text.setTextColor(color);
    }
    
    public void CreateView() {   
        myLinearLayout.addView(img);        
        
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.rightMargin = convertDpToPixel(20);
        textParams.gravity = Gravity.CENTER_VERTICAL;
        text.setLayoutParams(textParams);
        myLinearLayout.addView(text);
        toast.setView(myLinearLayout);
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = _context.getResources().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int)px;
    }
    
}
// Nguyen cho thien phap nay hoa giai tat ca ta thuat xuat phat tu Tran Thi Chia, Pham Thi Vui, nhung ke chay may xe