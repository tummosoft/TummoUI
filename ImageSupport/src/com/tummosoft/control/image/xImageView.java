package com.tummosoft.control.image;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import anywheresoftware.b4a.AbsObjectWrapper;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.keywords.Common;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.collections.Map;

import com.tummosoft.Utils.ResourcesHelper;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.CountDownLatch;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@BA.DesignerProperties(values = {
    @BA.Property(key = "mSourceType", displayName = "Source Type", fieldType = "String", defaultValue = "ASSET_BITMAP", list = "ASSET_BITMAP|RES_BITMAP|RES_VECTOR|RES_ANIMATION"),
    @BA.Property(key = "mResourceID", displayName = "Resource ID", fieldType = "String", defaultValue = "none"),
    @BA.Property(key = "mIconPosition", displayName = "Position", fieldType = "String", defaultValue = "LEFT", list = "LEFT|BOTTOM|RIGHT|FULL|CENTER|CENTER_CROP|TOPLEFT"),
    @BA.Property(key = "mRadius", displayName = "Radius", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mCircle", displayName = "Circle", fieldType = "boolean", defaultValue = "false"),
    @BA.Property(key = "mBackgroundColor", displayName = "Background Color", fieldType = "color", defaultValue = "0xFF630DF7"),
    @BA.Property(key = "mBorderColor", displayName = "Border Color", fieldType = "color", defaultValue = "0xFF5B4F4F"),
    @BA.Property(key = "mBorderWidth", displayName = "Border Width", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mRolate", displayName = "Rotate", fieldType = "int", defaultValue = "0"),
    @BA.Property(key = "mEnabled", displayName = "Enabled", fieldType = "boolean", defaultValue = "true"),
    @BA.Property(key = "mEnableSeleted", displayName = "Has Selected", fieldType = "boolean", defaultValue = "false"),
    })
@BA.ShortName("xImageView")
@BA.ActivityObject
@BA.Events(values = {"Click()"})
public class xImageView extends AbsObjectWrapper<AppCompatImageView> implements Common.DesignerCustomView {

    private static Context baContext;
    private String _eventName;
    private int _width = 0;
    private int _height = 0;
    private BA _ba;
    private float mRadius = 0;
    private Drawable cacheDraw = null;
    private Bitmap cacheBitmap = null;
    private int mPadding = 0;
    private boolean mRoundedDrawable = false;
    private boolean mSelected = false;
    private boolean mEnableClick = false;
    private int mBorderWidth;
    private int mBoderColor;
    private int mBackgroundColor;
    private boolean mRounded = false;
    private VectorDrawable mVectorDrawable;
    private AnimationDrawable mAnimationDrawable;
    private boolean mCircle;
    private PanelWrapper _base;
    private int mRolate = 0;
    private FrameLayout frame;
    private Bitmap bitmapError = null;
    private boolean mSeleted = false;
    private boolean mEnableSeleted = false;

    @Override
    public void DesignerCreateView(PanelWrapper base, LabelWrapper lw, Map props) {
        _base = base;
        setObject(new AppCompatImageView(baContext));
        _width = base.getWidth();
        _height = base.getHeight();

        cacheBitmap = null;

        String mIconPosition = (String) props.Get("mIconPosition");
        setPosition(mIconPosition);

        mRolate = (int) props.Get("mRolate");
        mBorderWidth = (int) props.Get("mBorderWidth");
        mBoderColor = (int) props.Get("mBorderColor");
        mBackgroundColor = (int) props.Get("mBackgroundColor");
        mEnableClick = (boolean) props.Get("mEnableClick");
        mRadius = (int) props.Get("mRadius");
        mCircle = (boolean) props.Get("mCircle");
        mEnableSeleted = (boolean) props.Get("mEnableSeleted");
        String mResourceID = (String) props.Get("mResourceID");
        String msource = (String) props.Get("mSourceType");

        int rsid = 0;
        if (msource.equals("ASSET_BITMAP")) {
            Bitmap bm = ResourcesHelper.OpenImage(mResourceID);
            boolean hasCircle = false;
            BuildBitmap(hasCircle, bm, base.getWidth(), base.getHeight());
        } else if (msource.equals("RES_VECTOR")) {
            BuildDrawable(mResourceID);
        }
        int color = Color.parseColor("#34E4D2");
        int[] attrs = new int[]{color};
        TypedArray typedArray = _ba.activity.obtainStyledAttributes(attrs);

        int backgroundResource = typedArray.getResourceId(0, 0);
        getObject().setBackgroundResource(backgroundResource);
        getObject().setPressed(mEnableSeleted);
        setEvent(base.getWidth(), base.getHeight());
    }

    public void setRadius(int value) {
        mRadius = value;
    }

    public void setRolate(int value) {
        mRolate = value;
    }

    public void setBoderColor(int value) {
        mBoderColor = value;
    }

//    public void setEnableClick(boolean value) {
//        mEnableClick = value;
//    }

    private void setEvent(int width, int height) {
        FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(width, height);
        getObject().setLayoutParams(layout);
        frame = new FrameLayout(baContext);
        frame.addView(getObject());
        int left = _base.getLeft();
        int top = _base.getTop();
        //base.AddView(frame, 0, 0, base.getWidth(), base.getHeight());

        ViewGroup parent = (ViewGroup) _base.getParent();
        frame.setX(left);
        frame.setY(top);
        parent.addView(frame);
        if (mRolate != 0) {
            frame.setRotation(mRolate);
        }

        int cx = getObject().getWidth() / 2;
        int cy = getObject().getHeight() / 2;
        int initialRadius = getObject().getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(getObject(), cx, cy, initialRadius, 0);

        frame.setElevation(0.3f);
        if (mEnableClick == true) {
            getObject().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent ev) {
                    if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                getObject().setVisibility(View.INVISIBLE);
                            }
                        });
                        anim.start();
                        _ba.raiseEventFromUI(v, _eventName + "_click", null);
                    }
                    return getObject().onTouchEvent(ev);
                }
            });
        }
    }

    public void setHasCircle(boolean value) {
        mCircle = value;
    }

    public void setBorderWidth(int value) {
        mBorderWidth = value;
    }

    public void setBackgroundDrawable(Drawable value) {
        getObject().setBackgroundDrawable(value);
    }

    private void BuildDrawable(String mResouceName) {
        ResourcesHelper res = new ResourcesHelper();
        res.initialize(_ba);
        int vecid = res.getResourceId(mResouceName, "drawable");
        VectorDrawable drawv = res.getVectorDrawable(vecid);
        getObject().setBackgroundDrawable(drawv);
    }

    private void BuildBitmap(boolean hasCircle, Bitmap bm, int width, int height) {
        if (mRadius > 0) {
            cacheBitmap = RadiusImage(bm, (int) mRadius);
        } else if (mCircle == true) {
            hasCircle = true;
            cacheBitmap = BitmapWithBorder(bm, mBorderWidth, (int) mRadius, mBoderColor, width, height);
        } else {
            cacheBitmap = bm;
        }
        if ((mBorderWidth > 0) && (hasCircle == false)) {
            Bitmap bm2 = BorderImage(cacheBitmap, mBorderWidth, mBoderColor, ConvertToDP((int) mRadius));
            getObject().setBackgroundDrawable(cacheDraw);
            getObject().setImageBitmap(bm2);
            _width = bm2.getWidth();
            _height = bm2.getHeight();

        } else {
            getObject().setImageBitmap(cacheBitmap);
        }
    }

    public void getImageFromURL(String imgUrl) throws NoSuchAlgorithmException, InterruptedException {
        WebImage imgURL = new WebImage();
        imgURL.downloadImage(imgUrl);

        BA.Log("Bitmap has....");

    }

    public void setPosition(String mIconPosition) {
        if (mIconPosition.contains("LEFT")) {
            getObject().setScaleType(ScaleType.FIT_START);
        } else if (mIconPosition.contains("RIGHT")) {
            getObject().setScaleType(ScaleType.FIT_END);
        } else if (mIconPosition.contains("CENTER")) {
            getObject().setScaleType(ScaleType.FIT_CENTER);
        } else if (mIconPosition.contains("FULL")) {
            getObject().setScaleType(ScaleType.FIT_XY);
        } else if (mIconPosition.contains("CENTER_CROP")) {
            getObject().setScaleType(ScaleType.CENTER_CROP);
        } else if (mIconPosition.contains("TOPLEFT")) {
            getObject().setScaleType(ScaleType.MATRIX); 
        }
    }

    private void AnimationAView(View view) {
        int centerX = (int) view.getX() + view.getWidth() / 2;
        view.animate()
                .translationX(centerX - view.getWidth() / 2 - view.getX())
                .translationY(-50)
                .scaleX(1.2f)
                .scaleY(1.2f)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        final ViewPropertyAnimator animator = view.animate();
                        animator
                                .translationX(-centerX + view.getX() + view.getWidth() / 2)
                                .translationY(0)
                                .setStartDelay(200)
                                .scaleX(1.0f)
                                .scaleY(1.0f)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        animator.setListener(null);

                                    }
                                });
                    }
                });
    }

    public Bitmap RadiusImage(Bitmap srcBitmap, int radius) {
        radius = ConvertToDP(radius);
        Bitmap bm = ResourcesHelper.toRoundCorner2(srcBitmap, radius);
        return bm;
    }

    public Bitmap BorderImage(Bitmap src, int borderWidth, int borderColor, int radius) {
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        borderWidth = ConvertToDP(borderWidth);

        RectF shadowRect = new RectF(
                0,
                0,
                width - radius,
                height - radius);
        RectF image = new RectF(
                borderWidth,
                borderWidth,
                width - (radius + (borderWidth * 2)),
                height - (radius + (borderWidth * 2)));
        int dy = borderWidth;
        int dx = borderWidth;
        if (dy > 0) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
            image.top += dy;
            image.bottom -= dy;
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
            image.top += Math.abs(dy);
            image.bottom -= Math.abs(dy);
        }

        if (dx > 0) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
            image.left += dx;
            image.right -= dx;
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
            image.left += Math.abs(dx);
            image.right -= Math.abs(dx);
        }

        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setStrokeWidth(borderWidth * 2);
        shadowPaint.setColor(borderColor);
        shadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(shadowRect, radius, radius, shadowPaint);
        canvas.drawBitmap(src, null, shadowRect, null);

        return output;
    }

    public void setBimap(Bitmap bm) {
        BuildBitmap(mCircle, bm, getObject().getWidth(), getObject().getHeight());
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }
    
    private float degree;

    public void setDegree(float degree) {
        this.degree = degree;
    }
    

    public int ConvertToDP(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, baContext.getResources().getDisplayMetrics());
    }

    @Hide
    @Override
    public void _initialize(BA ba, Object o, String event) {
        baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;
    }

    public void initialize2(BA ba, String event) {
        baContext = ba.context;
        _eventName = event.toLowerCase();
        _ba = ba;
        setObject(new AppCompatImageView(baContext));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        getObject().setLayoutParams(layout);

        getObject().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                    if (mEnableClick == true) {
                        _ba.raiseEventFromUI(v, _eventName + "_click", null);
                    }
                }
                return getObject().onTouchEvent(ev);
            }
        });

    }

    public View getView() {
        return getObject();
    }

    public void setRoundedDrawable(boolean value) {
        mRoundedDrawable = value;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }

    private boolean mCircular = false;

    public void setCircular(boolean value) {
        mCircular = true;
    }

    public void setEnableSelected(boolean value) {
        mSeleted = value;
    }

    public void setDrawable(Drawable draw) {
        getObject().setBackgroundDrawable(draw);
    }

    private Bitmap BitmapWithBorder(Bitmap srcBitmap, int mBorderThickness, int radius, int color, int defaultWidth, int defaultHeight) {
        Bitmap output = Bitmap.createBitmap(defaultWidth, defaultHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        radius = (defaultWidth < defaultHeight ? defaultWidth
                : defaultHeight) / 2 - 2 * 5;

        drawCircleBorder(canvas, radius + mBorderThickness / 2, color, defaultWidth, defaultHeight);
        drawCircleBorder(canvas, radius + mBorderThickness + mBorderThickness / 2, color, defaultWidth, defaultHeight);

        Bitmap roundBitmap = getCroppedRoundBitmap(srcBitmap, radius);
        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight
                / 2 - radius, null);

        return output;

    }

    private Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {

        Bitmap scaledSrcBmp;
        int diameter = radius * 2;

        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else if (bmpHeight < bmpWidth) {
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
                    squareHeight);
        } else {
            squareBitmap = bmp;
        }

        if (squareBitmap.getWidth() != diameter
                || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
                    diameter, true);

        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);

        return output;
    }

    private void drawCircleBorder(Canvas canvas, int radius, int color, int defaultWidth, int defaultHeight) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(mBorderWidth);
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }
    
    public void setBitmapLoading(Bitmap bm) {
        getObject().setImageBitmap(bm);
    }

    public void setBitmapError(Bitmap bm) {
        bitmapError = bm;
    }

    private class WebImage {

        private static final int CONNECT_TIMEOUT = 5000;
        private static final int READ_TIMEOUT = 8000;
        private OkHttpClient client = null;

        private OkHttpClient getUnsafeOkHttpClient() {
            try {
                // Tạo TrustManager chấp nhận tất cả các chứng chỉ
                final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
                };

                // Tạo SSLContext
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new SecureRandom());

                // Tạo OkHttpClient
                return new OkHttpClient.Builder()
                        .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                        .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void downloadImage(String imageUrl) throws InterruptedException {
           
            new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... params) {
                    return cacheBitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    getObject().setImageBitmap(cacheBitmap);
                    
                    _ba.activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                             CountDownLatch countDownLatch = new CountDownLatch(1);
                            try {
                                client = getUnsafeOkHttpClient();
                                Request request = new Request.Builder()
                                        .url(imageUrl)
                                        .head()
                                        .build();
                                
                                Call call = client.newCall(request);
                                Response response = call.execute();
                                long fileSize = Long.parseLong(response.header("Content-Length"));
                                BA.Log("Size:" + fileSize + " bytes");
                                Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                                 
                                if (bm != null) {
                                    int width = bm.getWidth();
                                    int height = bm.getHeight();
                                    BuildBitmap(mCircle, bm, width, height);  
                                     
                                }
                                
                                if (mEnableClick == true) {
                                    getObject().setOnTouchListener(new View.OnTouchListener() {
                                        boolean x = false;
                                        @Override
                                        public boolean onTouch(View v, MotionEvent ev) {                                            
                                            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                                                _ba.raiseEventFromUI(v, _eventName + "_click", null);
                                            }
                                            return getObject().onTouchEvent(ev);
                                        }
                                    });
                                }
                               
                                cacheBitmap = null;
                            } catch (IOException ex) {
                                BA.LogError(ex.getMessage());
                            }
                        }
                    });
                }
            }.execute();
            
        }
    }
}
