package com.example.kimsoohyeong.week12;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by KimSooHyeong on 2017. 5. 18..
 */

public class MyOwnCanvas extends View {
    private Bitmap mBitMap;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private boolean isStamp = false;
    private boolean isBluring = false;
    private boolean isColoring = false;
    private boolean isBigWidth = false;
    private int penColor = 0; // 0 : RED, 1 : BLUE
    private final int defaultPenStrokeWidth = 10;
    private final int bigPenStrokeWidth = 50;
    private String operationType = "";

    public MyOwnCanvas(Context context) {
        super(context);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.RED);
    }

    public MyOwnCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitMap != null)
            canvas.drawBitmap(mBitMap, 0, 0, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitMap);
        mCanvas.drawColor(Color.WHITE);
    }

    public void clear() {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        mCanvas.drawColor(Color.WHITE);
        invalidate();
    }

    private final int addX = 100;
    private final int addY = 100;
    private final float scaleFactor = 1.5f;

    private void drawStamp(int x, int y) {
        mCanvas.save();
        Bitmap img = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);

        if (isColoring) {
            float array[] = {
                    2f, 0, 0, 0, -25f,
                    0, 2f, 0, 0, -25f,
                    0, 0, 2f, 0, -25f,
                    0, 0, 0, 2f, 0
            };

            ColorMatrix colorMatrix = new ColorMatrix(array);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
            mPaint.setColorFilter(filter);
        } else {
            mPaint.setColorFilter(null);
        }


        if (isBluring) {
            BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.INNER);
            mPaint.setMaskFilter(blur);
        } else {
            mPaint.setMaskFilter(null);
        }

        if (operationType.equals("rotate")) {
            Log.d("DEBUG", "MODE : " + operationType);
            mCanvas.rotate(30, x, y);
            x -= img.getWidth()/2; y -= img.getHeight()/2;
            mCanvas.drawBitmap(img, x, y, mPaint);
            mCanvas.restore();
            operationType = "";
            return;
        }

        if (operationType.equals("move")) {
            Log.d("DEBUG", "MODE : " + operationType);
            x += addX; y += addY;
        }

        if (operationType.equals("scale")) {
            Log.d("DEBUG", "MODE : " + operationType);
            float scaleX = img.getWidth() * scaleFactor;
            float scaleY = img.getHeight() * scaleFactor;
            Bitmap largeImg = Bitmap.createScaledBitmap(img, (int) scaleX,
                    (int) scaleY, false);
            x -= largeImg.getWidth()/2; y -= largeImg.getHeight()/2;
            mCanvas.drawBitmap(largeImg, x, y, mPaint);
            operationType = "";
            return;
        }

        if (operationType.equals("skew")) {
            // TODO : SKEW가 자꾸 Y축에 평행하게 밀리는거 수정
            Log.d("DEBUG", "MODE : " + operationType);
            Log.d("POINT", "X : " + x + " & Y : " + y);
            x -= img.getWidth()/2; y -= img.getHeight()/2;
            mCanvas.skew(0.2f, 0);
            mCanvas.drawBitmap(img, x - 250 * y / 1245, y, mPaint);
            mCanvas.restore();
            operationType = "";
            return;
        }

        operationType = "";

        x -= img.getWidth()/2; y -= img.getHeight()/2;
        Log.d("DEBUG", "X : " + x + " & Y : " + y);

        mCanvas.drawBitmap(img, x, y, mPaint);
    }

    int oldX = -1, oldY = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("DEBUG", isStamp ? "TRUE" : "FALSE");
        int x = (int) event.getX();
        int y = (int) event.getY();
        if (isStamp) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                drawStamp(x, y);
                invalidate();
            }
        } else {
            if (isBigWidth) {
                mPaint.setStrokeWidth(bigPenStrokeWidth);
            } else {
                mPaint.setStrokeWidth(defaultPenStrokeWidth);
            }
            if (penColor == 0) {
                mPaint.setColor(Color.RED);
            } else {
                mPaint.setColor(Color.BLUE);
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                oldX = x;
                oldY = y;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if (oldX != -1) {
                    mCanvas.drawLine(oldX, oldY, x, y, mPaint);
                    invalidate();
                    oldX = x;
                    oldY = y;
                }
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (oldX != -1) {
                    mCanvas.drawLine(oldX, oldY, x, y, mPaint);
                    invalidate();
                }
                oldX = -1;
                oldY = -1;
            }
        }
        return true;
    }

    public void setBluring(boolean bluring) {
        isBluring = bluring;
    }

    public void setColoring(boolean coloring) {
        isColoring = coloring;
    }

    public void setBigWidth(boolean bigWidth) {
        isBigWidth = bigWidth;
    }

    public void setColor(int color) {
        penColor = color;
    }

    public void setIsStamp(boolean isStamp) {
        this.isStamp = isStamp;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
        invalidate();
    }
}
