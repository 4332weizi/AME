package io.auxo.ame.sample;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.auxo.ame.Mp3Encoder;

public class AmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ame");
        actionBar.setSubtitle("LAME:" + new Mp3Encoder().getLameVersion());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Mp3Encoder.Callback callback = new Mp3Encoder.Callback() {
                    @Override
                    public void onStart() {
                        Log.i("AmeLiteActivity", "onStart");
                    }

                    @Override
                    public void onProgress(int total, int current) {
                        Log.i("AmeLiteActivity", "onProgress: total->" + total + " current->" + current);
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
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.mp3", callback);
            }
        }).start();
    }
}
