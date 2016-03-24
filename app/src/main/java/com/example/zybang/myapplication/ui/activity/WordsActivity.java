package com.example.zybang.myapplication.ui.activity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zybang.myapplication.R;

import java.io.File;
import java.net.URL;

public class WordsActivity extends ActionBarActivity {
    private TextView words_tv;
    ImageView play_im;
    String words;
    URL url;
    MediaPlayer player;
    Thread mThread;
    Runnable a;
    boolean isFirst = true;
    File audio_file;
    String Cache_path;
    private AnimationDrawable frameAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Bundle bundle = getIntent().getExtras();
        words = (String) bundle.get("word");
        Cache_path = getCacheDir() + "";

        words_tv = (TextView) findViewById(R.id.words);
        play_im = (ImageView) findViewById(R.id.play_audio_im);

        words_tv.setText(words);
        play_im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirst) {
                    initMusic();
                }
                isFirst = false;
                play();

                frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.sear_word);
                play_im.setBackgroundDrawable(frameAnim);
                startAnim();

                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        endAnim();
                    }
                });
            }
        });

    }

    private void startAnim() {
        if (frameAnim != null && !frameAnim.isRunning()) {
            frameAnim.start();
        }
    }

    private void endAnim() {
        if (frameAnim != null && frameAnim.isRunning()) {
            frameAnim.stop();
            play_im.setBackgroundResource(R.drawable.search_word_audio_blue);
        }
    }

    private void play() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    void initMusic() {
        player = MediaPlayer.create(this, Uri.parse("http://www.w3school.com.cn/i/horse.mp3"));

    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
        if (mThread != null) {
            mThread.interrupt();
        }
        super.onDestroy();
    }
}
