package org.example.project.cmp.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import kts_hw2.composeapp.generated.resources.unispace_bd
import org.example.project.cmp.feature.main.data.Objects.Rocket.RemoteCard
import org.example.project.cmp.feature.main.presentation.MainViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
//@Preview
fun MainScreen(viewModel: MainViewModel = viewModel { MainViewModel() }) {
    val rockets by viewModel.rockets.collectAsState()
    MainContent(rockets)
}


@Composable
fun MainContent(rockets: List<RemoteCard>) {
    Scaffold {
        Box(
            modifier = Modifier
                .background(color = Color.Black)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            LazyColumn(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                if (rockets.isNotEmpty()) {
                    items(Int.MAX_VALUE) { index ->
                        val item = rockets[index % rockets.size]
//                        lazyElement(
//                            id = item.id,
//                            name = item.,
//                            date = item.creationDate,
//                            image = item.image
//                        )
                    }
                }

            }
        }
    }
}

@Composable
fun lazyElement(
    id: Long,
    name: String,
    date: Int,
    image: DrawableResource
) {
    Box(
        modifier = Modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(15),
        )
            .padding(12.dp)
    ) {
        Text(
            text = id.toString(),
            modifier = Modifier.padding(start = 3.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painterResource(image),
                contentDescription = "Rocket",
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = name,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(Res.font.unispace_bd)),
                color = Color.Black
            )
            Text(
                text = "Year of first launch is \n $date",
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun ElementPreview() {
    MaterialTheme {
        lazyElement(
            1,
            "Apollo",
            1,
            Res.drawable.astronaut
        )
    }
}