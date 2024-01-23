package com.salt.apps.notes.presentation.screens.main.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.notes.presentation.components.ViewPager
import com.salt.apps.notes.presentation.components.ViewPagerConfig
import com.salt.apps.notes.presentation.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.mainGraph(
    viewPagerConfig: ViewPagerConfig
) {
    composable(
        route = Screen.Main.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, tween(500)
            )
        },
        content = {
            ViewPager(
                viewPagerConfig = viewPagerConfig
            )
        }
    )
}