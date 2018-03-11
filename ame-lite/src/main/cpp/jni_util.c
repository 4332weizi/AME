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
    (*env)->DeleteLocalRef(env, methodID);
    (*env)->DeleteLocalRef(env, barr);
    (*env)->DeleteLocalRef(env, length);
    (*env)->DeleteLocalRef(env, *ba);

    return rtn;
}

jmethodID
get_method_id(JNIEnv *env, char *className, char *methodName,
              char *signature) {
    jclass clazz = (*env)->FindClass(env, className);
    jmethodID id = (*env)->GetMethodID(env, clazz, methodName, signature);
    (*env)->DeleteLocalRef(env, clazz);
    return id;
}

jobject
invoke_method_get_jobject(JNIEnv *env, jobject obj, char *objClassName, char *methodName,
                          char *signature) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, signature);
    jobject result = (*env)->CallObjectMethod(env, obj, methodID);
    (*env)->DeleteLocalRef(env, methodID);
    return result;
}

jboolean
invoke_method_get_jboolean(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_BOOLEAN);
    jboolean result = (*env)->CallBooleanMethod(env, obj, methodID);
    (*env)->DeleteLocalRef(env, methodID);
    return result;
}

jint
invoke_method_get_jint(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_INT);
    jint result = (*env)->CallIntMethod(env, obj, methodID);
    (*env)->DeleteLocalRef(env, methodID);
    return result;
}

jfloat
invoke_method_get_jfloat(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_FLOAT);
    jfloat result = (*env)->CallFloatMethod(env, obj, methodID);
    (*env)->DeleteLocalRef(env, methodID);
    return result;
}

jdouble
invoke_method_get_jdouble(JNIEnv *env, jobject obj, char *objClassName, char *methodName) {
    jmethodID methodID = get_method_id(env, objClassName, methodName, SIGNATURE_DOUBLE);
    jdouble result = (*env)->CallDoubleMethod(env, obj, methodID);
    (*env)->DeleteLocalRef(env, methodID);
    return result;
}