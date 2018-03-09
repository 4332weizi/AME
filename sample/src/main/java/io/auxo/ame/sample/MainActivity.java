package io.auxo.ame.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAmeLite;
    private Button mAme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAmeLite = findViewById(R.id.main_ame_lite);
        mAme = findViewById(R.id.main_ame);

        mAmeLite.setOnClickListener(this);
        mAme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_ame_lite:
                startActivity(new Intent(this, AmeLiteActivity.class));
                break;
            case R.id.main_ame:
                startActivity(new Intent(this, AmeActivity.class));
                break;
            default:
                break;
        }
    }
}
