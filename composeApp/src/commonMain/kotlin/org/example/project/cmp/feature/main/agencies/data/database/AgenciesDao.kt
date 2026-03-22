package org.example.project.cmp.feature.main.agencies.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AgenciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgency(agencies: List<AgencyEntity>)

    @Query("SELECT * FROM agency")
    suspend fun getAllAgencies(): List<AgencyEntity>
}