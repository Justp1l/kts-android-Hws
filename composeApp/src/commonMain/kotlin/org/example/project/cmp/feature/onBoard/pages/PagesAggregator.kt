package org.example.project.cmp.feature.onBoard.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.theme.ShuttleTheme

@Composable
fun PagesAggregator(
    page: Int,
    onNavigateToLogin: () -> Unit
) {
    when (page) {
        0 -> FirstPage()
        1 -> FirstPage()
        2 -> LastPage(onNavigateToLogin)
    }
}

@Composable
@Preview
fun PreviewP() {
    ShuttleTheme {
        PagesAggregator(0, {})
    }
}
