package com.yangcyzhang.kmpcleanmvi

private object AndroidNative {
    init {
        try {
            System.loadLibrary("simple_math_android")
        } catch (e: UnsatisfiedLinkError) {
            println("Failed to load Android native library: ${e.message}")
        }
    }

    @JvmStatic
    external fun nativeAddJni(a: Int, b: Int): Int
}

actual fun nativeAdd(a: Int, b: Int): Int {
    return try {
        AndroidNative.nativeAddJni(a, b)
    } catch (e: UnsatisfiedLinkError) {
        a + b
    }
}
