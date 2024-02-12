package com.salt.apps.notes.presentation.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.salt.apps.notes.presentation.navigation.screen.MainScreen
import com.salt.apps.notes.presentation.screens.main.MainViewModel
import com.salt.apps.notes.presentation.screens.note.navigation.noteGraph
import com.salt.apps.notes.presentation.screens.task.navigation.taskGraph

@Composable
fun MainNavHost(
    appNavController: NavHostController,
    mainViewModel: MainViewModel,
    modifier: Modifier = Modifier,
    mainNavController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = mainNavController,
        startDestination = MainScreen.Note.route,
        modifier = modifier,
    ) {
        noteGraph(appNavController = appNavController, mainViewModel = mainViewModel)
        taskGraph()
    }
}