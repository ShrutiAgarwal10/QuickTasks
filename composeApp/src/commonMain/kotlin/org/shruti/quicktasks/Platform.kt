package org.shruti.quicktasks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform