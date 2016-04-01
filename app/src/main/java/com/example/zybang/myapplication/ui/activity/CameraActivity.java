package com.example.zybang.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.com.example.zybang.fragment.CustomCameraFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CameraActivity extends ActionBarActivity implements View.OnClickListener, CustomCameraFragment.OnFragmentInteractionListener {
    private static int REQUEST_CODE1 = 1;
    private static int REQUEST_CODE2 = 2;
    private String mFilePath;
    private ImageView im;
    private RelativeLayout contentContainer;
    private TextView customCameraTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Button startCamera = (Button) findViewById(R.id.start_camera1);
        startCamera.setOnClickListener(this);
        Button startCamera2 = (Button) findViewById(R.id.start_camera2);
        startCamera2.setOnClickListener(this);
        Button startCustomCamera = (Button) findViewById(R.id.start_CustomCameras);
        startCustomCamera.setOnClickListener(this);
        contentContainer = (RelativeLayout) findViewById(R.id.content_container);
        customCameraTv = (TextView) findViewById(R.id.custom_camera_tv);

        im = (ImageView) findViewById(R.id.iv);
        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath + "/" + "temp.png";
    }

    public static Intent createIntent(Context ctx) {
        return new Intent(ctx, CameraActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_camera1:
                startCamera1();
                break;
            case R.id.start_camera2:
                startCamera2();
                break;
            case R.id.start_CustomCameras:
                startCustomCamera();
                break;
            default:
                break;
        }
    }


    private void startCustomCamera() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            contentContainer.setVisibility(View.INVISIBLE);
            fragmentTransaction.add(R.id.fragment_container, CustomCameraFragment.newInstance());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void startCamera1() {
        customCameraTv.setVisibility(View.INVISIBLE);
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, REQUEST_CODE1);
    }

    private void startCamera2() {
        customCameraTv.setVisibility(View.INVISIBLE);
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        it.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(it, REQUEST_CODE2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE1) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                im.setImageBitmap(bitmap);
            } else if (requestCode == REQUEST_CODE2) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFilePath);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                    if (bitmap != null)
                        im.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(Bitmap bitmap) {
        contentContainer.setVisibility(View.VISIBLE);
        im.setImageBitmap(bitmap);
        customCameraTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
            contentContainer.setVisibility(View.VISIBLE);
            return;
        }
        finish();
    }
}
