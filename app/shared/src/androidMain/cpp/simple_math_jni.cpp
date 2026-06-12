#include <jni.h>
extern "C" {
    #include "simple_math.h"
}

extern "C" JNIEXPORT jint JNICALL
Java_com_yangcyzhang_kmpcleanmvi_AndroidNative_nativeAddJni(JNIEnv *env, jobject thiz, jint a, jint b) {
    return add_numbers(a, b);
}
