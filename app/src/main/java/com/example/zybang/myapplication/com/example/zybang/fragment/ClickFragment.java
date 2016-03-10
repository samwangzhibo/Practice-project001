package com.example.zybang.myapplication.com.example.zybang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.ui.activity.backlayoutActivity;


public class ClickFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_click, container, false);
        Button goto_backlayout = (Button) v.findViewById(R.id.goto_backlayout);
        goto_backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(backlayoutActivity.createIntent(getActivity()));
            }
        });
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
