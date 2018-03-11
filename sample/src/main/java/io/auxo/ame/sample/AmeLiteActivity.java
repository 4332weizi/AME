package io.auxo.ame.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import io.auxo.ame.lite.Mp3Encoder;

public class AmeLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame_lite);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ame-Lite");
        actionBar.setSubtitle("LAME:" + Mp3Encoder.getLameVersion());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Mp3Encoder.encode(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.wav",
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.mp3");
            }
        }).start();
    }
}
