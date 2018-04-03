#include <jni.h>
#include "lame.h"
#include "ame_main.h"

/*
 * Class:     io_auxo_ame_Mp3Encoder
 * Method:    encode
 * Signature: (Ljava/lang/String;Ljava/lang/String;Lio/auxo/ame/Mp3Encoder$Options;Lio/auxo/ame/Mp3Encoder$Callback;)V
 */
JNIEXPORT void JNICALL Java_io_auxo_ame_Mp3Encoder_encode
        (JNIEnv *env, jclass clazz, jstring input, jstring output, jobject options,
         jobject callback) {

    ame_main(env, input, output, options, callback);
}

/*
 * Class:     io_auxo_ame_Mp3Encoder
 * Method:    getLameVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_io_auxo_ame_Mp3Encoder_getLameVersion
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, get_lame_version());
}
