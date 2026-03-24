package org.example.project.cmp.feature.main.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import kts_hw2.composeapp.generated.resources.unispace_bd
import org.example.project.cmp.app.Destination
import org.example.project.cmp.common.UI.Bars.Navbar
import org.example.project.cmp.common.UI.Bars.TopBar.SearchField
import org.example.project.cmp.common.UI.Bars.TopBar.TopBar
import org.example.project.cmp.feature.main.domain.MainViewModel
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLaunchesPressed: () -> Unit,
    onRocketsPressed: () -> Unit,
    onAgenciesPressed: () -> Unit,
    onMainPress: () -> Unit,
    onHeartPress: () -> Unit,
    onProfilePress: () -> Unit
) {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val behavior =
        BottomAppBarDefaults.exitAlwaysScrollBehavior(state = rememberBottomAppBarState())

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            Navbar(
                onMainPress = onMainPress,
                onHeartPress = onHeartPress,
                onProfilePress = onProfilePress,
                scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MainContent(
                query = state.searchQuery,
                onQueryChange = viewModel::onQueryChange,
                queryClear = viewModel::queryClear,
                onLaunchesPressed = onLaunchesPressed,
                onRocketsPressed = onRocketsPressed,
                onAgenciesPressed = onAgenciesPressed
            )
        }
    }
}

@Composable
fun MainContent(
    query: String,
    onQueryChange: (String) -> Unit,
    queryClear: () -> Unit,
    onLaunchesPressed: () -> Unit,
    onRocketsPressed: () -> Unit,
    onAgenciesPressed: () -> Unit
) {
    Column {


        SearchField(
            isSearchActive = true,
            searchQuery = query,
            onQueryChange = onQueryChange,
            onQueryClear = queryClear
        )
        Spacer(Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(Res.drawable.astronaut),
                contentDescription = "Launches image",
                modifier = Modifier,
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.size(25.dp))
            Button(
                onClick = onAgenciesPressed,
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShuttleTheme.colors.content
                )
            ) {
                Text(
                    text = "Launches",
                    fontSize = 20.sp,
                    fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
                    modifier = Modifier.padding(vertical = 60.dp),
                )
            }
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.width(200.dp),
                onClick = onRocketsPressed,
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShuttleTheme.colors.content
                )
            ) {
                Text(
                    text = "Rockets",
                    fontSize = 20.sp,
                    fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
                    modifier = Modifier.padding(vertical = 60.dp),
                )
            }
        }
        Spacer(Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(180.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(Res.drawable.astronaut),
                contentDescription = "Agencies image",
                modifier = Modifier,
                contentScale = ContentScale.Fit
            )
            Spacer(Modifier.size(25.dp))
            Button(
                onClick = onAgenciesPressed,
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ShuttleTheme.colors.content
                )
            ) {
                Text(
                    text = "Agencies",
                    fontSize = 20.sp,
                    fontFamily = ShuttleTheme.typography.bodyMedium.fontFamily,
                    modifier = Modifier.padding(vertical = 60.dp),
                )
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreenPreview() {
    ShuttleTheme {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { Navbar(
                onMainPress = {},
                onHeartPress = {},
                onProfilePress = {},
                scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
            ) }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                MainContent(
                    query = "",
                    onQueryChange = {},
                    queryClear = {},
                    onLaunchesPressed = {},
                    onRocketsPressed = {  },
                    onAgenciesPressed = {  }
                )
            }
        }

    }
}