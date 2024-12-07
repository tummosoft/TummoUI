package com.tummosoft.android.utils;


import anywheresoftware.b4a.AbsObjectWrapper;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import anywheresoftware.b4a.BA;

 @BA.ShortName("CameraHelper")
public class CameraHelper extends AbsObjectWrapper<Camera> {
    private static String eventname = "";
    private BA _ba;
    private boolean hasfocus = false;
    private String oldtext = "";
    private String newtext = "";

    public void initialize(final BA ba, String event) {
       _ba = ba;
       eventname = event.toLowerCase();
        setObject(new Camera());     
    }
    
    public void Save() {
        getObject().save();
        getObject().getLocationX();
    }
    
    public void getLocationX() {        
        getObject().getLocationX();
    }
    
    public void getLocationY() {        
        getObject().getLocationY();
    }
    
    public void getLocationZ() {        
        getObject().getLocationZ();
    }
    
    public void restore() {        
        getObject().restore();
    }
    
    public void restore(Canvas canvas) {        
        getObject().applyToCanvas(canvas);
    }
    
    public void restore(float f, float f1, float f2) {        
        getObject().dotWithNormal(f, f1, f2);
    }
    
    public void rotateX(float value) {        
        getObject().rotateX(value);
    }
    
    public void rotateY(float value) {        
        getObject().rotateY(value);
    }
    
      public void rotateZ(float value) {        
        getObject().rotateZ(value);
    }
    
    public void getMatrix(Matrix value) {        
        getObject().getMatrix(value);
    }
    
    public void restoreX(float value) {        
        getObject().rotateX(value);
    }
    
    public void restoreY(float value) {        
        getObject().rotateY(value);
    }
    
    public void restoreZ(float value) {        
        getObject().rotateZ(value);
    }
    
    public void restoreZ(float f, float f1, float f2) {        
        getObject().setLocation(f, f1, f2);
    }
    
    public void rotate(float x, float y, float z) {        
        getObject().rotate(x, y, z);        
    }
}
