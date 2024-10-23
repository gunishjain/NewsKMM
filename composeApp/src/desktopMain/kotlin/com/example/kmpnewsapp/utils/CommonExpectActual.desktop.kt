package com.example.kmpnewsapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Desktop
}

actual fun shareLink(url: String) {

    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url),null)
}

actual fun dataStorePreference(): DataStore<Preferences> {
    return AppSetting.getDataStore {
        DataStoreFileName
    }
}