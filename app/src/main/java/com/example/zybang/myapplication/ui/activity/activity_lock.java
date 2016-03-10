package com.example.zybang.myapplication.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.com.example.zybang.customview.MyUnlockView;

import java.util.List;

public class activity_lock extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        SharedPreferences sp = getSharedPreferences("password", this.MODE_PRIVATE);
        final String password = sp.getString("password", "");

        MyUnlockView lock = (MyUnlockView) findViewById(R.id.LockView);
        lock.setOnDrawFinishListener(new MyUnlockView.OnDrawFinishListener() {

            @Override
            public boolean OnDrawFinish(List<Integer> passList) {
                StringBuffer sb = new StringBuffer();
                for (Integer i : passList) {
                    sb.append(sb);
                }
                if (sb.toString().equals(password)) {
                    Toast.makeText(activity_lock.this, "解锁成功", Toast.LENGTH_SHORT).show();
                    return true;
                } else
                    return false;
            }
        });
    }

}
