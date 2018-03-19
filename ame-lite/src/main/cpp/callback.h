
#include <jni.h>

#define CLASS_NAME_CALLBACK     "io/auxo/ame/lite/Mp3Encoder$Callback"
#define METHOD_NAME_ON_START    "onStart"
#define METHOD_NAME_ON_PROGRESS "onProgress"
#define METHOD_NAME_ON_COMPLETE "onComplete"
#define METHOD_NAME_ON_ERROR    "onError"

#ifdef __cplusplus
extern "C" {
#endif

void on_start(JNIEnv *env, jobject callback);

void on_progress(JNIEnv *env, jobject callback, long total, long current);

void on_complete(JNIEnv *env, jobject callback);

void on_error(JNIEnv *env, jobject callback);

#ifdef __cplusplus
}
#endif