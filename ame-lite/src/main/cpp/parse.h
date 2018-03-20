
#include <jni.h>
#include "lame.h"

#define OPTIONS_CLASS_NAME "io/auxo/ame/lite/Mp3Encoder$Options"

#define METHOD_GET_SAMPLE_RATE "getSampleRate"
#define METHOD_GET_BIT_RATE "getBitrate"
#define METHOD_GET_NUM_CHANNELS "getNumChannels"
#define METHOD_GET_MODE "getMode"
#define METHOD_GET_QUALITY "getQuality"
#define METHOD_GET_VBR_MODE "getVbrMode"
#define METHOD_GET_VBR_QUALITY "getVbrQuality"

#ifdef __cplusplus
extern "C" {
#endif

void parse_options(JNIEnv *env, lame_global_flags *gf, jobject options);

#ifdef __cplusplus
}
#endif