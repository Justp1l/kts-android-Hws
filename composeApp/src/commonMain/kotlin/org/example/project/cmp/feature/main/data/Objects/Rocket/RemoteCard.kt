package org.example.project.cmp.feature.main.data.Objects.Rocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.cmp.feature.main.data.Objects.Rocket.RemoteRocket

@Serializable
data class RemoteCard(
    @SerialName("mission")
    val mission: String,
    @SerialName("missionDescription")
    val missionDescription: String,
    @SerialName("status")
    val status:String,
    @SerialName("id")
    val id: Long,
    @SerialName("rocket")
    val rocket: RemoteRocket,
    @SerialName("map")
    val padMap: String?,
    @SerialName("padCountry")
    val padCountry: String,
    @SerialName("probability")
    val probability: String?
)