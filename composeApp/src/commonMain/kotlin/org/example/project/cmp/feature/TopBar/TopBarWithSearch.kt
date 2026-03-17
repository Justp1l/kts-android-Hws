package org.example.project.cmp.feature.TopBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import org.example.project.theme.ShuttleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithSearch(
    isSearchActive: Boolean,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    makeSearch: () -> Unit
) {
    TopBar(
        title = {
            SearchField(
                isSearchActive = isSearchActive,
                searchQuery = searchQuery,
                onQueryChange = onQueryChange,
                onQueryClear = onQueryClear
            )
        },
        action = { activateSearch(makeSearch) },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun activateSearch(makeSearch: () -> Unit) {
    IconButton(onClick = makeSearch) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = ShuttleTheme.colors.onBackground
        )
    }
}