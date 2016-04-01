package com.example.zybang.myapplication.com.example.zybang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.com.example.zybang.customview.MyLineLayout;
import com.example.zybang.myapplication.ui.activity.BackActivity2;
import com.example.zybang.myapplication.ui.activity.backlayoutActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClickFragment extends Fragment {
    @Bind(R.id.goto_backlayout2)
    Button sadGotoBacklayout2;
    private MyLineLayout mll;

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
        mll = (MyLineLayout) v.findViewById(R.id.myLinearLayout);
        Button clickAddLine = (Button) v.findViewById(R.id.click_add_line);
        clickAddLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView a = new TextView(getActivity());
                a.setText("sadsaf");

                TextView b = new TextView(getActivity());
                b.setText("lkllkj");
                mll.addALine(a, b);
            }
        });
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.goto_backlayout2)
    public void onClick() {
        startActivity(BackActivity2.createIntent(getActivity()));
        getActivity().overridePendingTransition(R.anim.in_from_top, R.anim.keep);
    }
}
