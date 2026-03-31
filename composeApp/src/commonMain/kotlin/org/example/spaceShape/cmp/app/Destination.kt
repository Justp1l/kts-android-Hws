package org.example.spaceShape.cmp.app

import kotlinx.serialization.Serializable
@Serializable
sealed interface Destination {
    @Serializable
    data object Onboarding : Destination

    @Serializable
    data object Login : Destination
    @Serializable
    data object FeatureNavigation : Destination
    @Serializable
    data object Main : Destination
    @Serializable
    data object MainAgency : Destination
}