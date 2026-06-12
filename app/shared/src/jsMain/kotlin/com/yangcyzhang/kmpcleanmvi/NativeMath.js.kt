package com.yangcyzhang.kmpcleanmvi

actual fun nativeAdd(a: Int, b: Int): Int {
    return js("a + b") as Int
}
