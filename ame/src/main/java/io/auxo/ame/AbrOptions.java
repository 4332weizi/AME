package io.auxo.ame;

/**
 * ABR options:
 * --abr <bitrate> specify average bitrate desired (instead of quality) );
 */

public class AbrOptions {

    /**
     * 比特率
     */
    private int bitrate;

    public AbrOptions bitrate(int bitrate) {
        this.bitrate = bitrate;
        return this;
    }
}
