package com.example.zybang.myapplication.com.example.zybang.fragment;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zybang.myapplication.R;


public class RotateAnimFragment extends Fragment implements  View.OnClickListener{
    ImageView im;
    Button start_btn,rotate_end_btn,translate_btn,scale_start;

    RotateAnimation rotateAnimation;
    TranslateAnimation translateAnimation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rotate_anim, container, false);
        im = (ImageView) view.findViewById(R.id.rotate_im);

        start_btn = (Button) view.findViewById(R.id.rotate_start_btn);
        rotate_end_btn = (Button) view.findViewById(R.id.rotate_end_btn);
        translate_btn = (Button) view.findViewById(R.id.translate_btn);
        scale_start = (Button) view.findViewById(R.id.scale_start);

        start_btn.setOnClickListener(this);
        rotate_end_btn.setOnClickListener(this);
        translate_btn.setOnClickListener(this);
        scale_start.setOnClickListener(this);

        rotateAnimation = new RotateAnimation(-45,45,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,1.0f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(1);
        rotateAnimation.setFillAfter(true);


        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotate_start_btn:
                im.startAnimation(rotateAnimation);
                break;
            case R.id.rotate_end_btn:
                rotateAnimation.cancel();
                break;
            case R.id.translate_btn:

                translateAnimation  = new TranslateAnimation(0,10,0,0);
                translateAnimation.setFillAfter(true);
                translateAnimation.setRepeatCount(2);
                translateAnimation.setDuration(300);
                translateAnimation.setInterpolator(new CycleInterpolator(2f));

                im.startAnimation(translateAnimation);
                break;
            case R.id.scale_start:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,1.0f,0,1.0f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(2000);
                scaleAnimation.setFillAfter(true);

                im.startAnimation(scaleAnimation);
                break;
        }
    }
}
