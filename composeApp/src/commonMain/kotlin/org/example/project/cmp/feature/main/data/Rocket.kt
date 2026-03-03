package org.example.project.cmp.feature.main.data

import org.jetbrains.compose.resources.DrawableResource

data class Rocket(
    val id: Long,
    val model: String,
    val creationDate: Int,
    val image: DrawableResource,
)
