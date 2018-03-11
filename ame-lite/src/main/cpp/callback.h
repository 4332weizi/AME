
#include <jni.h>

void on_start(JNIEnv *env, jobject callback);

void on_progress(JNIEnv *env, jobject callback, long total, long current);

void on_complete(JNIEnv *env, jobject callback);

void on_error(JNIEnv *env, jobject callback);