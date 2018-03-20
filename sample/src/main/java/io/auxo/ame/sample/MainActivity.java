package io.auxo.ame.sample;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected final int REQUEST_PERMISSIONS = 1;

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

        requestPermissions();
    }

    @AfterPermissionGranted(REQUEST_PERMISSIONS)
    protected void requestPermissions() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            mAme.setEnabled(true);
            mAmeLite.setEnabled(true);
        } else {
            EasyPermissions.requestPermissions(this, "Mp3 Encoder需要读写权限",
                    REQUEST_PERMISSIONS, perms);
            mAme.setEnabled(false);
            mAmeLite.setEnabled(false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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
