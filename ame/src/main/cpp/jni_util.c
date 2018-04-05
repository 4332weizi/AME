//
// Created by ZHAOWEIWEI on 2018/2/8.
//
#include <malloc.h>
#include <string.h>
#include "jni_util.h"

#define UTF8 "UTF-8"
#define LOG_TAG "ame"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

char *parse_jstring(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;

    jmethodID methodID = get_method_id(env, "java/lang/String", "getBytes",
                                       "(Ljava/lang/String;)[B");

    jbooleanArray barr = (*env)->CallObjectMethod(env, jstr, methodID,
                                                  (*env)->NewStringUTF(env, UTF8));

    jsize length = (*env)->GetArrayLength(env, barr);
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);

    if (length > 0) {
        rtn = (char *) malloc(length + 1);
        memcpy(rtn, ba, length);
        rtn[length] = 0;
    }

    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
    (*env)->DeleteLocalRef(env, barr);

    return rtn;
}

jmethodID
get_method_id(JNIEnv *env, char *className, char *methodName,
              char *signature) {
    jclass clazz = (*env)->FindClass(env, className);
    return (*env)->GetMethodID(env, clazz, methodName, signature);
}

jobject
invoke_method_get_jobject(JNIEnv *env, jobject obj, char *objClassName, char *methodName,
                          char *signature) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, signature);
    return (*env)->CallObjectMethod(env, obj, methodID);
}

jboolean
invoke_method_get_jboolean(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_BOOLEAN);
    return (*env)->CallBooleanMethod(env, obj, methodID);
}

jint
invoke_method_get_jint(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_INT);
    return (*env)->CallIntMethod(env, obj, methodID);
}

jfloat
invoke_method_get_jfloat(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_FLOAT);
    return (*env)->CallFloatMethod(env, obj, methodID);
}

jdouble
invoke_method_get_jdouble(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_DOUBLE);
    return (*env)->CallDoubleMethod(env, obj, methodID);
}