package com.example.zybang.myapplication.com.example.zybang.com.example.zybang.v3;

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
public abstract class BaseView extends View {
    private MyThread thread;

    public BaseView(Context context) {
        super(context);
    }
    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract void logic();
    protected abstract void drawSub(Canvas canvas);

    protected final void onDraw(Canvas canvas) {
        if(thread == null){
            thread = new MyThread();
            thread.start();
        }else{
            drawSub(canvas);
        }
    }

    private  boolean running = true;

    @Override
    protected void onDetachedFromWindow() {
        running = false;
        super.onDetachedFromWindow();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            while(running){
                logic();
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
