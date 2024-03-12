package com.tummosoft.animation.line;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Hide
public class LineView extends View {
    private int width, height;
    private Paint bgPaint;
    private Paint scaleTxtPaint;
    private Paint linePaint;
    private Paint bigCirPaint ;
    private Paint smallCirPaint ;
    private float incressFloat;
    private List<Float> yList = new ArrayList<>();
    private List<Float> yShalfList = new ArrayList<>();
    private List<String> xList = new ArrayList<>();
    private float bootomDes = 10;
    private float leftDes = 10;
    private int leftTxtWidth;
    private int bottomTxtHeight;
    private Rect textRect;
    private float showLineAreaWidth;
    private float showLineAreaHeight;
    private int yGapSize = 6;
    private int bgColor = 0x66000000;   
    private int scaleTxtSize = 13;
    private int scaleTxtColor = 0xffffffff;
    private int lineColor = 0xff2791dc;
    private int lindWidth = 2;
    private float xGap;
    private float yGap;
    private float x,y;
    private Float min;
    private Float max;
    private int outCircleColor =0xff2791dc;
    private int innCircleColor =0xffffffff;
    private int DOT_INNER_CIR_RADIUS;
    private int DOT_OUTER_CIR_RADIUS;

    public LineView(Context context) {
        this(context, null);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }
    
    public void setStrokeHeight(int value) {
        lindWidth = value;
    }

    private void initPaint() {
        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setDither(true);
        bgPaint.setColor(bgColor);

        scaleTxtPaint = new Paint();
        scaleTxtPaint.setAntiAlias(true);
        scaleTxtPaint.setDither(true);
        scaleTxtPaint.setTextAlign(Paint.Align.CENTER);
        scaleTxtPaint.setTextSize((float) sp2px(this.getContext(), scaleTxtSize));
        scaleTxtPaint.setColor(scaleTxtColor);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth((float) dp2px(this.getContext(), lindWidth));
        linePaint.setStyle(Paint.Style.FILL);

        DOT_INNER_CIR_RADIUS = dp2px(this.getContext(), 2.0F);
        DOT_OUTER_CIR_RADIUS = dp2px(this.getContext(), 5.0F);

        bootomDes = dp2px(this.getContext(), bootomDes);
        leftDes = dp2px(this.getContext(), leftDes);

        textRect = new Rect();
        scaleTxtPaint.getTextBounds("2.0-", 0, "2.0-".length(), textRect);
        leftTxtWidth = textRect.width();
        scaleTxtPaint.getTextBounds("03-14", 0, "03-14".length(), textRect);
        bottomTxtHeight = textRect.height();

        bigCirPaint = new Paint();
        smallCirPaint = new Paint(bigCirPaint);
        bigCirPaint.setColor(outCircleColor);
        bigCirPaint.setAntiAlias(true);
        smallCirPaint.setColor(innCircleColor);

    }

    public void setData(List<String> xList, List<Float> yList){
        if (xList.size()!=yList.size()){
            throw new RuntimeException("Runtime Error");
        }
        this.xList = xList;
        this.yList = yList;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                height = dp2px(getContext(), 350);
                break;
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        showLineAreaWidth = width - leftTxtWidth - leftDes;
        showLineAreaHeight = height - bootomDes - bottomTxtHeight;

        drawBottomText(canvas);
        drawRectLineAndBg(canvas);
        drawLeftText(canvas);
        drawPointAndLine(canvas);

    }

    private void drawPointAndLine(Canvas canvas) {
        if (xList==null||xList.size()==0){
            return;
        }
        for (int i = 0; i < yList.size(); i++) {
            x= getXPix(i);
            y= getYPix(yList.get(i));
            if (i<=yList.size()-2){
                canvas.drawLine(x,y,getXPix(i+1),getYPix(yList.get(i+1)),linePaint);
            }
            canvas.drawCircle(x, y, (float) this.DOT_OUTER_CIR_RADIUS, bigCirPaint);
            canvas.drawCircle(x, y, (float) this.DOT_INNER_CIR_RADIUS, smallCirPaint);
        }
    }

    private void drawLeftText(Canvas canvas) {
        if (yList == null || yList.size() == 0) {
            return;
        }
        canvas.save();
         max = getUpOrDownNum(getMaxOrMinNum(yList, true), 0, true);
         min = getUpOrDownNum(getMaxOrMinNum(yList, false), 0, false);
        incressFloat = increaseBy6(max, min);
        for (int i = 0; i < yGapSize+1; i++) {
            yShalfList.add(getRoundHalfUp(getUpOrDownNum(getMaxOrMinNum(yList,false),0,false)+(incressFloat*i),1));
        }
        canvas.translate((width - showLineAreaWidth) / 2, showLineAreaHeight);
        yGap = showLineAreaHeight/(yGapSize+2);
        for (Float aFloat : yShalfList) {
            canvas.translate(0,-yGap);
            canvas.drawText(aFloat+" -",0,0,scaleTxtPaint);
        }
        canvas.restore();
    }

    private void drawRectLineAndBg(Canvas canvas) {
        canvas.drawLine(width - showLineAreaWidth, 0, width - showLineAreaWidth, showLineAreaHeight, linePaint);
        canvas.drawLine(width - showLineAreaWidth, showLineAreaHeight, width, showLineAreaHeight, linePaint);
        canvas.drawRect(width - showLineAreaWidth, 0, width, showLineAreaHeight, bgPaint);
    }

    private void drawBottomText(Canvas canvas) {
        if (xList == null || xList.size() == 0) {
            return;
        }
        canvas.save();
        canvas.translate(width - showLineAreaWidth, showLineAreaHeight + ((bootomDes + bottomTxtHeight) / 2) + bottomTxtHeight / 2);
        xGap = showLineAreaWidth / (xList.size() + 1);
        for (String aFloat : xList) {
            canvas.translate(xGap, 0);
            canvas.drawText(aFloat, 0, 0, scaleTxtPaint);
        }
        canvas.restore();
    }


    public Float getYPix(Float y) {
        return showLineAreaHeight-yGap-(((showLineAreaHeight-yGap-yGap)/(max-min))*y);
    }

    public Float getXPix(int i) {
        return width-showLineAreaWidth+xGap*(i+1);
    }

    private Float getUpOrDownNum(Float num, int i, boolean isUp) {
        return isUp ? new BigDecimal(num).setScale(i, BigDecimal.ROUND_UP).floatValue() :
                new BigDecimal(num).setScale(i, BigDecimal.ROUND_DOWN).floatValue();
    }

    private Float getRoundHalfUp(Float num, int i) {
        return  new BigDecimal(num).setScale(i, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    private Float getMaxOrMinNum(List<Float> list, boolean isMax) {
        return isMax ? Collections.max(list) : Collections.min(list);
    }

    private Float increaseBy6(Float a, Float b) {
        return (a - b) / yGapSize;
    }

    private  int dp2px(Context context, float dpVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    private   int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }
}