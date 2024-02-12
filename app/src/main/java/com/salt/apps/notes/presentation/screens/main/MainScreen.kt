package com.salt.apps.notes.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salt.apps.notes.presentation.navigation.navhost.MainNavHost
import com.salt.apps.notes.presentation.navigation.screen.AppScreen
import com.salt.apps.notes.presentation.navigation.screen.MainScreen
import com.salt.apps.notes.presentation.screens.main.components.BottomNavigationBar
import com.salt.apps.notes.presentation.screens.main.components.FloatingActionButton
import com.salt.apps.notes.presentation.screens.main.components.MainTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appNavController: NavHostController,
    mainNavController: NavHostController = rememberNavController(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    ),
    scrollOnlyInTopBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    ),
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val mainScreen: List<MainScreen> = MainScreen.getMainScreen()
    val backStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .nestedScroll(scrollOnlyInTopBehavior.nestedScrollConnection),
        topBar = {
            MainTopBar(
                scrollBehavior = scrollBehavior,
                scrollOnlyInTopBehavior = scrollOnlyInTopBehavior,
                currentScreen = currentScreen,
                mainViewModel = mainViewModel,
                onSettingsClicked = {
                    appNavController.navigate(AppScreen.Settings.route)

                })
        },
        bottomBar = {
            AnimatedVisibility(visible = !mainViewModel.screenState.value.isSearchActive) {
                BottomNavigationBar(
                    navController = mainNavController,
                    screens = mainScreen,
                )
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = !mainViewModel.screenState.value.isSearchActive) {
                FloatingActionButton(
                    onClick = {
                        appNavController.navigate(AppScreen.Add.route)
                    },
                )
            }
        },
    ) { innerPadding ->
        MainNavHost(
            appNavController = appNavController,
            mainNavController = mainNavController,
            mainViewModel = mainViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}