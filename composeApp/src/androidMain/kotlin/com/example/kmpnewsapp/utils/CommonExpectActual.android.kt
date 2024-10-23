package com.example.kmpnewsapp.utils

import android.app.Activity
import android.content.Intent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.util.UUID

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun shareLink(url: String) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT,url)
    }

    val intentChooser = Intent.createChooser(intent, "Share Link")
    activityProvider.invoke().startActivity(intentChooser)

}

private var activityProvider : () -> Activity = {
    throw IllegalArgumentException("Error")
}

fun setActivityProvider(provider :() -> Activity){
    activityProvider = provider
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSetting.getDataStore(
        producerPath = {
            activityProvider.invoke().filesDir
                .resolve(DataStoreFileName)
                .absolutePath

        }
    )
}