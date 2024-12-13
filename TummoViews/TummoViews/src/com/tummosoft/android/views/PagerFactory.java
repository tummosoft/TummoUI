package com.tummosoft.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;

public class PagerFactory {

    private Context mContext;
    private Bitmap m_book_bg = null;

    private int m_backColor = 0xffff9e85; 	// Background color

    private boolean m_isfirstPage, m_islastPage;

    private Paint mPaint;

    public PagerFactory(Context context) {
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Align.LEFT);
        mPaint.setColor(m_backColor);
    }

    public void onDraw(Canvas c) {
        if (m_book_bg == null) {
            Log.d("m_backColor", "m_backColor");
            c.drawColor(m_backColor);
        } else {
            Log.d("drawBitmap1", "drawBitmap");
            c.drawBitmap(m_book_bg, 0, 0, null);
        }

    }

    public void onDraw(Canvas c, Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColor(m_backColor);
        paint.setStrokeWidth(3);
        c.drawBitmap(bitmap, 0, 0, paint);
    }
    
    public void setBgBitmap(Bitmap BG) {
        m_book_bg = BG;
    }
}
