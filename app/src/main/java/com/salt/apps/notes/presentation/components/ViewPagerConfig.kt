package com.salt.apps.notes.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState

data class ViewPagerConfig @OptIn(ExperimentalFoundationApi::class) constructor(
    val pagerState: PagerState,
    val selectedTab: Int,
    val onTabSelected: (Int) -> Unit,
)