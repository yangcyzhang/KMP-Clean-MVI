package com.yangcyzhang.kmpcleanmvi

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")} (NativeAdd result: 2+3=${nativeAdd(2, 3)})"
}

actual fun getPlatform(): Platform = JVMPlatform()