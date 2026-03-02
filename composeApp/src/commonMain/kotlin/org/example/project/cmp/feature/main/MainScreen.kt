package org.example.project.cmp.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainScreen() {
    MainContent()
}
@Composable
fun MainContent() {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.background(color = Color.Black).fillMaxSize())
    }
}