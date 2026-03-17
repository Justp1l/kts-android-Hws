package org.example.project.cmp.feature.main.data.Objects.Rocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteRocket(
    @SerialName("rocketImage")
    val image: String?,
    @SerialName("rocketName")
    val name: String,
    @SerialName("rocketSize")
    val size: String?
)