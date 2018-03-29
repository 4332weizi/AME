package io.auxo.ame;

import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * VBR options:<p>
 * -V n            quality setting for VBR.  default n=%i<p>
 * .               0=high quality,bigger files. 9=smaller files<p>
 * -v              the same as -V 4<p>
 * --vbr-old       use old variable bitrate (VBR) routine<p>
 * --vbr-new       use new variable bitrate (VBR) routine (default)<p>
 * -Y              lets LAME ignore noise in sfb21, like in CBR<p>
 * .               (Default for V3 to V9.999)<p>
 * -b <bitrate>    specify minimum allowed bitrate, default  32 kbps<p>
 * -B <bitrate>    specify maximum allowed bitrate, default 320 kbps<p>
 * -F              strictly enforce the -b option, for use with players that<p>
 * .               do not support low bitrate mp3<p>
 * -t              disable writing LAME Tag<p>
 * -T              enable and force writing LAME Tag);<p>
 *
 * @author Victor Chiu
 */
public class VbrOptions {

    @IntDef({VBR_OFF, VBR_MT, VBR_RH, VBR_ABR, VBR_MTRH, VBR_MAX_INDICATOR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface VbrMode {
    }

    public static final int VBR_OFF = 0;
    public static final int VBR_MT = 1;
    public static final int VBR_RH = 2;
    public static final int VBR_ABR = 3;
    public static final int VBR_MTRH = 4;
    public static final int VBR_MAX_INDICATOR = 5;

    /**
     * VBR的质量设置。默认quality=4.
     * 可以指定Decima类型的值, 如：4.51
     * 0=最高质量，生成的文件较大；
     * 9.999=最低质量，生成的文件较小。
     */
    private double quality = -1;
    /**
     * VBR模式
     */
    private int vbrMode = -1;
    private boolean ignoreNoise = false;
    /**
     * 可接受的最低比特率，默认32kbps
     */
    private int minBitrate = -1;
    /**
     * 可接受的最高比特率，默认320kbps
     */
    private int maxBitrate = -1;
    /**
     * 严格限制最低比特率
     */
    private boolean strictlyEnforceMinBitrate = false;
    /**
     * 写入LAME标签
     */
    private boolean writingLameTag = false;

    public VbrOptions quality(@FloatRange(from = 0, to = 10, toInclusive = false) float quality) {
        this.quality = quality;
        return this;
    }

    public VbrOptions mode(@VbrMode int mode) {
        this.vbrMode = mode;
        return this;
    }

    public VbrOptions ignoreNoise() {
        this.ignoreNoise = true;
        return this;
    }

    public VbrOptions minBitrate(int min) {
        this.minBitrate = min;
        return this;
    }

    public VbrOptions maxBitrate(int max) {
        this.maxBitrate = max;
        return this;
    }

    public VbrOptions strictlyEnforceMinBitrate() {
        this.strictlyEnforceMinBitrate = true;
        return this;
    }

    public VbrOptions writingLameTag() {
        this.writingLameTag = true;
        return this;
    }
}
