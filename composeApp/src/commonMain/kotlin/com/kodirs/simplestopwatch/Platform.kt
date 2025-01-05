package com.kodirs.simplestopwatch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform