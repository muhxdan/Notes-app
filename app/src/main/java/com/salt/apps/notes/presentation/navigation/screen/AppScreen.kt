package com.salt.apps.notes.presentation.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class AppScreen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    data object Main : AppScreen(route = "main_route")
    data object Add : AppScreen(
        route = "add_route?noteId={noteId}&noteTitle={noteTitle}&noteDescription={noteDescription}&noteDate={noteDate}",
        navArguments = listOf(
            navArgument("noteId") {
                type = NavType.IntType
                defaultValue = 0
            },
            navArgument("noteTitle") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("noteDescription") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("noteDate") {
                type = NavType.StringType
                defaultValue = ""
            },
        )
    )

    data object Settings : AppScreen(route = "settings_route")
}
