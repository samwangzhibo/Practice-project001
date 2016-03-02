package com.example.zybang.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.zybang.myapplication.com.example.zybang.customview.MyUnlockView;

public class MyviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new myView(this));
        //setContentView(R.layout.activity_myview);
        //setContentView(new DrawingWithBezier(this));
        //setContentView(new MySurfaceView(this));
        setContentView(new MyUnlockView(this));
    }
}
