package io.auxo.ame.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
                Mp3Encoder.Options options = new Mp3Encoder.Options()
                        .bitRate(230)
                        .sampleRate(440)
                        .mode(Mp3Encoder.Options.DUAL_CHANNEL)
                        .numChannels(2)
                        .quality(9);
                Mp3Encoder.Callback callback = new Mp3Encoder.Callback() {
                    @Override
                    public void onStart() {
                        Log.i("AmeLiteActivity", "onStart");
                    }

                    @Override
                    public void onProgress(long total, long current) {
                        Log.i("AmeLiteActivity", "onProgress: tatal->" + total + " current->" + current);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("AmeLiteActivity", "onComplete");
                    }

                    @Override
                    public void onError() {
                        Log.i("AmeLiteActivity", "onError");
                    }
                };
                Mp3Encoder.encode(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.wav",
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.mp3", options, callback);
            }
        }).start();
    }
}
