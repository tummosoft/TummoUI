package com.tummosoft.android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;
import anywheresoftware.b4a.BA.Hide;

@Hide
public class Pager extends View {

    private int mWidth = 1280;
    private int mHeight = 768;
    private int mCornerX = 0; // The footer corresponding to the drag point
    private int mCornerY = 0;
    private Path mPath0;
    private Path mPath1;
    Bitmap mCurPageBitmap = null; // Current Page
    Bitmap mNextPageBitmap = null;

    PointF mTouch = new PointF();        // Drag point
    PointF mBezierStart1 = new PointF();        // Bezier curve starting point
    PointF mBezierControl1 = new PointF();        // Bezier curve control points
    PointF mBeziervertex1 = new PointF();        // Bezier curve vertices
    PointF mBezierEnd1 = new PointF();            // Bezier curve end point

    PointF mBezierStart2 = new PointF();        // Another Bezier curve
    PointF mBezierControl2 = new PointF();
    PointF mBeziervertex2 = new PointF();
    PointF mBezierEnd2 = new PointF();

    float mMiddleX;
    float mMiddleY;
    float mDegrees;
    float mTouchToCornerDis;
    ColorMatrixColorFilter mColorMatrixFilter;
    Matrix mMatrix;
    float[] mMatrixArray = {0, 0, 0, 0, 0, 0, 0, 0, 1.0f};

    boolean mIsRTandLB; // Whether it belongs to right, top, left, or bottom
    float mMaxLength = (float) Math.hypot(mWidth, mHeight);
    int[] mBackShadowColors;
    int[] mFrontShadowColors;
    GradientDrawable mBackShadowDrawableLR;
    GradientDrawable mBackShadowDrawableRL;
    GradientDrawable mFolderShadowDrawableLR;
    GradientDrawable mFolderShadowDrawableRL;

    GradientDrawable mFrontShadowDrawableHBT;
    GradientDrawable mFrontShadowDrawableHTB;
    GradientDrawable mFrontShadowDrawableVLR;
    GradientDrawable mFrontShadowDrawableVRL;

    Paint mPaint;
    Scroller mScroller;

    public Pager(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.mWidth = screenWidth;        // Pager width and height
        this.mHeight = screenHeight;

        mPath0 = new Path();
        mPath1 = new Path();
        createDrawable();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);

        ColorMatrix cm = new ColorMatrix();
        float array[] = {0.55f, 0, 0, 0, 80.0f, 0, 0.55f, 0, 0, 80.0f, 0, 0,
                0.55f, 0, 80.0f, 0, 0, 0, 0.2f, 0};
        cm.set(array);
        mColorMatrixFilter = new ColorMatrixColorFilter(cm);
        mMatrix = new Matrix();
        mScroller = new Scroller(getContext());

        mTouch.x = 0.01f;        // Do not let x and y be 0, otherwise there will be problems when calculating points
        mTouch.y = 0.01f;
    }

    /**
     * Calculate the drag foot corresponding to the drag point
     */
    public void calcCornerXY(float x, float y) {
        if (x <= mWidth / 2)
            mCornerX = 0;
        else
            mCornerX = mWidth;
        if (y <= mHeight / 2)
            mCornerY = 0;
        else
            mCornerY = mHeight;
        if ((mCornerX == 0 && mCornerY == mHeight) || (mCornerX == mWidth && mCornerY == 0))
            mIsRTandLB = true;
        else
            mIsRTandLB = false;
    }

    public boolean doTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mTouch.x = event.getX();
            mTouch.y = event.getY();
            this.postInvalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mTouch.x = event.getX();
            mTouch.y = event.getY();
            // calcCornerXY(mTouch.x, mTouch.y);
            // this.postInvalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (canDragOver()) {
                startAnimation(1200);
            } else {
                mTouch.x = mCornerX - 0.09f;
                mTouch.y = mCornerY - 0.09f;
            }

            this.postInvalidate();
        }
        // return super.onTouchEvent(event);
        return true;
    }

    /**
     * Solve the coordinates of the intersection of lines P1P2 and P3P4
     */
    public PointF getCross(PointF P1, PointF P2, PointF P3, PointF P4) {
        PointF CrossP = new PointF();
       // General formula of binary function: y=ax+b
        float a1 = (P2.y - P1.y) / (P2.x - P1.x);
        float b1 = ((P1.x * P2.y) - (P2.x * P1.y)) / (P1.x - P2.x);

        float a2 = (P4.y - P3.y) / (P4.x - P3.x);
        float b2 = ((P3.x * P4.y) - (P4.x * P3.y)) / (P3.x - P4.x);
        CrossP.x = (b2 - b1) / (a1 - a2);
        CrossP.y = a1 * CrossP.x + b1;
        return CrossP;
    }

    private void calcPoints() {
        mMiddleX = (mTouch.x + mCornerX) / 2;
        mMiddleY = (mTouch.y + mCornerY) / 2;
        mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY) * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
        mBezierControl1.y = mCornerY;
        mBezierControl2.x = mCornerX;
        mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);

        mBezierStart1.x = mBezierControl1.x - (mCornerX - mBezierControl1.x) / 2;
        mBezierStart1.y = mCornerY;

      // When mBezierStart1.x < 0 or mBezierStart1.x > 480
