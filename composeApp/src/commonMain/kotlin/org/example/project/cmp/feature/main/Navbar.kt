package org.example.project.cmp.feature.main

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.theme.ShuttleTheme

@Composable
fun Navbar() {
    BottomAppBar {

    }
}
@Composable
@Preview
fun NavbarPreview() {
    ShuttleTheme{
        Navbar()
    }
}