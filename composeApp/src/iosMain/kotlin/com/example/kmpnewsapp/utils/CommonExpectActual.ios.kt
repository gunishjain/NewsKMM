package com.example.kmpnewsapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSUserDomainMask
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
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

@OptIn(ExperimentalForeignApi::class)
actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSetting.getDataStore(
        producerPath = {
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$DataStoreFileName"
        })
}