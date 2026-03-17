package org.example.project.cmp.feature.main.data.net

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency
import org.example.project.cmp.feature.main.data.Objects.RemoteRepository

class ApiInteraction(val url: String, private val httpClient: HttpClient) {

    suspend fun searchAgencies(query:String) : RemoteRepository<RemoteAgency> {
        return httpClient.get(url) {
            parameter("q", query)
        }.body()
    }
    suspend fun interactionWithNet(tag:String = ""): RemoteRepository<RemoteAgency> {
        return httpClient.get(url + tag).body()
    }

}