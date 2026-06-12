package com.yangcyzhang.kmpcleanmvi

import com.yangcyzhang.kmpcleanmvi.native.add_numbers
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual fun nativeAdd(a: Int, b: Int): Int = add_numbers(a, b)
