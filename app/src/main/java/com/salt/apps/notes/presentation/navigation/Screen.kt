package com.salt.apps.notes.presentation.navigation

sealed class Screen(
    val route: String,
) {
    data object Home : Screen("home")
    data object Add : Screen("add")
}