// If you continue to turn the page, a BUG will occur, so limit it here
        if (mTouch.x > 0 && mTouch.x < mWidth) {
            if (mBezierStart1.x < 0 || mBezierStart1.x > mWidth) {
                if (mBezierStart1.x < 0)
                    mBezierStart1.x = mWidth - mBezierStart1.x;

                float f1 = Math.abs(mCornerX - mTouch.x);
                float f2 = mWidth * f1 / mBezierStart1.x;
                mTouch.x = Math.abs(mCornerX - f2);

                float f3 = Math.abs(mCornerX - mTouch.x)
                        * Math.abs(mCornerY - mTouch.y) / f1;
                mTouch.y = Math.abs(mCornerY - f3);

                mMiddleX = (mTouch.x + mCornerX) / 2;
                mMiddleY = (mTouch.y + mCornerY) / 2;

                mBezierControl1.x = mMiddleX - (mCornerY - mMiddleY) * (mCornerY - mMiddleY) / (mCornerX - mMiddleX);
                mBezierControl1.y = mCornerY;

                mBezierControl2.x = mCornerX;
                mBezierControl2.y = mMiddleY - (mCornerX - mMiddleX) * (mCornerX - mMiddleX) / (mCornerY - mMiddleY);
                mBezierStart1.x = mBezierControl1.x - (mCornerX - mBezierControl1.x) / 2;
            }
        }
        mBezierStart2.x = mCornerX;
        mBezierStart2.y = mBezierControl2.y - (mCornerY - mBezierControl2.y) / 2;

        mTouchToCornerDis = (float) Math.hypot((mTouch.x - mCornerX), (mTouch.y - mCornerY));

        mBezierEnd1 = getCross(mTouch, mBezierControl1, mBezierStart1, mBezierStart2);
        mBezierEnd2 = getCross(mTouch, mBezierControl2, mBezierStart1, mBezierStart2);
