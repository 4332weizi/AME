package io.auxo.ame;

/**
 * ABR options:
 * --abr <bitrate> specify average bitrate desired (instead of quality) );
 *
 * @author Victor Chiu
 */
public class AbrOptions {

    /**
     * 比特率
     */
    private int bitrate;

    public AbrOptions(){
    }

    public AbrOptions(int bitrate){
        bitrate(bitrate);
    }

    public AbrOptions bitrate(int bitrate) {
        this.bitrate = bitrate;
        return this;
    }
}
