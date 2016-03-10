package com.example.zybang.myapplication.com.example.zybang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.ui.activity.activity_lock;


public class UnlockFragment extends Fragment implements View.OnClickListener {
    Button set_pass, test_pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_unlock, container, false);
        set_pass = (Button) v.findViewById(R.id.set_pass);
        test_pass = (Button) v.findViewById(R.id.test_pass);
        set_pass.setOnClickListener(this);
        test_pass.setOnClickListener(this);
        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_pass:
                break;
            case R.id.test_pass:
                Intent it2 = new Intent(getActivity(), activity_lock.class);
                startActivity(it2);
                break;
            default:
                break;
        }
    }
}
