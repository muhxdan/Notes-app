package com.salt.apps.notes.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.salt.apps.notes.presentation.screens.note.NoteScreen
import com.salt.apps.notes.presentation.screens.todo.TodoScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    viewPagerConfig: ViewPagerConfig,
    selectedTab: Int = viewPagerConfig.selectedTab,
    pagerState: PagerState = viewPagerConfig.pagerState,
    onTabSelected: (Int) -> Unit = viewPagerConfig.onTabSelected
) {

    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage) {
        onTabSelected(pagerState.currentPage)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(
                60.dp,
                alignment = Alignment.CenterHorizontally
            )

        ) {
            for (tabIndex in 0 until pagerState.pageCount) {
                TabIcon(
                    isSelectedTab = selectedTab == tabIndex,
                    selectedTab = tabIndex,
                    clickIcon = { onTabSelected(tabIndex) }
                )
            }
        }

        HorizontalPager(state = pagerState) { currentPage ->
            when (currentPage) {
                0 -> NoteScreen()
                1 -> TodoScreen()
            }
        }
    }
}