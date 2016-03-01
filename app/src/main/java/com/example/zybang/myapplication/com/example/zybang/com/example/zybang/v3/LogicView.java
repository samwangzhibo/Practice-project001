package com.example.zybang.myapplication.com.example.zybang.com.example.zybang.v3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import java.util.Random;

/**
 * Created by zybang on 2016/2/26.
 */
public class LogicView extends  BaseView {

    Paint paint = new Paint();
    RectF rectF = new RectF(0,60,100,160);
    float sweepAngle = 0;
    private float rx = 0;

    public LogicView(Context context) {
        super(context);
    }

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void drawSub(Canvas canvas) {
        paint.setTextSize(30);
        canvas.drawText("LogicView", rx, 30, paint);
        canvas.drawArc(rectF,0,sweepAngle,true,paint);
    }

    @Override
    protected void logic() {
        Random random = new Random();
        rx += 3;
        if(rx > getWidth()){
            rx = 0 - paint.measureText("LogicView");
        }

        sweepAngle++;
        if(sweepAngle >= 360){
            sweepAngle = 0;
        }
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b=  random.nextInt(256);

        paint.setARGB(255,r,g,b);
    }
}
