package io.auxo.ame.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.auxo.ame.Mp3Encoder;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.main_hello);

        String version = new Mp3Encoder().getLameVersion();

        mTextView.setText("LAME version : " + version);
    }
}
