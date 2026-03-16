package org.example.project.cmp.feature.main.data.Objects.Agency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteAgency(
    @SerialName("name")
    val name: String,
    @SerialName("abbrev")
    val abbrev: String,
    @SerialName("administrator")
    val ceo: String,
    @SerialName("featured")
    val featured: Boolean,
    @SerialName("country")
    val country: List<Country>,
    @SerialName("image")
    val image: Image?,
    @SerialName("description")
    val description: String?,
    @SerialName("founding_year")
    val foundingYear: String?,
    @SerialName ("social_logo")
    val logo: Logo
) {

}