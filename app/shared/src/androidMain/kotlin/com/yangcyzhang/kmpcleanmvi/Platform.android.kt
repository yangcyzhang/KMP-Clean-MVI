package com.yangcyzhang.kmpcleanmvi

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT} (NativeAdd result: 2+3=${nativeAdd(2, 3)})"
}

actual fun getPlatform(): Platform = AndroidPlatform()