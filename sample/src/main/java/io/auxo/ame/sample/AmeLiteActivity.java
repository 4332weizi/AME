package io.auxo.ame.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.warkiz.widget.IndicatorSeekBar;

import java.io.File;

import io.auxo.ame.lite.Mp3Encoder;

public class AmeLiteActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener {

    private final String TAG = "AmeLiteActivity";

    private LinearLayout mBitrateGroup;
    private LinearLayout mQualityGroup;

    private RadioGroup mTarget;
    private RadioGroup mEngineQuality;
    private RadioGroup mVbrMode;

    private CheckBox mMonoEncoding;
    private CheckBox mForceCbr;

    private IndicatorSeekBar mBitrate;
    private IndicatorSeekBar mVbrQuality;

    private Button mEncode;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ame_lite);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ame-Lite");
        actionBar.setSubtitle("LAME:" + Mp3Encoder.getLameVersion());

        mBitrateGroup = findViewById(R.id.ame_lite_bitrate_group);
        mQualityGroup = findViewById(R.id.ame_lite_vbr_quality_group);

        mTarget = findViewById(R.id.ame_lite_target);
        mEngineQuality = findViewById(R.id.ame_lite_engine_quality);
        mVbrMode = findViewById(R.id.ame_lite_vbr_mode);

        mMonoEncoding = findViewById(R.id.ame_lite_mono);
        mForceCbr = findViewById(R.id.ame_lite_force_cbr);

        mBitrate = findViewById(R.id.ame_lite_bitrate);
        mVbrQuality = findViewById(R.id.ame_lite_vbr_quality);

        mEncode = findViewById(R.id.ame_lite_encode);

        mProgressBar = findViewById(R.id.ame_lite_encode_progress_bar);

        mTarget.setOnCheckedChangeListener(this);
        mEngineQuality.setOnCheckedChangeListener(this);
        mVbrMode.setOnCheckedChangeListener(this);

        mEncode.setOnClickListener(this);

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String file = FileChooser.onActivityResult(this, requestCode, resultCode, data);
        if (file != null) {
            decodeFile(file);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.ame_lite_target_bitrate:
                mBitrateGroup.setVisibility(View.VISIBLE);
                mQualityGroup.setVisibility(View.GONE);
                break;
            case R.id.ame_lite_target_quality:
                mBitrateGroup.setVisibility(View.GONE);
                mQualityGroup.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ame_lite_encode:
                FileChooser.choose(this, FileChooser.TYPE_AUDIO);
                break;
            default:
                break;
        }
    }

    protected void decodeFile(final String file) {
        File fin = new File(file);
        if (fin.exists() && !fin.isDirectory()) {
            final String out = file.substring(0, file.lastIndexOf(".")) + ".mp3";
            new EncodeThread(file, out).start();
        }
    }

    protected Mp3Encoder.Options getOptions() {
        Mp3Encoder.Options options = new Mp3Encoder.Options();
        switch (mEngineQuality.getCheckedRadioButtonId()) {
            case R.id.ame_lite_engine_quality_fast:
                options.quality(9);
                break;
            case R.id.ame_lite_engine_quality_standard:
                options.quality(5);
                break;
            case R.id.ame_lite_engine_quality_high:
                options.quality(0);
                break;
            default:
                break;
        }
        if (mMonoEncoding.isChecked()) {
            options.mode(Mp3Encoder.Options.MONO);
            options.numChannels(1);
        }
        if (mTarget.getCheckedRadioButtonId() == R.id.ame_lite_target_bitrate) {
            options.bitrate(mBitrate.getProgress());
            if (mForceCbr.isChecked()) {
                options.vbrMode(Mp3Encoder.Options.VBR_OFF);
            }
        } else {
            options.vbrQuality(mVbrQuality.getProgress());
            if (mVbrMode.getCheckedRadioButtonId() == R.id.ame_lite_vbr_standard) {
                options.vbrQuality(0);
            } else if (mVbrMode.getCheckedRadioButtonId() == R.id.ame_lite_vbr_fast) {
                options.vbrQuality(9);
            }
        }
        return options;
    }

    protected final int MESSAGE_START = 0;
    protected final int MESSAGE_PROGRESS = 1;
    protected final int MESSAGE_COMPLETE = 2;
    protected final int MESSAGE_ERROR = 3;

    protected Handler mEncodeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_START:
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(0);
                    mEncode.setEnabled(false);
                    break;
                case MESSAGE_PROGRESS:
                    mProgressBar.setMax(msg.arg1);
                    mProgressBar.setProgress(msg.arg2);
                    mEncode.setText(msg.arg2 / 1000000f + "M/" + msg.arg1 / 1000000f + "M");
                    break;
                case MESSAGE_COMPLETE:
                    Toast.makeText(getApplicationContext(), "转码完成", Toast.LENGTH_LONG).show();
                case MESSAGE_ERROR:
                    mProgressBar.setVisibility(View.GONE);
                    mEncode.setEnabled(true);
                    mEncode.setText("encode file");
                    break;
                default:
                    break;
            }
        }
    };

    protected class EncodeThread extends Thread implements Mp3Encoder.Callback {

        private String input;
        private String output;

        public EncodeThread(String input, String output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public void run() {
            super.run();
            Mp3Encoder.encode(input, output, getOptions(), this);
        }

        @Override
        public void onStart() {
            Log.i(TAG, "onStart");
            mEncodeHandler.sendEmptyMessage(MESSAGE_START);
        }

        @Override
        public void onProgress(final int total, final int current) {
            Log.i(TAG, "onProgress total -> " + total + " current -> " + current);
            Message msg = mEncodeHandler.obtainMessage(MESSAGE_PROGRESS);
            msg.arg1 = total;
            msg.arg2 = current;
            mEncodeHandler.sendMessage(msg);
        }

        @Override
        public void onComplete() {
            Log.i(TAG, "onComplete");
            mEncodeHandler.sendEmptyMessage(MESSAGE_COMPLETE);
        }

        @Override
        public void onError() {
            Log.e(TAG, "onError");
            mEncodeHandler.sendEmptyMessage(MESSAGE_ERROR);
        }
    }

}
