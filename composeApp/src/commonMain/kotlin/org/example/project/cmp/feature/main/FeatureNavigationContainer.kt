package org.example.project.cmp.feature.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.cmp.app.Destination
import org.example.project.cmp.common.UI.Bars.Navbar
import org.example.project.cmp.common.UI.Bars.TopBar.TopBar
import org.example.project.cmp.feature.main.UI.MainScreen
import org.example.project.cmp.feature.main.agencies.UI.MainAgencyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FeatureNavigationContainer() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Main,
    ) {
        composable<Destination.Main> {
            MainScreen(
                onLaunchesPressed = {
                    navController.navigate(route = Destination.MainAgency)
                },
                onRocketsPressed = {
                    navController.navigate(route = Destination.MainAgency)
                },
                onAgenciesPressed = {
                    navController.navigate(route = Destination.MainAgency)
                },
                onMainPress = {},
                onHeartPress = {
                    navController.navigate(route = Destination.MainAgency)
                },
                onProfilePress = {
                    navController.navigate(route = Destination.MainAgency)
                }
            )
        }
        composable<Destination.MainAgency>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            MainAgencyScreen(
                onMainPress = { navController.navigate(route = Destination.Main) },
                onHeartPress = {},
                onProfilePress = {}
            )
        }
        // Destination.Favourite
        // Destination.Profile
        // Destination.Map
    }
}
