package com.example.zybang.myapplication.com.example.zybang.fragment;



import android.support.v4.app.Fragment;

import com.example.zybang.myapplication.MainActivity;

/**
 * Created by zybang on 2016/2/29.
 */
public class FragmentFactory {
    public static Fragment createFragment(int position){
        Fragment f = null;
        switch (position){
            case 0:
                f =  MainActivity.PlaceholderFragment.newInstance(1);
                break;
            case 1:
                f = new RotateAnimFragment();
                break;
            case 2:
                f = new EditAnimFragment();
                break;
            case 3:
                f = new PropertyAnimFragment();
                break;
            case 4:
                f = new UnlockFragment();
                break;
        }
        return f;
    }
}
