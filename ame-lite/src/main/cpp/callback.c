#include "callback.h"
#include "jni_util.h"

void on_start(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_START,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
    }
}

void on_progress(JNIEnv *env, jobject callback, long total, long current) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_PROGRESS,
                                           "(II)V");
        (*env)->CallVoidMethod(env, callback, methodId, total, current);
    }
}

void on_complete(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_COMPLETE,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
    }
}

void on_error(JNIEnv *env, jobject callback) {
    if (callback != NULL) {
        jmethodID methodId = get_method_id(env, CLASS_NAME_CALLBACK, METHOD_NAME_ON_ERROR,
                                           SIGNATURE_VOID);
        (*env)->CallVoidMethod(env, callback, methodId);
    }
}