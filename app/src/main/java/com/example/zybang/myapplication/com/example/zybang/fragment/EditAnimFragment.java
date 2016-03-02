package com.example.zybang.myapplication.com.example.zybang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.zybang.myapplication.R;

public class EditAnimFragment extends Fragment {
    FrameLayout fragment_container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_anim, container, false);
        fragment_container = (FrameLayout) view.findViewById(R.id.fragment_container);
        fragment_container.addView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_myview, null));

        /*FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container,FragmentFactory.createFragment(2)).commit();*/

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
