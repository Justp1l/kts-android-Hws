package org.example.spaceShape.cmp.feature.main.UI.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.aakira.napier.Napier
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.main_rocket
import kts_hw2.composeapp.generated.resources.moon
import org.example.spaceShape.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun RocketElement(onRocketClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = onRocketClick,
                modifier = Modifier
                    .width(300.dp),
                shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShuttleTheme.colors.container
                )
            ) {
                Image(
                    painter = painterResource(Res.drawable.moon),
                    contentDescription = "Mars",
                    contentScale = ContentScale.Fit
                )
            }
            Button(
                onClick = onRocketClick,
                shape = RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20),
                modifier = Modifier.width(300.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShuttleTheme.colors.container
                )
            ) {
                Text(
                    color = ShuttleTheme.colors.onContainer,
                    fontSize = 18.sp,
                    fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
                    text = "Spacecrafts",
                )
            }
        }
        Button(
            modifier = Modifier
                .rotate(345f)
                .offset(x = (-25).dp),
            onClick = {
                Napier.e("Test", tag = "Crash test")
                throw RuntimeException("Test")

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RectangleShape
        ) {
            Icon(
                modifier = Modifier.height(120.dp),
                painter = painterResource(Res.drawable.main_rocket),
                tint = ShuttleTheme.colors.content,
                contentDescription = "Main Spacecrafts",
            )
        }
    }
}

@Composable
@Preview
fun RocketElementPreview() {
    ShuttleTheme {
        RocketElement(
            onRocketClick = {}
        )
    }
}