package org.example.project

import androidx.room.RoomDatabase
import org.example.project.cmp.common.storage.database.AppDatabase

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getFilesDir(): String

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>