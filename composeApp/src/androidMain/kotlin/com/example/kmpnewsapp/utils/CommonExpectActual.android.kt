package com.example.kmpnewsapp.utils

import java.util.UUID

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Mobile
}