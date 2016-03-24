package com.example.zybang.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.zybang.myapplication.R;

public class ScrollingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

    }


    public static Intent createIntent(Context context) {
        return new Intent(context, ScrollingActivity.class);
    }

}
