package org.example.project.cmp.feature.onBoard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.cmp.feature.onBoard.pages.PagesAggregator
import org.example.project.theme.ShuttleTheme

@Composable

fun Onboarding(onNavigateToLogin: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    Scaffold {
        Box(contentAlignment = Alignment.BottomCenter) {
            HorizontalPager(state = pagerState) { page ->
                PagesAggregator(page,
                    onNavigateToLogin)
            }
            Column {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
@Preview
fun OnboardPreview() {
    ShuttleTheme {
        Onboarding({})
    }
}