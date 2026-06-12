#include <jni.h>
#include "simple_math.h"

// Standard JNI naming for companion object: Java_PackageName_ClassName_00024Companion_MethodName
JNIEXPORT jint JNICALL Java_com_yangcyzhang_kmpcleanmvi_NativeMethods_00024Companion_nativeAddJni(JNIEnv *env, jobject obj, jint a, jint b) {
    return add_numbers(a, b);
}

// Fallback just in case
JNIEXPORT jint JNICALL Java_com_yangcyzhang_kmpcleanmvi_NativeMethods_nativeAddJni(JNIEnv *env, jclass clazz, jint a, jint b) {
    return add_numbers(a, b);
}
