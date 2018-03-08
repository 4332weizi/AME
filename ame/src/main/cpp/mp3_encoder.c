#include <jni.h>
#include "lame.h"

/*
 * Class:     io_auxo_ame_Mp3Encoder
 * Method:    getLameVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_io_auxo_ame_Mp3Encoder_getLameVersion
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, get_lame_version());
}