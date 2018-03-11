package io.auxo.ame.lite;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    public static class Options {

        @IntDef({STEREO, JOINT_STEREO, DUAL_CHANNEL, MONO, NOT_SET})
        @Retention(RetentionPolicy.SOURCE)
        public @interface MPEGMode {
        }

        public static final int STEREO = 0;
        public static final int JOINT_STEREO = 1;
        public static final int DUAL_CHANNEL = 2;
        public static final int MONO = 3;
        public static final int NOT_SET = 4;

        private int sampleRate;
        private int bitRate;
        private int quality;
        private int numChannels;
        private int mode;

        public int getSampleRate() {
            return sampleRate;
        }

        public Options sampleRate(int sampleRate) {
            this.sampleRate = sampleRate;
            return this;
        }

        public int getBitRate() {
            return bitRate;
        }

        public Options bitRate(int bitRate) {
            this.bitRate = bitRate;
            return this;
        }

        public int getQuality() {
            return quality;
        }

        public Options quality(int quality) {
            this.quality = quality;
            return this;
        }

        public int getNumChannels() {
            return numChannels;
        }

        public Options numChannels(int numChannels) {
            this.numChannels = numChannels;
            return this;
        }

        public int getMode() {
            return mode;
        }

        public Options mode(@MPEGMode int mode) {
            this.mode = mode;
            return this;
        }
    }

    public interface Callback {
        void onStart();

        void onProgress(long tatal, long current);

        void onComplete();

        void onError();
    }

    static {
        System.loadLibrary("ame-lite");
    }

    public static void encode(String in, String out) {
        encode(in, out, null, null);
    }

    public static void encode(String in, String out, Options options) {
        encode(in, out, options, null);
    }

    public static void encode(String in, String out, Callback callback) {
        encode(in, out, null, callback);
    }

    public static native void encode(String in, String out, Options options, Callback callback);

    /**
     * 获取LAME版本
     *
     * @return LAME版本号
     */
    public static native String getLameVersion();
}
