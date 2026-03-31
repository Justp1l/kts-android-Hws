package org.example.spaceShape.cmp.common.net

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.spaceShape.cmp.feature.main.agencies.data.objects.RemoteAgency
import org.example.spaceShape.cmp.feature.main.data.Objects.RemoteRepository

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