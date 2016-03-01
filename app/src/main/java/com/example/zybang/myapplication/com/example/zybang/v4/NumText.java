package com.example.zybang.myapplication.com.example.zybang.v4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.com.example.zybang.com.example.zybang.v3.BaseView;

/**
 * Created by zybang on 2016/2/26.
 */
public class NumText extends BaseView{
    Paint paint = new Paint();
    private int Num = 0;
    private int mx = 0;
    private boolean xScroll = false;
    public NumText(Context context) {
        super(context);

    }

    public NumText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumText);
        Num = ta.getInt(R.styleable.NumText_Num, 1);
        xScroll = ta.getBoolean(R.styleable.NumText_xScroll,false);
        ta.recycle();
    }

    @Override
    protected void logic() {
        if(xScroll){
            mx += 3;
            if(mx > getWidth()){
                mx = (int) -paint.measureText("jay周");
            }
        }

    }

    @Override
    protected void drawSub(Canvas canvas) {

        for (int i=0; i < Num; i++){
            int textSize = 30 + i;
            paint.setTextSize(textSize);
            canvas.drawText("jay周",mx , textSize + textSize * i ,paint);
        }

    }
}
