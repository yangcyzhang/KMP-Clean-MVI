package com.yangcyzhang.kmpcleanmvi

import web.navigator.navigator

class JsPlatform: Platform {
    private val userAgent = navigator.userAgent
    private val browserList = listOf("Chrome", "Firefox", "Safari", "Edge")

    override val name: String = (userAgent.findAnyOf(browserList, ignoreCase = true)
            ?.let { (startIndex) -> userAgent.substring(startIndex).substringBefore(" ") }
            ?: "Unknown") + " (NativeAdd result: 2+3=${nativeAdd(2, 3)})"
}

actual fun getPlatform(): Platform = JsPlatform()