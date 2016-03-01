package com.example.zybang.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.zybang.myapplication.com.example.zybang.v1.myView;

public class MyviewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new myView(this));
        setContentView(R.layout.activity_myview);
    }
}
