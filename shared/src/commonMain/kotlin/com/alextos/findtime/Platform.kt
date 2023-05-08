package com.alextos.findtime

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform