package com.example.kmpnewsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform