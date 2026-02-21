package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.Image
import coil3.compose.AsyncImage
import org.example.project.R

@Composable
fun GreetingScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    // var showContent by remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                AsyncImage(
                    modifier = Modifier,
                    contentScale = ContentScale.Crop,
                    model = "https://s0.rbk.ru/v6_top_pics/media/img/7/75/347472273420757.jpeg",
                    contentDescription = "LegendaryMem",
                    placeholder = painterResource(R.drawable.img_1),
                    error = painterResource(R.drawable.img)
                )
                
                Column(modifier = Modifier.padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = onNavigateToLogin,
                        content = {
                            Text("Let's get started")
                        }
                    )
                    Spacer(Modifier.padding(10.dp))
                    Text(
                        modifier = Modifier,
                        text = "Данное приложение было сделано студентом Вединым Дмитрием специально для курса \"Начинающий Android-разработчик\" от KTS",
                        fontFamily = FontFamily(Font(R.font.bad_script_regular)),
                        fontSize = 20.sp

                    )

                }

            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewGreet() {
    MaterialTheme {
        GreetingScreen(
            onNavigateToLogin = {}
        )
    }
}