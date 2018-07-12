package io.auxo.ame.options;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Input options for RAW PCM:<p>
 * -r              input is raw pcm<p>
 * -s sfreq        sampling frequency of input file (kHz) - default 44.1 kHz<p>
 * --signed        input is signed (default)<p>
 * --unsigned      input is unsigned<p>
 * --bitwidth w    input bit width is w (default 16)<p>
 * -x              force byte-swapping of input<p>
 * --little-endian input is little-endian (default)<p>
 * --big-endian    input is big-endian<p>
 * -a              downmix from stereo to mono file for mono encoding<p>
 *
 * @author Victor Chiu
 */

public class RawPcmInputOptions {

    @IntDef({LITTLE_ENDIAN, BIG_ENDIAN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Endian {
    }

    public static final int LITTLE_ENDIAN = 1;
    public static final int BIG_ENDIAN = 2;

    /**
     * 输入文件为原始PCM数据
     */
    private boolean rawPcm;
    /**
     * 采样率（单位Khz）(默认44.1Khz)
     */
    private float sampleRate;
    /**
     * 输入源数据为有符号(默认)或无符号
     */
    private boolean signed = true;
    /**
     * 输入源数据位宽（默认16）
     */
    private int bitWidth;
    /**
     * 是否强制进行字节交换
     */
    private boolean forceByteSwapping;
    /**
     * 输入源数据的字节存储机制
     *
     * @see #LITTLE_ENDIAN
     * @see #BIG_ENDIAN
     */
    private int endian;
    /**
     * 是否从立体声(stereo)下混音(downmix)到单声道(mono)以进行单声道编码
     */
    private boolean downmix;

    public RawPcmInputOptions rawPcm(boolean rawPcm) {
        this.rawPcm = rawPcm;
        return this;
    }

    public RawPcmInputOptions forceByteSwapping(boolean forceByteSwapping) {
        this.forceByteSwapping = forceByteSwapping;
        return this;
    }

    public RawPcmInputOptions sampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public RawPcmInputOptions bitWidth(int bitWidth) {
        this.bitWidth = bitWidth;
        return this;
    }

    public RawPcmInputOptions signed(boolean signed) {
        this.signed = signed;
        return this;
    }

    public RawPcmInputOptions endian(@Endian int endian) {
        this.endian = endian;
        return this;
    }

    public RawPcmInputOptions downmix(boolean downmix) {
        this.downmix = downmix;
        return this;
    }
}
