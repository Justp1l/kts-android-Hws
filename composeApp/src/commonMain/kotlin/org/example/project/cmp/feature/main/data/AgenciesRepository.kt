package org.example.project.cmp.feature.main.data

import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency

interface AgenciesRepository {
    suspend fun loadItems(): List<AgencyEntity>
    suspend fun saveAgencies(initialAgencies: List<RemoteAgency>, size: Int)
}