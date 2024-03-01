package com.tummosoft.vector;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.util.TypedValue;
import anywheresoftware.b4a.BA;
import com.tummosoft.vector.VectorDrawableCreator.PathData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

@BA.ShortName("DrawableHelper")
public class DrawableHelper {

    private BA _ba;
    private String _filename;
    private int _color;
    private int _strokeColor;
    private float _viewportWidth;
    private float _viewportHeight;

    public float getViewportWidth() {
        return _viewportWidth;
    }

    public void setViewportWidth(float _viewportWidth) {
        this._viewportWidth = _viewportWidth;
    }

    public float getViewportHeight() {
        return _viewportHeight;
    }

    public void setViewportHeight(float _viewportHeight) {
        this._viewportHeight = _viewportHeight;
    }

    public int getWidth() {
        return _width;
    }

    public void setWidth(int _width) {
        this._width = _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setHeight(int _height) {
        this._height = _height;
    }
    private int _width;
    private int _height;
    
    private Drawable _result;

    public void initialize(final BA ba, String filename) throws SAXException, IOException, ParserConfigurationException {
        _ba = ba;
        this._filename = filename;
    }
    
    public void setStrokeColor(int color) {
        _strokeColor = color;
    }
    private ArrayList<Dot> drawDotList = new ArrayList<Dot>();;
    
    public void drawLines(Canvas canvas){
        Paint linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.parseColor("#FF0033"));
        linePaint.setStrokeWidth(dpToPx(2,_ba.context));
        for(int i=0; i<drawDotList.size()-1; i++){
            canvas.drawLine(drawDotList.get(i).x,
                    drawDotList.get(i).y,
                    drawDotList.get(i+1).x,
                    drawDotList.get(i+1).y,
                    linePaint);
        }
    }

    
    private void parser() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, XmlPullParserException, ClassNotFoundException, NoSuchMethodException, SAXException, ParserConfigurationException {

        List<AndroidVector> pathvec = getVector(_filename);
        if (pathvec.size() > 0) {
            AndroidVector andvec = pathvec.get(0);    
            //BA.Log("VIEW=" + andvec.getViewportHeight());
            List<PathData> pathList = Arrays.asList(new PathData(andvec.getPathData(),  _strokeColor));
            //BA.Log("dip" + _width);
            _result = VectorDrawableCreator.getVectorDrawable(_ba.context, _width, _height, _viewportWidth, _viewportHeight, pathList);
        }
    }

    public Drawable getDrawable() {
       
        try {
            parser();           
        } catch (InstantiationException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlPullParserException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DrawableHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
         return _result;
    }

    private static int dpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    private List<AndroidVector> getVector(String filename) throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        InputStream is = getXMLFileAsStream(filename);
        SAXParser saxParser = factory.newSAXParser();
        VectorXMLParser handler = new VectorXMLParser();
        saxParser.parse(is, handler);

        List<AndroidVector> result = handler.getVectorPath();

        return result;
    }

    private static InputStream getXMLFileAsStream(String filename) throws FileNotFoundException {
        File file = new File(filename);
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }
    
    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null)
            return null;
        else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();

        if (!(intrinsicWidth > 0 && intrinsicHeight > 0))
            return null;

        try {
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                    : Bitmap.Config.RGB_565;
            Bitmap bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    } 
      
    public static GradientDrawable createRectangleDrawable(int color, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
        
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createRectangleDrawable(int[] colors, float cornerRadius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }
    
    public static GradientDrawable createOvalDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }
        
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static GradientDrawable createOvalDrawable(int[] colors) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColors(colors);
        return gradientDrawable;
    }
    
    private int updateSelf(int origin, int target, int velocity){
        if (origin < target) {
            origin += velocity;
        } else if (origin > target){
            origin-= velocity;
        }
        if(Math.abs(target-origin)<velocity){
            origin = target;
        }
        return origin;
    }
    
    class Dot{
        int x;
        int y;
        int data;
        int targetX;
        int targetY;
        int velocity = dpToPx(10,_ba.context);

        Dot(int x,int y,int targetX,int targetY,Integer data){
            this.x = x;
            this.y = y;
            setTargetData(targetX, targetY,data);
        }

        Point getPoint(){
            return new Point(x,y);
        }

        Dot setTargetData(int targetX,int targetY,Integer data){
            this.targetX = targetX;
            this.targetY = targetY;
            this.data = data;
            return this;
        }

        boolean isAtRest(){
            return (x==targetX)&&(y==targetY);
        }

        void update(){
            x = updateSelf(x, targetX, velocity);
            y = updateSelf(y, targetY, velocity);
        }
    }
    
}
