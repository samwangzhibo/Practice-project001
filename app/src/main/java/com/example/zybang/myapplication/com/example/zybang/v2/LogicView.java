package com.example.zybang.myapplication.com.example.zybang.v2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by zybang on 2016/2/26.
 */
public class LogicView extends View {

    public LogicView(Context context) {
        super(context);
    }

    public LogicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogicView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private float rx = 0;
    private MyThread thread;
    Paint paint = new Paint();
    RectF rectF = new RectF(0,60,100,160);
    float sweepAngle = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        paint.setTextSize(30);
        canvas.drawText("LogicView", rx, 30, paint);

        canvas.drawArc(rectF,0,sweepAngle,true,paint);
        if(thread == null){
            thread = new MyThread();
            thread.start();
        }
    //     rx = rx + 100;
   //    canvas.drawText("LogicView", rx, 30, paint);
    }
    class MyThread extends Thread{
        Random random = new Random();
        @Override
        public void run() {
            while(true){

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

                postInvalidate();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
