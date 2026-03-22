package org.example.project

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.RoomDatabase
import org.example.project.cmp.feature.main.agencies.data.database.MainAgencyDatabase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getFilesDir(): String

expect fun getDatabaseBuilder(): RoomDatabase.Builder<MainAgencyDatabase>

expect fun createDataStore(): DataStore<Preferences>
private const val DATA_STORE_FILE_NAME = "app-storage.preferences_pb"