package io.auxo.ame.options;

/**
 * -b <bitrate>    set the bitrate in kbps, default 128 kbps
 * --cbr           enforce use of constant bitrate
 *
 * @author Victor Chiu
 */
public class CbrOptions {

    /**
     * 比特率，单位kbps，默认128kbps
     */
    private int bitrate = -1;
    /**
     * 是否严格使用CBR编码
     */
    private boolean enforce = false;

    public CbrOptions bitrate(int bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    public CbrOptions enforce() {
        this.enforce = true;
        return this;
    }
}
