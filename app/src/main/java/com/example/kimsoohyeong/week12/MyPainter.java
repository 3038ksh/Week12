//package com.example.kimsoohyeong.week12;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//
///**
// * Created by KimSooHyeong on 2017. 5. 18..
// */
//
//public class MyPainter extends View {
//    Bitmap mBitMap;
//    Canvas mCanvas;
//    Paint mPaint = new Paint();
//
//    public MyPainter(Context context) {
//        super(context);
//        mPaint.setColor(Color.BLACK);
//    }
//
//    public MyPainter(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        mPaint.setColor(Color.BLACK);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        mBitMap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//
//        mCanvas = new Canvas();
//        mCanvas.setBitmap(mBitMap);
//        mCanvas.drawColor(Color.YELLOW);
//
//        drawStamp();
//    }
//
//    private void drawStamp() {
//        Bitmap img = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.ic_launcher);
//        mCanvas.drawBitmap(img, 10, 10, mPaint);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        if (mBitMap != null)
//            canvas.drawBitmap(mBitMap, 0, 0, null);
//    }
//
//    int oldX = -1, oldY = -1;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int x = (int) event.getX();
//        int y = (int) event.getY();
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            oldX = x; oldY = y;
//        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
//            if (oldX != -1) {
//                mCanvas.drawLine(oldX, oldY, x, y, mPaint);
//                invalidate();
//                oldX = x; oldY = y;
//            }
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (oldX != -1) {
//                mCanvas.drawLine(oldX, oldY, x, y, mPaint);
//                invalidate();
//            }
//            oldX = -1; oldY = -1;
//        }
//        return true;
//    }
//}
