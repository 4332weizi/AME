package io.auxo.ame.lite;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;

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

        @IntDef({VBR_OFF, VBR_MT, VBR_RH, VBR_ABR, VBR_MTRH})
        @Retention(RetentionPolicy.SOURCE)
        public @interface VBRMode {
        }

        public static final int VBR_OFF = 0;
        public static final int VBR_MT = 1;
        public static final int VBR_RH = 2;
        public static final int VBR_ABR = 3;
        public static final int VBR_MTRH = 4;

        private int sampleRate = -1;
        private int bitrate = -1;
        private int numChannels = -1;
        private int mode = -1;
        /**
         * Internal algorithm selection.
         * True quality is determined by the bitrate but this variable will effect
         * quality by selecting expensive or cheap algorithms.
         * quality=0..9.
         * 0=best (very slow).
         * 9=worst.
         * recommended:  3     near-best quality, not too slow
         * 5     good quality, fast
         * 7     ok quality, really fast
         */
        private int quality = -1;
        private int vbrMode = -1;
        /**
         * 0 = highest
         * 9 = lowest
         */
        private int vbrQuality = -1;

        public Options sampleRate(int sampleRate) {
            this.sampleRate = sampleRate;
            return this;
        }

        public Options bitrate(int bitrate) {
            this.bitrate = bitrate;
            return this;
        }

        public Options numChannels(@IntRange(from = 1, to = 2) int numChannels) {
            this.numChannels = numChannels;
            return this;
        }

        public Options mode(@MPEGMode int mode) {
            this.mode = mode;
            return this;
        }

        public Options quality(@IntRange(from = 0, to = 9) int quality) {
            this.quality = quality;
            return this;
        }

        public Options vbrMode(@VBRMode int vbrMode) {
            this.vbrMode = vbrMode;
            return this;
        }

        public Options vbrQuality(@IntRange(from = 0, to = 9) int vbrQuality) {
            this.vbrQuality = vbrQuality;
            return this;
        }

        public int getSampleRate() {
            return sampleRate;
        }

        public int getBitrate() {
            return bitrate;
        }

        public int getNumChannels() {
            return numChannels;
        }

        public int getMode() {
            return mode;
        }

        public int getQuality() {
            return quality;
        }

        public int getVbrMode() {
            return vbrMode;
        }

        public int getVbrQuality() {
            return vbrQuality;
        }
    }

    public interface Callback {
        void onStart();

        void onProgress(int total, int current);

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
