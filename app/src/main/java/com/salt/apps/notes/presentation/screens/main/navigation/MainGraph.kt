package com.salt.apps.notes.presentation.screens.main.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.screens.main.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(
    appNavController: NavHostController
) {
    composable(
        route = AppScreen.Main.route,
    ) {
        MainScreen(appNavController = appNavController)
    }
}