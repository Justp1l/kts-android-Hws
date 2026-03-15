package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.project.cmp.app.App



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
