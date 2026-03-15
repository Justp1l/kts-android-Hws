package org.example.project.cmp.app

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.project.cmp.common.MainViewModel
import org.example.project.cmp.feature.login.LoginScreen
import org.example.project.cmp.feature.main.MainAgencyScreen
import org.example.project.cmp.feature.main.MainScreen
import org.example.project.cmp.feature.onBoard.Onboarding
import org.example.project.cmp.feature.onBoard.pages.LastPage

import org.example.project.theme.ShuttleTheme

@Composable
fun App(
    viewModel: MainViewModel
) {
    val startDestination = remember { mutableStateOf<Destination?>(null) }
    LaunchedEffect(Unit) {
        startDestination.value = viewModel.getStartDestination()
        Napier.base(DebugAntilog())
    }
    startDestination.value?.let { destination ->
        ShuttleTheme() {
            RootNavHost(startDestination = destination)
        }
    }
}

@Composable
private fun RootNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Destination
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Destination.MainAgency>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            MainAgencyScreen()
        }
        composable<Destination.Onboarding>(
            enterTransition = { fadeIn(tween(300)) },
            exitTransition = { fadeOut(tween(300)) }
        ) {
            Onboarding(
                onNavigateToLogin = {
                    navController.navigate(route = Destination.Login)
                }
            )
        }
        composable<Destination.Login> {
            LoginScreen(
                onNavigateToMainScreen = {
                    navController.navigate(route = Destination.MainAgency) {
                        popUpTo<Destination.Onboarding> {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Destination.Main> {
            MainScreen()
        }
    }
}

@Composable
@Preview
fun appPreview() {
    ShuttleTheme() {
        RootNavHost(
            //startDestination = Destination.MainAgency
            startDestination = Destination.Onboarding
        )
    }
}