package org.example.spaceShape

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import okio.Path.Companion.toOkioPath
import org.example.spaceShape.cmp.feature.main.agencies.data.database.MainAgencyDatabase

lateinit var appContext: Context
    private set

fun initContext(context: Context) {
    appContext = context.applicationContext
}
actual fun getFilesDir(): String = appContext.filesDir.absolutePath
actual fun getDatabaseBuilder(): RoomDatabase.Builder<MainAgencyDatabase> {
    val dbFile = appContext.getDatabasePath("app-database")
    return Room.databaseBuilder<MainAgencyDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}

actual fun getPlatform(): Platform {
    return getPlatform()
}

private const val DATA_STORE_FILE_NAME = "app_storage.preferences_pb"
actual fun createDataStore(): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            appContext.filesDir.resolve(DATA_STORE_FILE_NAME).toOkioPath()
        }
    )