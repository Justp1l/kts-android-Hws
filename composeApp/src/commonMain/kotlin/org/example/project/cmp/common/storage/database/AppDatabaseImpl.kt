package org.example.project.cmp.common.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.cmp.common.Converters
import org.example.project.getDatabaseBuilder

@TypeConverters(Converters::class)
@Database(
    entities = [AgencyEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun agencyDao(): AgenciesDao
}

fun getAppDatabase(): AppDatabase {
    return getDatabaseBuilder()
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .build()
}