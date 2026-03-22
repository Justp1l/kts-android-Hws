package org.example.project.cmp.feature.main.agencies.data.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name")
    val countryName: String
)
