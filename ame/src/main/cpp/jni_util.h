#include <jni.h>
#include <android/log.h>

#define LOG_TAG "ame"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

#define SIGNATURE_BOOLEAN   "()Z"
#define SIGNATURE_BYTE      "()B"
#define SIGNATURE_CHAR      "()C"
#define SIGNATURE_SHORT     "()S"
#define SIGNATURE_INT       "()I"
#define SIGNATURE_LONG      "()J"
#define SIGNATURE_FLOAT     "()F"
#define SIGNATURE_DOUBLE    "()D"
#define SIGNATURE_VOID      "()V"
#define SIGNATURE_OBJECTS   "()L"

jmethodID
get_method_id(JNIEnv *env, char *className, char *methodName,
              char *signature);

jobject
invoke_method_get_jobject(JNIEnv *env, jobject obj, char *objClassName, char *methodName,
                          char *signature);

jboolean
invoke_method_get_jboolean(JNIEnv *env, jobject obj, char *objClassName, char *methodName);

jint
invoke_method_get_jint(JNIEnv *env, jobject obj, char *objClassName, char *methodName);

jfloat
invoke_method_get_jfloat(JNIEnv *env, jobject obj, char *objClassName, char *methodName);

jdouble
invoke_method_get_jdouble(JNIEnv *env, jobject obj, char *objClassName, char *methodName);

char *parse_jstring(JNIEnv *env, jstring jstr);