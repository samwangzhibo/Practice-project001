package com.example.zybang.myapplication.com.example.zybang.com.example.zybang.v3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by zybang on 2016/2/26.
 */
public class MyText extends  BaseView{
    private Paint paint = new Paint();
    private float rx = 0;
    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void logic() {
        rx += 3;
        if(rx > getWidth()){
            rx = - paint.measureText("MyText");
        }
    }

    @Override
    protected void drawSub(Canvas canvas) {
        paint.setTextSize(30);
    canvas.drawText("MyText",rx,30,paint);
    }
}
