package org.example.spaceShape

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import org.example.spaceShape.cmp.feature.main.agencies.data.database.MainAgencyDatabase
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getFilesDir(): String {
    return NSSearchPathForDirectoriesInDomains(
        NSDocumentDirectory, NSUserDomainMask, true
    ).first() as String
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<MainAgencyDatabase> {
    val dbFile = NSHomeDirectory() + "/Documents/app-database"
    return Room.databaseBuilder<MainAgencyDatabase>(name = dbFile)
}

private const val DATA_STORE_FILE_NAME = "app_storage.preferences_pb"
@OptIn(ExperimentalForeignApi::class)
actual fun createDataStore(): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            val dir = NSFileManager.defaultManager
                .URLForDirectory(NSDocumentDirectory, NSUserDomainMask, null, true, null)
            (requireNotNull(dir).path + "/$DATA_STORE_FILE_NAME").toPath()
        }
    )
