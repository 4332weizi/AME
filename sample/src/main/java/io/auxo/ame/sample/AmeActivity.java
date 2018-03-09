package io.auxo.ame.sample;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.auxo.ame.Mp3Encoder;

public class AmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ame");
        actionBar.setSubtitle("LAME:" + new Mp3Encoder().getLameVersion());
    }
}