/*
* mBeziervertex1.x derivation
* ((mBezierStart1.x+mBezierEnd1.x)/2+mBezierControl1.x)/2 is equivalent to
* (mBezierStart1.x+ 2*mBezierControl1.x+mBezierEnd1.x) / 4
*/
        mBeziervertex1.x = (mBezierStart1.x + 2 * mBezierControl1.x + mBezierEnd1.x) / 4;
        mBeziervertex1.y = (2 * mBezierControl1.y + mBezierStart1.y + mBezierEnd1.y) / 4;
        mBeziervertex2.x = (mBezierStart2.x + 2 * mBezierControl2.x + mBezierEnd2.x) / 4;
        mBeziervertex2.y = (2 * mBezierControl2.y + mBezierStart2.y + mBezierEnd2.y) / 4;
    }

    private void drawCurrentPageArea(Canvas canvas, Bitmap bitmap, Path path) {
        path.reset();
        mPath0.reset();
        mPath0.moveTo(mBezierStart1.x, mBezierStart1.y);
        mPath0.quadTo(mBezierControl1.x, mBezierControl1.y, mBezierEnd1.x, mBezierEnd1.y);
        mPath0.lineTo(mTouch.x, mTouch.y);
        mPath0.lineTo(mBezierEnd2.x, mBezierEnd2.y);
        mPath0.quadTo(mBezierControl2.x, mBezierControl2.y, mBezierStart2.x, mBezierStart2.y);
        mPath0.lineTo(mCornerX, mCornerY);
        mPath0.close();

        canvas.save();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

			float height = getHeight();
			float width = getWidth();
			Path mPathXOR = new Path();
			mPathXOR.moveTo(0, 0);
			mPathXOR.lineTo(width, 0);
			mPathXOR.lineTo(width, height);
			mPathXOR.lineTo(0, height);
			mPathXOR.lineTo(0, 0);
			mPathXOR.close();
          // The above can be done by drawing a Path of the same size according to the actual size of the Canvas or View
            mPathXOR.op(path, Path.Op.XOR);
            canvas.clipPath(mPathXOR);

        } else {
            canvas.clipPath(path, Region.Op.XOR);
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.restore();
    }

    private void drawNextPageAreaAndShadow(Canvas canvas, Bitmap bitmap) {
        mPath1.reset();
        mPath1.moveTo(mBezierStart1.x, mBezierStart1.y);
        mPath1.lineTo(mBeziervertex1.x, mBeziervertex1.y);
        mPath1.lineTo(mBeziervertex2.x, mBeziervertex2.y);
        mPath1.lineTo(mBezierStart2.x, mBezierStart2.y);
        mPath1.lineTo(mCornerX, mCornerY);
        mPath1.close();

        mDegrees = (float) Math.toDegrees(Math.atan2(mBezierControl1.x - mCornerX, mBezierControl2.y - mCornerY));
        int leftx;
        int rightx;
        GradientDrawable mBackShadowDrawable;
        if (mIsRTandLB) {
            leftx = (int) (mBezierStart1.x);
            rightx = (int) (mBezierStart1.x + mTouchToCornerDis / 4);
            mBackShadowDrawable = mBackShadowDrawableLR;
        } else {
            leftx = (int) (mBezierStart1.x - mTouchToCornerDis / 4);
            rightx = (int) mBezierStart1.x;
            mBackShadowDrawable = mBackShadowDrawableRL;
        }
        canvas.save();
//        canvas.clipPath(mPath0);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

			float height = getHeight();
			float width = getWidth();
			Path mPathXOR = new Path();
			mPathXOR.moveTo(0, 0);
			mPathXOR.lineTo(width, 0);
			mPathXOR.lineTo(width, height);
			mPathXOR.lineTo(0, height);
//			mPathXOR.lineTo(0, 0);
			mPathXOR.close();

            mPathXOR.op(mPath0, Path.Op.INTERSECT);
            canvas.clipPath(mPathXOR);

            Path mPathXOR1 = new Path();
            mPathXOR1.moveTo(0, 0);
            mPathXOR1.lineTo(width, 0);
            mPathXOR1.lineTo(width, height);
            mPathXOR1.lineTo(0, height);
            mPathXOR1.lineTo(0, 0);
            mPathXOR1.close();
			// The above can be done by drawing a Path of the same size according to the actual size of the Canvas or View
			mPathXOR1.op(mPath1, Path.Op.INTERSECT);
			canvas.clipPath(mPathXOR1);

//			canvas.clipPath(path);
		} else {
            canvas.clipPath(mPath0, Region.Op.INTERSECT);
			canvas.clipPath(mPath1, Region.Op.INTERSECT);
		}

        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.rotate(mDegrees, mBezierStart1.x, mBezierStart1.y);
        mBackShadowDrawable.setBounds(leftx, (int) mBezierStart1.y, rightx, (int) (mMaxLength + mBezierStart1.y));
        mBackShadowDrawable.draw(canvas);
        canvas.restore();
    }

    public void setBitmaps(Bitmap bm1, Bitmap bm2) {
        mCurPageBitmap = getResizedBitmap(bm1, mWidth, mHeight, true);
        mNextPageBitmap = getResizedBitmap(bm2, mWidth, mHeight, true);
    }
    
      private static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, boolean isNecessaryToKeepOrig) {
    int width = bm.getWidth();
    int height = bm.getHeight();
    float scaleWidth = ((float) newWidth) / width;
    float scaleHeight = ((float) newHeight) / height;

    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);

    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    if (!isNecessaryToKeepOrig) {
        bm.recycle();
    }
    return resizedBitmap;
}

    public void setScreen(int w, int h) {
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xFFAAAAAA);
        calcPoints();
        drawCurrentPageArea(canvas, mCurPageBitmap, mPath0);
        drawNextPageAreaAndShadow(canvas, mNextPageBitmap);
        drawCurrentPageShadow(canvas);
        drawCurrentBackArea(canvas, mCurPageBitmap);
    }

    /**
* Create a GradientDrawable for the shadow
*/
    private void createDrawable() {
        int[] color = {0x333333, 0xb0333333};
        mFolderShadowDrawableRL = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, color);
        mFolderShadowDrawableRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFolderShadowDrawableLR = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color);
        mFolderShadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackShadowColors = new int[]{0xff111111, 0x111111};
        mBackShadowDrawableRL = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, mBackShadowColors);
        mBackShadowDrawableRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mBackShadowDrawableLR = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mBackShadowColors);
        mBackShadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowColors = new int[]{0x80111111, 0x111111};
        mFrontShadowDrawableVLR = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, mFrontShadowColors);
        mFrontShadowDrawableVLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        mFrontShadowDrawableVRL = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, mFrontShadowColors);
        mFrontShadowDrawableVRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowDrawableHTB = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, mFrontShadowColors);
        mFrontShadowDrawableHTB.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        mFrontShadowDrawableHBT = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, mFrontShadowColors);
        mFrontShadowDrawableHBT.setGradientType(GradientDrawable.LINEAR_GRADIENT);
    }

  /**
* Draw the shadow of the flipped page
*/
    public void drawCurrentPageShadow(Canvas canvas) {
        double degree;
        if (mIsRTandLB) {
            degree = Math.PI / 4 - Math.atan2(mBezierControl1.y - mTouch.y, mTouch.x - mBezierControl1.x);
        } else {
            degree = Math.PI / 4 - Math.atan2(mTouch.y - mBezierControl1.y, mTouch.x - mBezierControl1.x);
        }
       // The distance between the flip page shadow vertex and the touch point
        double d1 = (float) 25 * 1.414 * Math.cos(degree);
        double d2 = (float) 25 * 1.414 * Math.sin(degree);
        float x = (float) (mTouch.x + d1);
        float y;
        if (mIsRTandLB) {
            y = (float) (mTouch.y + d2);
        } else {
            y = (float) (mTouch.y - d2);
        }
        mPath1.reset();
        mPath1.moveTo(x, y);
        mPath1.lineTo(mTouch.x, mTouch.y);
        mPath1.lineTo(mBezierControl1.x, mBezierControl1.y);
        mPath1.lineTo(mBezierStart1.x, mBezierStart1.y);
        mPath1.close();
        float rotateDegrees;
        canvas.save();
        convasPath(canvas);


        int leftx;
        int rightx;
        GradientDrawable mCurrentPageShadow;
        if (mIsRTandLB) {
            leftx = (int) (mBezierControl1.x);
            rightx = (int) mBezierControl1.x + 25;
            mCurrentPageShadow = mFrontShadowDrawableVLR;
        } else {
            leftx = (int) (mBezierControl1.x - 25);
            rightx = (int) mBezierControl1.x + 1;
            mCurrentPageShadow = mFrontShadowDrawableVRL;
        }

        rotateDegrees = (float) Math.toDegrees(Math.atan2(mTouch.x - mBezierControl1.x, mBezierControl1.y - mTouch.y));
        canvas.rotate(rotateDegrees, mBezierControl1.x, mBezierControl1.y);
        mCurrentPageShadow.setBounds(leftx, (int) (mBezierControl1.y - mMaxLength), rightx, (int) (mBezierControl1.y));
        mCurrentPageShadow.draw(canvas);
        canvas.restore();

        mPath1.reset();
        mPath1.moveTo(x, y);
        mPath1.lineTo(mTouch.x, mTouch.y);
        mPath1.lineTo(mBezierControl2.x, mBezierControl2.y);
        mPath1.lineTo(mBezierStart2.x, mBezierStart2.y);
        mPath1.close();
        canvas.save();
        convasPath(canvas);

        if (mIsRTandLB) {
            leftx = (int) (mBezierControl2.y);
            rightx = (int) (mBezierControl2.y + 25);
            mCurrentPageShadow = mFrontShadowDrawableHTB;
        } else {
            leftx = (int) (mBezierControl2.y - 25);
            rightx = (int) (mBezierControl2.y + 1);
            mCurrentPageShadow = mFrontShadowDrawableHBT;
        }
        rotateDegrees = (float) Math.toDegrees(Math.atan2(mBezierControl2.y - mTouch.y, mBezierControl2.x - mTouch.x));
        canvas.rotate(rotateDegrees, mBezierControl2.x, mBezierControl2.y);
        float temp;
        if (mBezierControl2.y < 0)
            temp = mBezierControl2.y - mHeight;
        else
            temp = mBezierControl2.y;

        int hmg = (int) Math.hypot(mBezierControl2.x, temp);
        if (hmg > mMaxLength)
            mCurrentPageShadow.setBounds((int) (mBezierControl2.x - 25) - hmg, leftx, (int) (mBezierControl2.x + mMaxLength) - hmg, rightx);
        else
            mCurrentPageShadow.setBounds((int) (mBezierControl2.x - mMaxLength), leftx, (int) (mBezierControl2.x), rightx);

        mCurrentPageShadow.draw(canvas);
        canvas.restore();
    }

    private void convasPath(Canvas canvas) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
			float height = getHeight();
			float width = getWidth();
			Path mPathXOR = new Path();
			mPathXOR.moveTo(0, 0);
			mPathXOR.lineTo(width, 0);
			mPathXOR.lineTo(width, height);
			mPathXOR.lineTo(0, height);
            mPathXOR.lineTo(0, 0);
            mPathXOR.close();

            Path mPathXOR1 = new Path();
            mPathXOR1.moveTo(0, 0);
            mPathXOR1.lineTo(width, 0);
            mPathXOR1.lineTo(width, height);
            mPathXOR1.lineTo(0, height);
            mPathXOR1.lineTo(0, 0);
            mPathXOR1.close();

           // The above can be done by drawing a Path of the same size according to the actual size of the Canvas or View
            mPathXOR.op(mPath0, Path.Op.XOR);
            canvas.clipPath(mPathXOR);

            mPathXOR1.op(mPath1, Path.Op.INTERSECT);
            canvas.clipPath(mPathXOR1);

        } else {
            canvas.clipPath(mPath0, Region.Op.XOR);
            canvas.clipPath(mPath1, Region.Op.INTERSECT);
        }
    }

   /**
* Draw the back of the flip page
*/
    private void drawCurrentBackArea(Canvas canvas, Bitmap bitmap) {
        int i = (int) (mBezierStart1.x + mBezierControl1.x) / 2;
        float f1 = Math.abs(i - mBezierControl1.x);
        int i1 = (int) (mBezierStart2.y + mBezierControl2.y) / 2;
        float f2 = Math.abs(i1 - mBezierControl2.y);
        float f3 = Math.min(f1, f2);
        mPath1.reset();
        mPath1.moveTo(mBeziervertex2.x, mBeziervertex2.y);
        mPath1.lineTo(mBeziervertex1.x, mBeziervertex1.y);
        mPath1.lineTo(mBezierEnd1.x, mBezierEnd1.y);
        mPath1.lineTo(mTouch.x, mTouch.y);
        mPath1.lineTo(mBezierEnd2.x, mBezierEnd2.y);
        mPath1.close();
        GradientDrawable mFolderShadowDrawable;
        int left;
        int right;
        if (mIsRTandLB) {
            left = (int) (mBezierStart1.x - 1);
            right = (int) (mBezierStart1.x + f3 + 1);
            mFolderShadowDrawable = mFolderShadowDrawableLR;
        } else {
            left = (int) (mBezierStart1.x - f3 - 1);
            right = (int) (mBezierStart1.x + 1);
            mFolderShadowDrawable = mFolderShadowDrawableRL;
        }
        canvas.save();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

			float height = getHeight();
			float width = getWidth();
			Path mPathXOR = new Path();
			mPathXOR.moveTo(0, 0);
			mPathXOR.lineTo(width, 0);
			mPathXOR.lineTo(width, height);
			mPathXOR.lineTo(0, height);
//			mPathXOR.lineTo(0, 0);
			mPathXOR.close();
            mPathXOR.op(mPath0, Path.Op.INTERSECT);
            canvas.clipPath(mPathXOR);

            Path mPathXOR1 = new Path();
            mPathXOR1.moveTo(0, 0);
            mPathXOR1.lineTo(width, 0);
            mPathXOR1.lineTo(width, height);
            mPathXOR1.lineTo(0, height);
            mPathXOR1.lineTo(0, 0);
            mPathXOR1.close();
			// The above can be done by drawing a Path of the same size according to the actual size of the Canvas or View
			mPathXOR1.op(mPath1, Path.Op.INTERSECT);
			canvas.clipPath(mPathXOR1);

		} else {
            canvas.clipPath(mPath0, Region.Op.INTERSECT);
			canvas.clipPath(mPath1, Region.Op.INTERSECT);
		}

        mPaint.setColorFilter(mColorMatrixFilter);

        float dis = (float) Math.hypot(mCornerX - mBezierControl1.x, mBezierControl2.y - mCornerY);
        float f8 = (mCornerX - mBezierControl1.x) / dis;
        float f9 = (mBezierControl2.y - mCornerY) / dis;
        mMatrixArray[0] = 1 - 2 * f9 * f9;
        mMatrixArray[1] = 2 * f8 * f9;
        mMatrixArray[3] = mMatrixArray[1];
        mMatrixArray[4] = 1 - 2 * f8 * f8;
        mMatrix.reset();
        mMatrix.setValues(mMatrixArray);
        mMatrix.preTranslate(-mBezierControl1.x, -mBezierControl1.y);
        mMatrix.postTranslate(mBezierControl1.x, mBezierControl1.y);
        canvas.drawBitmap(bitmap, mMatrix, mPaint);
        //canvas.drawBitmap(bitmap, mMatrix, null);
        mPaint.setColorFilter(null);
        canvas.rotate(mDegrees, mBezierStart1.x, mBezierStart1.y);
        mFolderShadowDrawable.setBounds(left, (int) mBezierStart1.y, right, (int) (mBezierStart1.y + mMaxLength));
        mFolderShadowDrawable.draw(canvas);
        canvas.restore();
    }

    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            float x = mScroller.getCurrX();
            float y = mScroller.getCurrY();
            mTouch.x = x;
            mTouch.y = y;
            postInvalidate();
        }
    }

    private void startAnimation(int delayMillis) {
        int dx, dy; // dx is the distance to slide horizontally, a negative value will cause the scroll to scroll to the left dy is the distance to slide vertically, a negative value will cause the scroll to scroll upward
        if (mCornerX > 0) {
            dx = -(int) (mWidth + mTouch.x);
        } else {
            dx = (int) (mWidth - mTouch.x + mWidth);
        }
        if (mCornerY > 0) {
            dy = (int) (mHeight - mTouch.y);
        } else {
            dy = (int) (1 - mTouch.y); // Prevent mTouch.y from becoming 0
        }
        mScroller.startScroll((int) mTouch.x, (int) mTouch.y, dx, dy, delayMillis);
    }

    public void abortAnimation() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
        }
    }

    public boolean canDragOver() {
        if (mTouchToCornerDis > mWidth / 10)
            return true;
        return false;
    }

  /**
* Whether to flip from left to right
*/
    public boolean DragToRight() {
        if (mCornerX > 0)
            return false;
        return true;
    }

}
