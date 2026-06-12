package com.yangcyzhang.kmpcleanmvi

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion + 
        " (NativeAdd result: 2+3=${nativeAdd(2, 3)})"
}

actual fun getPlatform(): Platform = IOSPlatform()