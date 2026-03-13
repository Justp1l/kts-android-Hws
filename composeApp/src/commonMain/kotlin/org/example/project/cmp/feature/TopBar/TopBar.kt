package org.example.project.cmp.feature.TopBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import org.example.project.theme.ShuttleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: @Composable () -> Unit = { defaultTitle() },
    action: @Composable () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults
        .enterAlwaysScrollBehavior(state = rememberTopAppBarState()),
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ShuttleTheme.colors.background,
            scrolledContainerColor = ShuttleTheme.colors.background,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            title()
        },
        scrollBehavior = scrollBehavior,
        actions = { action() },
    )
}

@Composable
fun defaultTitle() {
    Text(
        text = "SpaceShape",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontFamily = ShuttleTheme.typography.bodyBold.fontFamily
    )
}

