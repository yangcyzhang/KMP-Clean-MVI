package com.yangcyzhang.kmpcleanmvi

import java.io.File

@Suppress("unused")
private object JniLoader {
    init {
        val userDir = System.getProperty("user.dir")
        val baseDir = if (userDir.endsWith("app/shared")) File(userDir, "native_lib") else File(userDir, "app/shared/native_lib")
        val libFile = File(baseDir, "libsimple_math_jni.dylib")
        
        if (libFile.exists()) {
            try {
                System.load(libFile.absolutePath)
                println("JNI Library loaded from ${libFile.absolutePath}")
            } catch (e: Exception) {
                println("Failed to load JNI library: ${e.message}")
            }
        } else {
            println("JNI Library not found at ${libFile.absolutePath}")
        }
    }
    
    fun ensureLoaded() {}
}

private class NativeMethods {
    companion object {
        @JvmStatic
        external fun nativeAddJni(a: Int, b: Int): Int
    }
}

actual fun nativeAdd(a: Int, b: Int): Int {
    JniLoader.ensureLoaded()
    return try {
        NativeMethods.nativeAddJni(a, b)
    } catch (e: UnsatisfiedLinkError) {
        println("JNI Call failed: ${e.message}")
        a + b
    }
}
