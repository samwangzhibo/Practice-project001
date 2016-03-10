package com.example.zybang.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.zybang.myapplication.R;

public class backlayoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlayout);
    }

    public static Intent createIntent(Context context) {
        Intent it = new Intent(context, backlayoutActivity.class);
        return it;
    }

}
