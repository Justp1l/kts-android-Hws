package org.example.project

import kotlinx.serialization.Serializable

@Serializable
sealed interface Destination {

    @Serializable
    data class Greet(var name: String = "")

    @Serializable
    object Login

}