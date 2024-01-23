package com.salt.apps.notes.presentation.navigation

sealed class Screen(
    val route: String,
) {
    data object Main : Screen("main")
    data object Add : Screen("add")
}