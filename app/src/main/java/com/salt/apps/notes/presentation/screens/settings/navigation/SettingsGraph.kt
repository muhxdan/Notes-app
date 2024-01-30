package com.salt.apps.notes.presentation.screens.settings.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.screens.settings.SettingsScreen

fun NavGraphBuilder.settingsGraph(
) {
    composable(
        route = AppScreen.Settings.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },

        content = {
            SettingsScreen()
        }
    )
}