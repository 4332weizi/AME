package io.auxo.ame;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    static {
        System.loadLibrary("ame");
    }

    public native String getLameVersion();
}
