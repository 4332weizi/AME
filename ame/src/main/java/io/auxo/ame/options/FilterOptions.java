package io.auxo.ame.options;

/**
 * Filter options:
 * --lowpass <freq>        frequency(kHz), lowpass filter cutoff above freq
 * --lowpass-width <freq>  frequency(kHz) - default 15% of lowpass freq
 * --highpass <freq>       frequency(kHz), highpass filter cutoff below freq
 * --highpass-width <freq> frequency(kHz) - default 15% of highpass freq
 * --resample <sfreq>  sampling frequency of output file(kHz)- default=automatic
 *
 * @author Victor Chiu
 */
public class FilterOptions {

    /**
     * 低通滤波器，过滤掉频率lowPass以上的数据。
     * 范围为[0.001..50]kHz或[50..50000]Hz。
     */
    private double lowPass = -1;
    /**
     * 低通曲线宽度。范围为[0.001..16]kHz或 [16..50000]Hz。
     */
    private double lowPassWidth = -1;
    /**
     * 高通滤波器，过滤掉频率freq以下的数据。
     * 范围为[0.001..16]kHz或[16..50000]Hz。
     */
    private double highPass = -1;
    /**
     * 高通曲线宽度。
     */
    private double highPassWidth = -1;
    /**
     * 输出文件的采样率。
     */
    private double sampleRate = -1;

    public FilterOptions lowPass(double lowPass) {
        this.lowPass = lowPass;
        return this;
    }

    public FilterOptions lowPassWidth(double lowPassWidth) {
        this.lowPassWidth = lowPassWidth;
        return this;
    }

    public FilterOptions highPass(double highPass) {
        this.highPass = highPass;
        return this;
    }

    public FilterOptions highPassWidth(double highPassWidth) {
        this.highPassWidth = highPassWidth;
        return this;
    }

    public FilterOptions sampleRate(double sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }
}
