package org.example.project.cmp.feature.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun AgencyItem(agency: AgencyEntity) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 15.dp)
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            Box(
                modifier = Modifier
                    .width(160.dp)
                    .height(40.dp)
                    .background(
                        brush = Brush.verticalGradient(ShuttleTheme.colors.gradient),
                        shape = RoundedCornerShape(topStartPercent = 20, topEndPercent = 20)
                    )
            )
            AsyncImage(
                contentScale = ContentScale.Fit,
                clipToBounds = true,
                contentDescription = "pic",
                model = agency.logo,
                placeholder = painterResource(Res.drawable.astronaut),
                modifier = Modifier.background(
                    shape = RoundedCornerShape(15),
                    color = Color.White,
                )
                    .clip(RoundedCornerShape(15))
                    .size(90.dp)
                    .zIndex(1f)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = ShuttleTheme.colors.content,
                    shape = RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20)
                )
                .padding(horizontal = 10.dp)
                .width(140.dp)
        ) {
            Column(
                modifier = Modifier.padding(bottom = 3.dp)
                    .align(Alignment.BottomCenter)
                    .padding(top = 10.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = agency.name,
                    textAlign = TextAlign.Center,
                    fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
                    color = Color.White,
                    fontSize = 15.sp,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = agency.ceo ?: "",
                    textAlign = TextAlign.Center,
                    fontFamily = ShuttleTheme.typography.bodyBold.fontFamily,
                    color = Color.White,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Composable
@Preview
fun AgencyItemPreview() {
    ShuttleTheme {
        AgencyItem(agency = AgenciesPreview().agencies[0])
    }
}