package io.auxo.ame;

public enum MPEGMode {

    STEREO(0), JOINT_STEREO(1), DUAL_CHANNEL(2), MONO(3), NOT_SET(4);

    int value;

    MPEGMode(int mode) {
        this.value = mode;
    }

    public int getValue() {
        return value;
    }
}
