package org.example.project.cmp.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.lifecycle.viewmodel.compose.viewModel
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.try_again
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.TopBar.TopBarWithSearch
import org.example.project.cmp.feature.main.presentation.FiltrationButtons
import org.example.project.cmp.feature.main.presentation.MainAgencyViewModel
import org.example.project.cmp.feature.main.presentation.components.AgenciesPreview
import org.example.project.cmp.feature.main.presentation.components.AgencyItem
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainAgencyScreen(
    viewModel: MainAgencyViewModel = viewModel { MainAgencyViewModel() }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MainAgencyContent(
        isSearchActive = state.isSearchActive,
        makeSearch = viewModel::makeSearch,
        searchQuery = state.searchQuery,
        onQueryChange = viewModel::onQueryChange,
        onQueryClear = viewModel::onQueryClear,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAgencyContent(
    isSearchActive: Boolean,
    makeSearch: () -> Unit,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    isLoading: Boolean,
    error: String?,
    agencies: List<AgencyEntity>,
    getInitialListAgain: () -> Unit,
    onAllButtonClick: () -> Unit,
    onFeatureButtonClick: () -> Unit,
    isAllButtonEnabled: Boolean = true
) {
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
                makeSearch = makeSearch,
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
                onQueryClear = onQueryClear,
                isSearchActive = isSearchActive,
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            Navbar(
                onMainPress = {},
                onHeartPress = {},
                onProfilePress = {},
                scrollBehavior = bottomScrollBehavior
            )
        },
        containerColor = ShuttleTheme.colors.background,
        contentWindowInsets = WindowInsets(),
    ) { paddingValues ->
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
}

@Composable
@Preview
fun MainAgencyPreview() {
    ShuttleTheme {
        MainAgencyContent(
            isSearchActive = false,
            makeSearch = {},
            searchQuery = "",
            onQueryChange = {},
            onQueryClear = {},
            isLoading = false,
            error = "null",
            agencies = AgenciesPreview().agencies,
            getInitialListAgain = {},
            onAllButtonClick = {},
            onFeatureButtonClick = {},
            isAllButtonEnabled = true,
        )
    }
}