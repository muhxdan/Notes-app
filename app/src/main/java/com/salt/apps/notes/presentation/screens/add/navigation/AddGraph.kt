package com.salt.apps.notes.presentation.screens.add.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.screens.add.AddScreen

fun NavGraphBuilder.addGraph(
    appNavController: NavHostController
) {
    composable(
        route = AppScreen.Add.route,
        arguments = AppScreen.Add.navArguments,
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
            val noteId = it.arguments?.getInt("noteId") ?: 0
            val noteTitle = it.arguments?.getString("noteTitle") ?: ""
            val noteDescription = it.arguments?.getString("noteDescription") ?: ""
            val noteDate = it.arguments?.getString("noteDate") ?: ""

            val note = NoteEntity(
                id = noteId,
                title = noteTitle,
                description = noteDescription,
                date = noteDate
            )
            AddScreen(appNavController = appNavController, note = note)
        }
    )
}