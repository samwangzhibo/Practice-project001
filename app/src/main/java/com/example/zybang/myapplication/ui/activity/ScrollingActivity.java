package com.example.zybang.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.com.example.zybang.fragment.WebViewFragment;

public class ScrollingActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView view;
    private int startY = 0;
    //移动因子，是一个百分比，比如手指移动100px，View移动50px
    //目的是达到延迟效果
    private static float MOVE_FACTOR = 0.5f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        view = (TextView) findViewById(R.id.myHeaderView);
        Button checkJsBtn = (Button) findViewById(R.id.check_Js_btn);
        checkJsBtn.setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) event.getY();

                view.layout(0, startY, 0, 0);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getY();
                int deltaY = moveY - startY;

                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams2.topMargin = (int) (deltaY * MOVE_FACTOR);
                //view.setLayoutParams(layoutParams1);
                //view.requestLayout();

                break;
            case MotionEvent.ACTION_UP:
                //恢复状态
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.topMargin = 0;
                view.requestLayout();
                break;
        }
        return super.onTouchEvent(event);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, ScrollingActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_Js_btn:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, new WebViewFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            default:
                break;
        }
    }
}
