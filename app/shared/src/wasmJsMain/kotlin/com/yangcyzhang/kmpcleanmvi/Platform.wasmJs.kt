package com.yangcyzhang.kmpcleanmvi

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm (NativeAdd result: 2+3=${nativeAdd(2, 3)})"
}

actual fun getPlatform(): Platform = WasmPlatform()