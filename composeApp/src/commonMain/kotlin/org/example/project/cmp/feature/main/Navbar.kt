package org.example.project.cmp.feature.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.cosm_icon
import kts_hw2.composeapp.generated.resources.rocket_icon
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Navbar(
    onMainPress: () -> Unit,
    onHeartPress: () -> Unit,
    onProfilePress: () -> Unit,
    scrollBehavior: BottomAppBarScrollBehavior
) {
    BottomAppBar(
        contentColor = ShuttleTheme.colors.content,
        containerColor = ShuttleTheme.colors.background,
        modifier = Modifier
            .clip(RoundedCornerShape(topStartPercent = 40, topEndPercent = 40)),
        scrollBehavior = scrollBehavior
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = onMainPress
            ) {
                Icon(
                    painter = painterResource(Res.drawable.rocket_icon),
                    tint = ShuttleTheme.colors.onBackground,
                    contentDescription = "Main",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(
                onClick = onHeartPress
            ) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    tint = ShuttleTheme.colors.onBackground,
                    contentDescription = "Favourite",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(
                onClick = onProfilePress
            ) {
                Icon(
                    painter = painterResource(Res.drawable.cosm_icon),
                    tint = ShuttleTheme.colors.onBackground,
                    contentDescription = "Profile",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun NavbarPreview() {
    ShuttleTheme {
        Navbar(
            onMainPress = {},
            onHeartPress = {},
            onProfilePress = {},
            scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
        )
    }
}