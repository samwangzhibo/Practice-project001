package com.example.zybang.myapplication.com.example.zybang.v1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.zybang.myapplication.R;

/**
 * Created by zybang on 2016/2/25.
 */
public class myView extends View {
    private Bitmap bitmap;
    public myView(Context context){
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    }
    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    }

    public myView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(0xffff0000);
        canvas.drawText("this is wzb", 0, 30, paint);
        canvas.drawLine(0, 0, 90, 90, paint);
       // canvas.drawRect(0, 90, 100, 190, paint);

        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(0,90,100,190);
        //canvas.drawRect(rectF , paint);

        //绘制圆矩形
        canvas.drawRoundRect(rectF, 10, 10, paint);

        //绘制圆
        canvas.drawCircle(200,200,10,paint);

        canvas.drawBitmap(bitmap,0, 300 ,paint);
    }
}
