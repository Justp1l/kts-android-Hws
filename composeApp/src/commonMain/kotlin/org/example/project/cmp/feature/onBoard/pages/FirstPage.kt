package org.example.project.cmp.feature.onBoard.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.start
import org.example.project.cmp.feature.TopBar.TopBar
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FirstPage() {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = ShuttleTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SpaceShape",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 30.sp,
            fontFamily = ShuttleTheme.typography.bodyBold.fontFamily,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(Res.string.start),
            fontFamily = ShuttleTheme.typography.bodyNormal.fontFamily,
            fontSize = 18.sp,
            color = ShuttleTheme.colors.onBackground
        )
    }
}


@Composable
@Preview
fun PagePrev() {
    ShuttleTheme {
        FirstPage()
    }
}
