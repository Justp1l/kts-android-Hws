package org.example.project

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.room.RoomDatabase
import org.example.project.cmp.common.storage.database.AppDatabase

lateinit var appContext: Context
    private set

fun initContext(context: Context) {
    appContext = context.applicationContext
}

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual fun getFilesDir(): String = appContext.filesDir.absolutePath
actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = appContext.getDatabasePath("app-database")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}