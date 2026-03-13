package org.example.project.cmp.feature.onBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.bad_script_regular
import kts_hw2.composeapp.generated.resources.img_1
import kts_hw2.composeapp.generated.resources.photo
import org.example.project.cmp.app.Destination
import org.example.project.cmp.feature.TopBar.TopBar
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingScreen(
    onNavigateToLogin: () -> Unit,
) {
    ShuttleTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar() },
            containerColor = ShuttleTheme.colors.background,
            contentColor = ShuttleTheme.colors.onBackground,
            contentWindowInsets = WindowInsets()
        ) {
            GreetingContent(onNavigateToLogin)
        }
    }

}

@Composable
fun GreetingContent(onNavigateToLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = "https://s0.rbk.ru/v6_top_pics/media/img/7/75/347472273420757.jpeg",
                contentDescription = "LegendaryMem",
                placeholder = painterResource(Res.drawable.img_1),
                error = painterResource(Res.drawable.photo),
                onError = { state ->
                    println("Coil error: ${state.result.throwable}")
                },
            )

            Column(
                modifier = Modifier.padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onNavigateToLogin,
                    content = {
                        Text(
                            text = "Let's get started",
                            fontFamily = ShuttleTheme.typography.bodyBold.fontFamily
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContentColor = ShuttleTheme.colors.onBackground,
                        containerColor = ShuttleTheme.colors.onBackground,
                        contentColor = ShuttleTheme.colors.background
                    )
                )
                Spacer(Modifier.padding(10.dp))
                Text(
                    text = "Данное приложение было сделано студентом Вединым Дмитрием специально для курса \"Начинающий Android-разработчик\" от KTS",
                    fontFamily = FontFamily(Font(Res.font.bad_script_regular)),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GreetingPreview() {
    ShuttleTheme {
        GreetingContent(onNavigateToLogin = {})
    }
}