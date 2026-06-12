package com.yangcyzhang.kmpcleanmvi

import kotlin.test.Test
import kotlin.test.assertEquals

class JniTest {
    @Test
    fun testNativeAddJni() {
        val result = nativeAdd(10, 20)
        println("JNI Result: $result")
        assertEquals(30, result)
    }
}
