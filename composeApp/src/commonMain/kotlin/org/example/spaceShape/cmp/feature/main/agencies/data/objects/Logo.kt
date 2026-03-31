package org.example.spaceShape.cmp.feature.main.agencies.data.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Logo(
    @SerialName("image_url")
    val link: String?
) {
}