package org.example.project.cmp.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                resource = Res.drawable.astronaut
            ),
            contentDescription = "Astronaut and Mars",
            modifier = Modifier
                .size(width = 190.dp, height = 250.dp)
        )
        Text(
            fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
            color = Color.Red,
            text = "Sign in",
            fontSize = 35.sp,
        )
    }
}