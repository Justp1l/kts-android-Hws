package org.example.project.cmp.feature.main.data

import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency
import org.example.project.cmp.feature.main.data.net.ApiInteraction
import org.example.project.cmp.feature.main.data.net.Networking
import kotlin.String

class AgenciesRepository {
    val Api = ApiInteraction(
        url = "2.3.0/agencies",
        httpClient = Networking.httpClient
    )
    suspend fun loadItems(): List<RemoteAgency> {
        return Api.initialInteraction(tag = "?featured=true&limit=50").items
    }

    suspend fun search(query: String): List<RemoteAgency> {
        return Api.searchAgencies(query).items
    }
}