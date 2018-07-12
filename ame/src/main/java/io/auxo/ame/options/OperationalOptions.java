package io.auxo.ame.options;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Operational options:<p>
 * -m <mode>       (j)oint, (s)imple, (f)orce, (d)ual-mono, (m)ono (l)eft (r)ight
 * default is (j)<p>
 * joint  = Uses the best possible of MS and LR stereo<p>
 * simple = force LR stereo on all frames<p>
 * force  = force MS stereo on all frames.<p>
 * --preset type   type must be "medium", "standard", "extreme", "insane",<p>
 * or a value for an average desired bitrate and depending
 * on the value specified, appropriate quality settings will
 * be used.
 * "--preset help" gives more info on these<p>
 * --comp  <arg>   choose bitrate to achieve a compression ratio of <arg><p>
 * --replaygain-fast   compute RG fast but slightly inaccurately (default)<p>
 * --replaygain-accurate   compute RG more accurately and find the peak sample<p>
 * --noreplaygain  disable ReplayGain analysis<p>
 * --clipdetect    enable --replaygain-accurate and print a message whether
 * clipping occurs and how far the waveform is from full scale<p>
 * --flush         flush output stream as soon as possible<p>
 * --freeformat    produce a free format bitstream<p>
 * --decode        input=mp3 file, output=wav<p>
 * -t              disable writing wav header when using --decode<p>
 *
 * @author Victor Chiu
 */

public class OperationalOptions {

    @IntDef({MODE_JOINT, MODE_SIMPLE, MODE_FORCE, MODE_DUAL_MONO,
            MODE_MONO, MODE_LEFT, MODE_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public static final int MODE_JOINT = 1;
    public static final int MODE_SIMPLE = 2;
    public static final int MODE_FORCE = 3;
    public static final int MODE_DUAL_MONO = 4;
    public static final int MODE_MONO = 5;
    public static final int MODE_LEFT = 6;
    public static final int MODE_RIGHT = 7;

    @IntDef({TYPE_MEDIUM, TYPE_STANDARD, TYPE_EXTREME, TYPE_INSANE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    public static final int TYPE_MEDIUM = 1;
    public static final int TYPE_STANDARD = 2;
    public static final int TYPE_EXTREME = 3;
    public static final int TYPE_INSANE = 4;

    @IntDef({REPLAY_GAIN_FAST, REPLAY_GAIN_ACCURATE, REPLAY_GAIN_NO})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReplayGain {
    }

    public static final int REPLAY_GAIN_FAST = 1;
    public static final int REPLAY_GAIN_ACCURATE = 2;
    public static final int REPLAY_GAIN_NO = 3;

    /**
     * 编码模式
     *
     * @see #MODE_JOINT
     * @see #MODE_SIMPLE
     * @see #MODE_FORCE
     * @see #MODE_DUAL_MONO
     * @see #MODE_MONO
     * @see #MODE_LEFT
     * @see #MODE_RIGHT
     */
    private int mode = -1;
    /**
     * 使用预设
     *
     * @see #TYPE_MEDIUM
     * @see #TYPE_STANDARD
     * @see #TYPE_EXTREME
     * @see #TYPE_INSANE
     */
    private int preset;
    /**
     * 压缩率
     */
    private float ratio;
    /**
     * 回放增益
     *
     * @see #REPLAY_GAIN_FAST
     * @see #REPLAY_GAIN_ACCURATE
     * @see #REPLAY_GAIN_NO
     */
    private int replayGain;
    /**
     * 尽快清空输出流
     */
    private boolean flush;
    /**
     * 生成自由格式的比特流(bitstream)，而不是标准格式，
     * 支持8到640kbps之间的比特率。但解码器提供的支持不足。
     */
    private boolean freeFormat;
    /**
     * 输入为mp3文件，输出wav文件。
     */
    private boolean decode;
    /**
     * 使用decode时禁止写入wav header
     */
    private boolean writingWavHeader;


    public OperationalOptions mode(@Mode int mode) {
        this.mode = mode;
        return this;
    }

    public OperationalOptions preset(@Type int type) {
        this.preset = type;
        return this;
    }

    public OperationalOptions ratio(int ratio) {
        this.ratio = ratio;
        return this;
    }

    public OperationalOptions replayGain(@ReplayGain int replayGain) {
        this.replayGain = replayGain;
        return this;
    }

    public OperationalOptions flush(boolean flush) {
        this.flush = flush;
        return this;
    }

    public OperationalOptions freeFormat(boolean freeFormat) {
        this.freeFormat = freeFormat;
        return this;
    }

    public OperationalOptions decode(boolean decode) {
        this.decode = decode;
        return this;
    }

    public OperationalOptions writingWavHeader(boolean writingWavHeader) {
        this.writingWavHeader = writingWavHeader;
        return this;
    }
}
