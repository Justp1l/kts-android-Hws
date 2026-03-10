package org.example.project.cmp.feature.main.data.Objects.Agency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAgency(
    @SerialName("name")
    val name: String,
    @SerialName("administrator")
    val ceo: String,
    @SerialName("description")
    val description: String?,
    @SerialName("founding_year")
    val foundingYear: String?,
    @SerialName ("social_logo")
    val logo: Logo
) {

}