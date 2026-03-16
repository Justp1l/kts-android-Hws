package org.example.project.cmp.feature.main.data.Objects.Agency

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("name")
    val imageName: String?,
    @SerialName("image_url")
    val imageURL: String?
)