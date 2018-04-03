package io.auxo.ame;

/**
 * Mp3Encoder
 *
 * @author Victor Chiu
 * @date 2018/3/8
 */

public class Mp3Encoder {

    public static class Options {

        private InputOptions inputOptions;
        private RawPcmInputOptions rawPcmInputOptions;
        private OperationalOptions operationalOptions;
        private CbrOptions cbrOptions;
        private AbrOptions abrOptions;
        private VbrOptions vbrOptions;
        private Mp3HeaderStreamOptions mp3HeaderStreamOptions;
        private FilterOptions filterOptions;
        private Id3TagOptions id3TagOptions;

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
