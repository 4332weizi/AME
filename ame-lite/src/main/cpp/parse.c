
#include "parse.h"
#include "jni_util.h"

void parse_options(JNIEnv *env, lame_global_flags *gf, jobject options) {
    if (options != NULL) {
        int sample_rate = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME,
                                                 METHOD_GET_SAMPLE_RATE);
        lame_set_out_samplerate(gf, sample_rate);
        int bit_rate = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME,
                                              METHOD_GET_BIT_RATE);
        lame_set_brate(gf, bit_rate);
        int num_channels = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME,
                                                  METHOD_GET_NUM_CHANNELS);
        lame_set_num_channels(gf, num_channels);
        int mode = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME, METHOD_GET_MODE);
        lame_set_mode(gf, mode);
        int quality = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME, METHOD_GET_QUALITY);
        lame_set_quality(gf, quality);
        int vbr_mode = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME,
                                              METHOD_GET_VBR_MODE);
        lame_set_VBR(gf, vbr_mode);
        int vbr_quality = invoke_method_get_jint(env, options, OPTIONS_CLASS_NAME,
                                                 METHOD_GET_VBR_QUALITY);
        lame_set_VBR_q(gf, vbr_quality);
    }
}
