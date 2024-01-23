package com.salt.apps.notes.presentation.screens.add.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.notes.presentation.navigation.Screen
import com.salt.apps.notes.presentation.screens.add.AddScreen

fun NavGraphBuilder.addGraph() {
    composable(
        route = Screen.Add.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Up, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Down, tween(500)
            )
        },
        popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        content = {
            AddScreen()
        }
    )
}