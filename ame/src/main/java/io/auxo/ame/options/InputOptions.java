package io.auxo.ame.options;

import android.support.annotation.FloatRange;

/**
 * Input options:<p>
 * --scale <arg>   scale input (multiply PCM data) by <arg><p>
 * --scale-l <arg> scale channel 0 (left) input (multiply PCM data) by <arg><p>
 * --scale-r <arg> scale channel 1 (right) input (multiply PCM data) by <arg><p>
 * --swap-channel  swap L/R channels<p>
 * --ignorelength  ignore file length in WAV header<p>
 * --gain <arg>    apply Gain adjustment in decibels, range -20.0 to +12.0<p>
 * --mp1input      input file is a MPEG Layer I   file<p>
 * --mp2input      input file is a MPEG Layer II  file<p>
 * --mp3input      input file is a MPEG Layer III file<p>
 * --nogap <file1> <file2> <...><p>
 * gapless encoding for a set of contiguous files<p>
 * --nogapout <dir><p>
 * output dir for gapless encoding (must precede --nogap)<p>
 * --nogaptags     allow the use of VBR tags in gapless encoding<p>
 * --out-dir <dir> output dir, must exist<p>
 *
 * @author Victor Chiu
 */

public class InputOptions {

    /**
     * 按scale缩放输入数据
     */
    private float scale = -1;
    /**
     * 按scaleLeft缩放channel 0 (左声道)数据
     */
    private float scaleLeft = -1;
    /**
     * 按scaleRight缩放channel 1 (右声道)数据
     */
    private float scaleRight = -1;
    /**
     * 在[-20.0,+12.0]范围内以分贝为单位进行增益调整
     */
    private float gain = 0;
    /**
     * 交换左右声道
     */
    private boolean swapChannel = false;
    /**
     * 忽略WAV文件头中的文件长度
     */
    private boolean ignoreLength = false;

    public InputOptions scale(float scale) {
        this.scale = scale;
        return this;
    }

    public InputOptions scaleLeft(float scaleLeft) {
        this.scaleLeft = scaleLeft;
        return this;
    }

    public InputOptions scaleRight(float scaleRight) {
        this.scaleRight = scaleRight;
        return this;
    }

    public InputOptions gain(@FloatRange(from = -20, to = 12) float gain) {
        this.gain = gain;
        return this;
    }

    public InputOptions swapChannel(boolean swapChannel) {
        this.swapChannel = swapChannel;
        return this;
    }

    public InputOptions ignoreLength(boolean ignoreLength) {
        this.ignoreLength = ignoreLength;
        return this;
    }

}
