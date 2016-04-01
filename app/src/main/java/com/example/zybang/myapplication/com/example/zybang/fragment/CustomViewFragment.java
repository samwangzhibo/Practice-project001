package com.example.zybang.myapplication.com.example.zybang.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.ui.activity.BezierActivity;
import com.example.zybang.myapplication.ui.activity.CameraActivity;
import com.example.zybang.myapplication.ui.activity.EmojiActivity;
import com.example.zybang.myapplication.ui.activity.ScrollingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CustomViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomViewFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.goto_emoji)
    Button sadGotoEmoji;
    private OnFragmentInteractionListener mListener;

    public CustomViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomViewFragment.
     */
    public static CustomViewFragment newInstance(String param1, String param2) {
        CustomViewFragment fragment = new CustomViewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_custom_view, container, false);
        Button customScrollview1 = (Button) v.findViewById(R.id.custom_scrollview1);
        customScrollview1.setOnClickListener(this);
        Button customScrollView2 = (Button) v.findViewById(R.id.custom_scrollview2);
        customScrollView2.setOnClickListener(this);
        Button customScrollView3 = (Button) v.findViewById(R.id.custom_scrollview3);
        customScrollView3.setOnClickListener(this);
        Button customScrollView4 = (Button) v.findViewById(R.id.custom_scrollview4);
        customScrollView4.setOnClickListener(this);
        Button customScrollView5 = (Button) v.findViewById(R.id.goto_recyclerView);
        customScrollView5.setOnClickListener(this);

        ButterKnife.bind(this, v);
        return v;
    }

    public void onButtonPressed(Uri uri) {
        Toast.makeText(getActivity(), "back click", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_scrollview1:
                Intent it = ScrollingActivity.createIntent(getActivity());
                it.putExtra("a", "a");
                startActivity(it);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.custom_scrollview2:

                break;
            case R.id.custom_scrollview3:
                startActivity(BezierActivity.creatIntent(getActivity()));
                break;
            case R.id.custom_scrollview4:
                startActivity(CameraActivity.createIntent(getActivity()));
                break;
            case R.id.goto_recyclerView:
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.goto_emoji)
    public void onClick() {
        startActivity(new Intent(getActivity(), EmojiActivity.class));
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
