package com.example.zybang.myapplication.com.example.zybang.customview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zybang on 2016/3/18.
 */
public class MyLineLayout extends LinearLayout {
    Context ctx;

    public MyLineLayout(Context context) {
        super(context);
        ctx = context;
    }

    public MyLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
    }

    public MyLineLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        ctx = context;
    }


    public void addALine(TextView tvL, TextView tvR) {
        RelativeLayout r = new RelativeLayout(ctx);
        r.setBackgroundColor(Color.parseColor("#FF0D41BC"));
        r.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 100;
        layoutParams.topMargin = 20;
        tvL.setLayoutParams(layoutParams);


        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.rightMargin = 100;
        tvR.setLayoutParams(layoutParams2);
        //无效
        tvR.setGravity(Gravity.TOP | Gravity.RIGHT);

        r.addView(tvL);
        r.addView(tvR);

        this.addView(r);
    }
}
