package org.example.project.cmp.feature.main.presentation

import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.Feature
import org.example.project.theme.ShuttleTheme

@Composable
fun FiltrationButtons(
    onAllButtonClick: () -> Unit,
    onFeatureButtonClick: () -> Unit,
    isAllButtonEnabled: Boolean = true
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        AllButton(onAllButtonClick, isAllButtonEnabled)
        FeatureButton(onFeatureButtonClick, !isAllButtonEnabled)
    }
}

@Composable
fun AllButton(
    onAllButtonClick: () -> Unit,
    isAllButtonEnabled: Boolean = true
) {
    val buttonColors = if (!isAllButtonEnabled)
        ShuttleTheme.colors.activeButtonColours else
        ShuttleTheme.colors.inactiveButtonColours

    val size by animateSizeAsState(
        if (!isAllButtonEnabled)
            Size(
                width = 150f,
                height = 39f
            ) else Size(
            width = 125f,
            height = 39f
        ),
        label = "SizeAnimation"
    )

    Button(
        onClick = onAllButtonClick,
        modifier = Modifier
            .background(
                color = ShuttleTheme.colors.onBackground,
                shape = RoundedCornerShape(topStartPercent = 40, bottomStartPercent = 40)
            )
            .size(
                width = size.width.dp,
                height = size.height.dp
            )
            .padding(
                bottom = 5.dp,
            )
            .offset(x = 5.dp),
        shape = RoundedCornerShape(
            topStartPercent = 40, bottomStartPercent = 40,
        ),
        colors = buttonColors
    ) {
        Text(
            text = "All",
            fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
            fontSize = 14.sp
        )
    }
}

@Composable
fun FeatureButton(
    onFeatureButtonClick: () -> Unit,
    isFeatureButtonEnabled: Boolean = false
) {
    val buttonColors = if (!isFeatureButtonEnabled)
        ShuttleTheme.colors.activeButtonColours else
        ShuttleTheme.colors.inactiveButtonColours

    val size by animateSizeAsState(
        if (!isFeatureButtonEnabled)
            Size(
                width = 150f,
                height = 39f
            ) else Size(
            width = 125f,
            height = 39f
        ),
        label = "SizeAnimation"
    )

    Button(
        onClick = onFeatureButtonClick ,
        modifier = Modifier
            .background(
                color = ShuttleTheme.colors.onBackground,
                shape = RoundedCornerShape(
                    topEndPercent = 40,
                    bottomEndPercent = 40
                )
            )
            .size(
                width = size.width.dp,
                height = size.height.dp
            )
            .padding(
                bottom = 5.dp,
            )
            .offset(x = (-5).dp)
        ,
        shape = RoundedCornerShape(
            topEndPercent = 40, bottomEndPercent = 40
        ),
        colors = buttonColors
    ) {
        Text(
            text = "Famed",
            fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
            //fontSize = 14.sp
        )
    }
}

@Composable
@Preview
fun AllButtonPreview() {
    ShuttleTheme {
        FiltrationButtons(
            {},
            {},
            true
        )
    }
}