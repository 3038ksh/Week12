//package com.example.kimsoohyeong.week12;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.BlurMaskFilter;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.ColorMatrix;
//import android.graphics.ColorMatrixColorFilter;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Toast;
//
///**
// * Created by KimSooHyeong on 2017. 5. 18..
// */
//
//public class MyCanvas extends View {
//    Rect rect;
//    String operationType = "";
//
//    public MyCanvas(Context context) {
//        super(context);
//        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
//    }
//
//    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        this.setLayerType(LAYER_TYPE_SOFTWARE, null);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
////
////        Paint paint = new Paint();
////        paint.setColor(Color.RED);
////
////        rect = new Rect(10, 10, 100, 100);
////        canvas.drawRect(rect, paint);
////
////        int width = canvas.getWidth()/2 - 45;
////        int height = canvas.getHeight()/2 - 45;
////        paint.setStyle(Paint.Style.STROKE);
////        paint.setColor(Color.YELLOW);
////        paint.setStrokeWidth(5);
////        canvas.drawRect(width, height, width + 90, height + 90, paint);
////
////        paint.setTextSize(70);
////        canvas.drawText("Click Me!!", 300, 300, paint);
////
////        Bitmap img = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
////        canvas.drawBitmap(img, 300, 350, paint);
////        canvas.drawBitmap(img, 400, 150, paint);
////
////        Bitmap smallimg = Bitmap.createScaledBitmap(img, img.getWidth()/2,
////                img.getHeight()/2, false);
////        canvas.drawBitmap(smallimg, 400, 350, paint);
////
////        img.recycle();
//
////        Paint paint = new Paint();
////        paint.setColor(Color.RED);
////        paint.setStyle(Paint.Style.STROKE);
////        paint.setStrokeWidth(5);
////        canvas.drawRect(startX, startY, stopX, stopY, paint);
//
//        Paint paint = new Paint();
//        Bitmap img = BitmapFactory.decodeResource(getResources(),
//                R.mipmap.ic_launcher);
//        Bitmap bigImg = Bitmap.createScaledBitmap(img,
//                img.getWidth()*2, img.getHeight()*2, false);
//
//        int cenX = (canvas.getWidth() - bigImg.getWidth())/2;
//        int cenY = (canvas.getHeight() - bigImg.getHeight())/2;
//
//        if (operationType.equals("rotate"))
//            canvas.rotate(angle += 45, this.getWidth()/2, getWidth()/2);
//
//        BlurMaskFilter blur = new BlurMaskFilter(100, BlurMaskFilter.Blur.INNER);
//        if (operationType.equals("blur"))
//            paint.setMaskFilter(blur);
//
//        float array[] = {
//                2f, 0, 0, 0, -25f,
//                0, 2f, 0, 0, -25f,
//                0, 0, 2f, 0, -25f,
//                0, 0, 0, 2f, 0
//        };
//
//        ColorMatrix colorMatrix = new ColorMatrix(array);
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
//        paint.setColorFilter(filter);
//
//        canvas.drawBitmap(bigImg, cenX, cenY, paint);
//    }
//
//    public void setOperationType(String operationType) {
//        this.operationType = operationType;
//        invalidate();
//    }
//
//    float angle = 0;
//    float startX = -1, startY = -1, stopX = -1, stopY = -1;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
////        int x = (int) event.getX();
////        int y = (int) event.getY();
//
////        if (rect.contains(x, y))
//
////        if (10 <= x && x <= 100 && 10 <= y && y <= 100) {
////            // RED RECT
////            Toast.makeText(getContext(), "RED BUTTON", Toast.LENGTH_SHORT).show();
////        } else {
////            Toast.makeText(getContext(), "BACKGROUND", Toast.LENGTH_SHORT).show();
////        }
//        if (event.getAction() == event.ACTION_DOWN) {
//            startX = event.getX(); startY = event.getY();
//        } else if (event.getAction() == event.ACTION_UP) {
//            stopX = event.getX(); stopY = event.getY();
//            invalidate();
//        }
//        return true;
//    }
//}
