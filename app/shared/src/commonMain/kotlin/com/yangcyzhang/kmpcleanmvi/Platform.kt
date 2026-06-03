package com.yangcyzhang.kmpcleanmvi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform