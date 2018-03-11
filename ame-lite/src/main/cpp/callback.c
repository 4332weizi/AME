#include "callback.h"
#include "jni_util.h"

#define CLASS_NAME_CALLBACK     "io.auxo.ame.lite.Mp3Encoder$Callback"
#define METHOD_NAME_ON_START    "onStart"
#define METHOD_NAME_ON_PROGRESS "onProgress"
#define METHOD_NAME_ON_COMPLETE "onComplete"
#define METHOD_NAME_ON_ERROR    "onError"

void on_start(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_START,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
        (*env)->DeleteLocalRef(env, methodId);
    }
}

void on_progress(JNIEnv *env, jobject callback, long total, long progress) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_PROGRESS,
                                           "(JJ)V");
        (*env)->CallVoidMethod(env, callback, methodId, total, progress);
        (*env)->DeleteLocalRef(env, methodId);
    }
}

void on_complete(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_COMPLETE,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
        (*env)->DeleteLocalRef(env, methodId);
    }
}

void on_error(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_ERROR,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
        (*env)->DeleteLocalRef(env, methodId);
    }
}