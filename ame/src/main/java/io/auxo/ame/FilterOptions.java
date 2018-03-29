package io.auxo.ame;

/**
 * Filter options:
 * --lowpass <freq>        frequency(kHz), lowpass filter cutoff above freq
 * --lowpass-width <freq>  frequency(kHz) - default 15% of lowpass freq
 * --highpass <freq>       frequency(kHz), highpass filter cutoff below freq
 * --highpass-width <freq> frequency(kHz) - default 15% of highpass freq
 * --resample <sfreq>  sampling frequency of output file(kHz)- default=automatic
 */

public class FilterOptions {

    private double lowPass = -1;
    private double lowPassWidth = -1;
    private double highPass = -1;
    private double highPassWidth = -1;
    private double reSample = -1;

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

    public FilterOptions reSample(double reSample) {
        this.reSample = reSample;
        return this;
    }
}
