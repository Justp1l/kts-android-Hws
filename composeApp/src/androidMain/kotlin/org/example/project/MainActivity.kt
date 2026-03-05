package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.project.screens.GreetingScreen
import org.example.project.screens.LoginScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Destination.Greet()
            ) {
                composable<Destination.Greet> { backStackEntry ->
                    val login: Destination.Login = backStackEntry.toRoute()
                    GreetingScreen(
                        onNavigateToLogin = {
                            navController.navigate(route = login)
                        }
                    )
                }
                composable<Destination.Login> { backStackEntry ->
                    val greet: Destination.Greet = backStackEntry.toRoute()
                    LoginScreen(
                        onNavigateBack = {
                            navController.navigate(route = greet) {
                                popUpTo<Destination.Login> {
                                    inclusive = true
                                    saveState = true
                                }
                                restoreState = true
                            }

                        }
                    )
                }
            }
        }
    }
}

    @Preview
    @Composable
    fun GreetingScreenAndroidPreview() {
        GreetingScreen(
            onNavigateToLogin = {}
        )
    }