package org.example.spaceShape.cmp.feature.main.agencies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.spaceShape.getDatabaseBuilder

@TypeConverters(Converters::class)
@Database(
    entities = [AgencyEntity::class],
    version = 1
)
abstract class MainAgencyDatabase : RoomDatabase() {
    abstract fun agencyDao(): AgenciesDao
}

fun getAppDatabase(): MainAgencyDatabase {
    return getDatabaseBuilder()
        .fallbackToDestructiveMigration(true)
        .setDriver(BundledSQLiteDriver())
        .build()
}