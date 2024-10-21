package com.example.kmpnewsapp.utils

import platform.Foundation.NSUUID
import platform.UIKit.*

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun shareLink(url: String) {

    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )

}