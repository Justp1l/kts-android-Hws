package org.example.project.cmp.feature.main.data.Objects.Agency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    @SerialName("image_url")
    val link: String?
) {
}