package org.example.project.cmp.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.project.cmp.feature.login.LoginScreen
import org.example.project.cmp.feature.login.LoginViewModel
import org.example.project.cmp.feature.main.MainScreen
import org.example.project.cmp.feature.onBoard.GreetingScreen

import org.example.project.theme.ShuttleTheme

@Composable
@Preview
fun App() {
    RootNavHost()
}

@Composable
private fun RootNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Destination.Greet()
    ) {
        composable<Destination.Greet> { backStackEntry ->
            GreetingScreen(
                onNavigateToLogin = {
                    navController.navigate(route = Destination.Login)
                }
            )
        }
        composable<Destination.Login> { backStackEntry ->
            val greet = backStackEntry.toRoute<Destination.Greet>()
            LoginScreen(
                onNavigateToGreeting = {
                    navController.navigate(route = greet) {
                        popUpTo<Destination.Login> {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onNavigateToMainScreen = {
                    navController.navigate(route = Destination.Main) {
                        popUpTo<Destination.Login> {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Destination.Main> { backStackEntry ->
            MainScreen()
        }
    }
}