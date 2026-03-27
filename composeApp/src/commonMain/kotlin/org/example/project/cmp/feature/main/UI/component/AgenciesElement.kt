package org.example.project.cmp.feature.main.UI.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.main_astronaut
import kts_hw2.composeapp.generated.resources.mars
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun AgencyElement(onAgenciesClick: () -> Unit) {
    Box() {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            Box() {
                Button(
                    onClick = onAgenciesClick,
                    modifier = Modifier
                        .width(300.dp),

                    shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ShuttleTheme.colors.container
                    )
                ) {
                    Image(
                        painter = painterResource(Res.drawable.mars),
                        contentDescription = "Mars",
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Button(
                onClick = onAgenciesClick,
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
                    text = "Agencies",
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.size(190.dp),
                onClick = onAgenciesClick,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.main_astronaut),
                    tint = ShuttleTheme.colors.content,
                    contentDescription = "Main Astronaut"
                )
            }
        }
    }
}


@Composable
@Preview
fun AgencyElementPreview() {
    ShuttleTheme {
        AgencyElement(
            onAgenciesClick = {}
        )
    }
}