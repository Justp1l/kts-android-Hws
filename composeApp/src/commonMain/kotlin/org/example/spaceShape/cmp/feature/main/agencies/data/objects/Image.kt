package org.example.spaceShape.cmp.feature.main.agencies.data.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("name")
    val imageName: String?,
    @SerialName("image_url")
    val imageURL: String?
)