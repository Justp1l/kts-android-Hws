package org.example.spaceShape.cmp.feature.main.agencies.UI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.try_again
import org.example.spaceShape.cmp.common.UI.Bars.Navbar
import org.example.spaceShape.cmp.common.UI.Bars.TopBar.TopBarWithSearch
import org.example.spaceShape.cmp.feature.main.UI.FiltrationButtons
import org.example.spaceShape.cmp.feature.main.agencies.data.database.AgencyEntity
import org.example.spaceShape.cmp.feature.main.agencies.domain.MainAgencyViewModel
import org.example.spaceShape.cmp.feature.main.domain.components.AgenciesPreview
import org.example.spaceShape.theme.ShuttleTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAgencyScreen(
    onMainPress: () -> Unit,
    onHeartPress: () -> Unit,
    onProfilePress: () -> Unit
) {
    val viewModel: MainAgencyViewModel = koinViewModel<MainAgencyViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val bottomScrollBehavior =
        BottomAppBarDefaults.exitAlwaysScrollBehavior(state = rememberBottomAppBarState())
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(bottomScrollBehavior.nestedScrollConnection)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBarWithSearch(
                makeSearch = viewModel::makeSearch,
                searchQuery = state.searchQuery,
                onQueryChange = viewModel::onQueryChange,
                onQueryClear = viewModel::onQueryClear,
                isSearchActive = state.isSearchActive,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            Navbar(
                onMainPress = onMainPress,
                onHeartPress = onHeartPress,
                onProfilePress = onProfilePress,
                scrollBehavior = bottomScrollBehavior
            )
        },
        containerColor = ShuttleTheme.colors.background,
    ) { paddingValues ->
        MainAgencyContent(
            paddingValues = paddingValues,
            isLoading = state.isLoading,
            error = state.error,
            //agencies = AgenciesPreview().agencies,  // test
            agencies = state.agencies,            // Api interaction
            getInitialListAgain = viewModel::loadAgency,
            onAllButtonClick = viewModel::onAllClick,
            onFeatureButtonClick = viewModel::onFeatureClick,
            isAllButtonEnabled = state.isAllButtonEnabled,
        )
    }
}

@Composable
fun MainAgencyContent(
    paddingValues: PaddingValues,
    isLoading: Boolean,
    error: String?,
    agencies: List<AgencyEntity>,
    getInitialListAgain: () -> Unit,
    onAllButtonClick: () -> Unit,
    onFeatureButtonClick: () -> Unit,
    isAllButtonEnabled: Boolean = true
) {

    Box(modifier = Modifier.padding(paddingValues)) {
        Column {
            if (isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    CircularProgressIndicator()
                }
            }
            if (error != null) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Spacer(Modifier.size(15.dp))
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                    Button(
                        onClick = getInitialListAgain,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ShuttleTheme.colors.container,
                            contentColor = ShuttleTheme.colors.onContainer
                        ),
                    ) {
                        Text(
                            text = stringResource(Res.string.try_again),
                            fontFamily = ShuttleTheme.typography.bodyBold.fontFamily
                        )
                    }
                }
            } else if (!isLoading) {
                Spacer(Modifier.size(5.dp))
                FiltrationButtons(
                    onAllButtonClick = onAllButtonClick,
                    onFeatureButtonClick = onFeatureButtonClick,
                    isAllButtonEnabled = isAllButtonEnabled
                )
            }
            Spacer(Modifier.padding(5.dp))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(
                    agencies.size,
                ) { index ->
                    AgencyItem(agencies[index])
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainAgencyPreview() {
    ShuttleTheme{
        Scaffold(
            topBar = {
                TopBarWithSearch(
                    makeSearch = {},
                    searchQuery = "",
                    onQueryChange = {},
                    onQueryClear = {},
                    isSearchActive = false,
                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                )
            },
            bottomBar = {
                Navbar(
                    onMainPress = {},
                    onHeartPress = {},
                    onProfilePress = {},
                    scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
                )
            },
            containerColor = ShuttleTheme.colors.background,
        ) { paddingValues ->
            MainAgencyContent(
                paddingValues = paddingValues,
                isLoading = false,
                error = "null",
                agencies = AgenciesPreview().agencies,
                getInitialListAgain = {},
                onAllButtonClick = {},
                onFeatureButtonClick = {},
            )
        }

}
}