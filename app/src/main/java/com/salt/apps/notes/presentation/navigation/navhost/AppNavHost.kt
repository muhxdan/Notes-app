package com.salt.apps.notes.presentation.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.screens.add.navigation.addGraph
import com.salt.apps.notes.presentation.screens.main.navigation.mainGraph
import com.salt.apps.notes.presentation.screens.settings.navigation.settingsGraph

@Composable
fun AppNavHost(appNavController: NavHostController) {
    NavHost(
        navController = appNavController,
        startDestination = AppScreen.Main.route,
    ) {
        mainGraph(appNavController = appNavController)
        addGraph(appNavController = appNavController)
        settingsGraph()
    }
}