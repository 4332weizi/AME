package io.auxo.ame.lite;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    public static class Options {
        private int sampleRate;
        private int bitRate;
        private int quality;
        private int numChannels;
    }

    public interface Callback {
        void onStart();
        void onProgress(int progress);
        void onComplete();
        void onError();
    }

    static {
        System.loadLibrary("ame-lite");
    }

    public static native void encode(String in, String out);

    public static native void encode(String in, String out, Callback callback);

    public static native void encode(String in, String out, Options options);

    public static native void encode(String in, String out, Options options, Callback callback);

    /**
     * 获取LAME版本
     *
     * @return LAME版本号
     */
    public static native String getLameVersion();
}
