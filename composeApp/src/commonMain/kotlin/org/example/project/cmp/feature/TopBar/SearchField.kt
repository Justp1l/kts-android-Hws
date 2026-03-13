package org.example.project.cmp.feature.TopBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchField(
    isSearchActive: Boolean,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
) {
    if (!isSearchActive) {
        defaultTitle()
    }

    AnimatedVisibility(
        visible = isSearchActive,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        Field(
            searchQuery = searchQuery,
            onQueryChange = onQueryChange,
            onQueryClear = onQueryClear
        )
    }
}

@Composable
fun Field(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onQueryClear: () -> Unit,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onQueryChange,
        singleLine = true,
        placeholder = { Text(text = "Search") },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(40))
            .padding()
            .clip(RoundedCornerShape(40)),
        leadingIcon = {
            IconButton(
                onClick = onQueryClear,
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    tint = Color.Black,
                    contentDescription = "Clear sign"
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        )
    )
}