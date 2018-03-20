#include <jni.h>
#include <malloc.h>
#include <string.h>
#include "mp3_encoder.h"
#include "lame.h"
#include "jni_util.h"
#include "callback.h"
#include "parse.h"

long fsize(FILE *fp) {
    long n;
    // 当前位置
    fpos_t fpos;
    // 获取当前位置
    fgetpos(fp, &fpos);
    fseek(fp, 0, SEEK_END);
    n = ftell(fp);
    // 恢复之前的位置
    fsetpos(fp, &fpos);
    return n;
}

void encode(JNIEnv *env, jclass clazz, jstring in, jstring out, jobject options, jobject callback) {

    lame_global_flags *gf = lame_init();
    // 解析参数
    parse_options(env, gf, options);
    // 初始化参数
    int ret = lame_init_params(gf);
    // LAME参数初始化失败
    if (ret != 0) {
        on_error(env, callback);
        return;
    }

    // 获取输入输出文件路径
    char *src = parse_jstring(env, in);
    char *tar = parse_jstring(env, out);

    LOGI("source file is = %s", src);
    LOGI("target file is = %s", tar);

    // 打开输入文件
    FILE *fin = fopen(src, "rb");

    if (fin != NULL) {

        // 打开输出文件
        FILE *fout = fopen(tar, "wb");

        short int wav_buffer[8192 * 2];
        unsigned char mp3_buffer[8192];

        int read;
        int write; //代表读了多少个次 和写了多少次
        long current = 0; // 当前读的wav文件的byte数目

        long total = fsize(fin);

        // 回调开始转码
        on_start(env, callback);

        do {

            read = fread(wav_buffer, sizeof(short int) * 2, 8192, fin);

            current += read * sizeof(short int) * 2;

            if (read != 0) {
                write = lame_encode_buffer_interleaved(gf, wav_buffer, read, mp3_buffer, 8192);
                // 把转化后的mp3数据写到文件里
                fwrite(mp3_buffer, sizeof(unsigned char), write, fout);
            }

            if (read == 0) {
                lame_encode_flush(gf, mp3_buffer, 8192);
            }

            LOGI("on_progress %ld,%ld", total, current);
            on_progress(env, callback, total, current);
        } while (read != 0);

        LOGI("convert finish");

        // 关闭文件
        lame_close(gf);
        fclose(fin);
        fclose(fout);
        // 回调转码成功
        on_complete(env, callback);
    } else {
        LOGE("Can not found file %s", src);
        on_error(env, callback);
    }
}

/*
 * Class:     io_auxo_ame_lite_Mp3Encoder
 * Method:    encode
 * Signature: (Ljava/lang/String;Ljava/lang/String;Lio/auxo/ame/lite/Mp3Encoder$Options;Lio/auxo/ame/lite/Mp3Encoder$Callback;)V
 */
JNIEXPORT void JNICALL
Java_io_auxo_ame_lite_Mp3Encoder_encode__Ljava_lang_String_2Ljava_lang_String_2Lio_auxo_ame_lite_Mp3Encoder_00024Options_2Lio_auxo_ame_lite_Mp3Encoder_00024Callback_2
        (JNIEnv *env, jclass clazz, jstring in, jstring out, jobject options, jobject callback) {
    encode(env, clazz, in, out, options, callback);
}

/*
 * Class:     io_auxo_ame_lite_Mp3Encoder
 * Method:    getLameVersion
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_io_auxo_ame_lite_Mp3Encoder_getLameVersion
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, get_lame_version());
}