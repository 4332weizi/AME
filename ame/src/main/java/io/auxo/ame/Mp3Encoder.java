package io.auxo.ame;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    public static class Options {

        public InputOptions inputOptions;
        public RawPcmInputOptions rawPcmInputOptions;
        public OperationalOptions operationalOptions;
        public CbrOptions cbrOptions;
        public AbrOptions abrOptions;
        public VbrOptions vbrOptions;
        public Mp3HeaderStreamOptions mp3HeaderStreamOptions;
        public FilterOptions filterOptions;
        public Id3TagOptions id3TagOptions;

        protected Options() {

        }

        public Options addInputOptions(InputOptions inputOptions) {
            this.inputOptions = inputOptions;
            return this;
        }

        public Options addRawPcmInputOptions(RawPcmInputOptions rawPcmInputOptions) {
            this.rawPcmInputOptions = rawPcmInputOptions;
            return this;
        }

        public Options addOperationalOptions(OperationalOptions operationalOptions) {
            this.operationalOptions = operationalOptions;
            return this;
        }

        public Options addCBROptions(CbrOptions cbrOptions) {
            this.cbrOptions = cbrOptions;
            return this;
        }

        public Options addABROptions(AbrOptions abrOptions) {
            this.abrOptions = abrOptions;
            return this;
        }

        public Options addVBROptions(VbrOptions vbrOptions) {
            this.vbrOptions = vbrOptions;
            return this;
        }

        public Options addFilterOptions(FilterOptions filterOptions) {
            this.filterOptions = filterOptions;
            return this;
        }

        public Options addID3TagOptions(Id3TagOptions id3TagOptions) {
            this.id3TagOptions = id3TagOptions;
            return this;
        }

        public Options addMP3HeaderStreamOptions(Mp3HeaderStreamOptions mp3HeaderStreamOptions) {
            this.mp3HeaderStreamOptions = mp3HeaderStreamOptions;
            return this;
        }

        public Mp3HeaderStreamOptions getMp3HeaderStreamOptions() {
            return mp3HeaderStreamOptions;
        }

        public InputOptions getInputOptions() {
            return inputOptions;
        }

        public RawPcmInputOptions getRawPcmInputOptions() {
            return rawPcmInputOptions;
        }

        public OperationalOptions getOperationalOptions() {
            return operationalOptions;
        }

        public CbrOptions getCbrOptions() {
            return cbrOptions;
        }

        public AbrOptions getAbrOptions() {
            return abrOptions;
        }

        public VbrOptions getVbrOptions() {
            return vbrOptions;
        }

        public FilterOptions getFilterOptions() {
            return filterOptions;
        }

        public Id3TagOptions getId3TagOptions() {
            return id3TagOptions;
        }
    }

    public interface Callback {
        void onStart();

        void onProgress(int total, int current);

        void onComplete();

        void onError();
    }

    static {
        System.loadLibrary("ame");
    }

    public static void encode(String in, String out) {
        encode(in, out, null, null);
    }

    public static void encode(String in, String out, Options options) {
        encode(in, out, options, null);
    }

    public static void encode(String in, String out, Callback callback) {
        encode(in, out, null, callback);
    }

    public static native void encode(String in, String out, Options options, Callback callback);

    /**
     * 获取LAME版本
     *
     * @return LAME版本号
     */
    public native String getLameVersion();
}
