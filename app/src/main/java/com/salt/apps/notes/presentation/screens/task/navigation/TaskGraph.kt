package com.salt.apps.notes.presentation.screens.task.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.salt.apps.notes.presentation.navigation.screen.MainScreen
import com.salt.apps.notes.presentation.screens.task.TaskScreen

fun NavGraphBuilder.taskGraph(
) {
    composable(
        route = MainScreen.Task.route,
        enterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
    ) {
        TaskScreen()
    }
}