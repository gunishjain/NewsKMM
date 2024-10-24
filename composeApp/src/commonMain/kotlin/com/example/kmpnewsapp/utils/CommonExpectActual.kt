package com.example.kmpnewsapp.utils

import androidx.compose.ui.graphics.ColorProducer
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

expect fun randomUUIDStr():String

expect fun getType():Type

expect fun shareLink(url: String)

expect fun dataStorePreference() : DataStore<Preferences>

object AppSetting {
    private lateinit var dataStore: DataStore<Preferences>

    @OptIn(InternalCoroutinesApi::class)
    private val lock = SynchronizedObject()


    @OptIn(InternalCoroutinesApi::class)
    fun getDataStore(producerPath: ()->String): DataStore<Preferences> {

        return synchronized(lock) {
            if (this::dataStore.isInitialized) {
                dataStore
            } else {
                PreferenceDataStoreFactory.createWithPath(
                    produceFile = { producerPath().toPath() }
                ).also { dataStore = it }

            }
        }
    }

}

