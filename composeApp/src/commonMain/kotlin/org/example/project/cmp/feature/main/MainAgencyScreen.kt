package org.example.project.cmp.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import kts_hw2.composeapp.generated.resources.try_again
import org.example.project.cmp.common.storage.database.AgencyEntity
import org.example.project.cmp.feature.TopBar.TopBar
import org.example.project.cmp.feature.TopBar.TopBarWithSearch
import org.example.project.cmp.feature.main.data.Objects.Agency.RemoteAgency
import org.example.project.cmp.feature.main.presentation.components.AgenciesPreview
import org.example.project.cmp.feature.main.presentation.MainAgencyViewModel
import org.example.project.cmp.feature.main.presentation.components.AgencyItem
import org.example.project.theme.ShuttleTheme
import org.jetbrains.compose.resources.painterResource
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
        getInitialListAgain = viewModel::loadAgency
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
    getInitialListAgain: () -> Unit
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.fillMaxSize()
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
        containerColor = ShuttleTheme.colors.background,
        contentWindowInsets = WindowInsets(),
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column {
                Spacer(modifier = Modifier.padding(10.dp))
                if (isLoading) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error?.let { e ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Spacer(Modifier.size(15.dp))
                        Text(
                            text = e,
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

                }
                Spacer(Modifier.padding(15.dp))
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
            getInitialListAgain = {}
        )
    }
}