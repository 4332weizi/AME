package io.auxo.ame.sample;

import android.os.Bundle;
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
        actionBar.setSubtitle("LAME:" + new Mp3Encoder().getLameVersion());
    }
}
