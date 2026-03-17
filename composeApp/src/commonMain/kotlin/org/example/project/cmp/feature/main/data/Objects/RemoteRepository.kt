package org.example.project.cmp.feature.main.data.Objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRepository<T>(
    @SerialName("count")
    val size:Int,
    @SerialName("results")
    val items: List<T>
)