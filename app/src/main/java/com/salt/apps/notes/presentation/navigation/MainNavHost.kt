package com.salt.apps.notes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.salt.apps.notes.presentation.components.ViewPagerConfig
import com.salt.apps.notes.presentation.navigation.Screen.Main
import com.salt.apps.notes.presentation.screens.add.navigation.addGraph
import com.salt.apps.notes.presentation.screens.main.navigation.mainGraph

@Composable
fun MainNavHost(
    navController: NavHostController,
    viewPagerConfig: ViewPagerConfig,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = Main.route, modifier = modifier) {
        mainGraph(
            viewPagerConfig = viewPagerConfig
        )
        addGraph(navController = navController)
    }
}

