package com.example.kmpnewsapp.utils

import platform.Foundation.NSUUID

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getType(): Type {
    return Type.Mobile
}