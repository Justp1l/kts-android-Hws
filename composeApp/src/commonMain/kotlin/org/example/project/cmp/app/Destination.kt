package org.example.project.cmp.app

import kotlinx.serialization.Serializable
@Serializable
sealed interface Destination {
    @Serializable
    data class Greet(var name: String = "") : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Main : Destination
}