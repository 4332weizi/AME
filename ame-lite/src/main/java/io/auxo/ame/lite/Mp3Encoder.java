package io.auxo.ame.lite;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    static {
        System.loadLibrary("ame-lite");
    }

    public Mp3Encoder() {
    }

    /**
     * 获取LAME版本
     *
     * @return LAME版本号
     */
    public native String getLameVersion();
}
