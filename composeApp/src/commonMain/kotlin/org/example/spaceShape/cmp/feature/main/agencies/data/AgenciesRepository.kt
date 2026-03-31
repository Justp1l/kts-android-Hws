package org.example.spaceShape.cmp.feature.main.agencies.data

import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgencyEntity
import org.example.spaceShape.cmp.feature.main.agencies.data.objects.RemoteAgency

interface AgenciesRepository {
    suspend fun loadItems(): List<AgencyEntity>
    suspend fun saveAgencies(initialAgencies: List<RemoteAgency>, size: Int)
}