package com.example.zybang.myapplication.com.example.zybang.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zybang on 2016/3/1.
 */
public class DrawingWithBezier extends View {

    private float x = 0;
    private float y = 0;
    private Paint paint = new Paint();
    private Path path = new Path();

    public DrawingWithBezier(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
    }

    public DrawingWithBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.lineTo(x, y);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //path.reset(); 开启的话，每次点下的时候清除上次绘画

                x = (int) event.getX();
                y = (int) event.getY();
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = (int) event.getX();
                float y = (int) event.getY();

                float previousX = this.x;
                float previousY = this.y;

                float dx = Math.abs(previousX - x);
                float dy = Math.abs(previousY - y);
                if (dx > 3 || dy > 3) {
                    //path.lineTo(x ,y);

                    float cX = (x + previousX) / 2;
                    float cY = (y + previousY) / 2;
                    path.quadTo(previousX, previousY, cX, cY);

                    this.x = x;
                    this.y = y;
                }
                break;
        }
        invalidate();
        return true;
    }
}
