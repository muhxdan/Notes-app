package com.salt.apps.notes.presentation.screens.main

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salt.apps.notes.presentation.components.TopAppBar
import com.salt.apps.notes.presentation.components.ViewPagerConfig
import com.salt.apps.notes.presentation.navigation.MainNavHost
import com.salt.apps.notes.presentation.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
    val pagerState = rememberPagerState(initialPage = 0) { 2 }
    var selectedTab by remember { mutableIntStateOf(pagerState.currentPage) }

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Main.route) TopAppBar(
                currentScreen = currentRoute,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
            )
        },
        floatingActionButton = {
            if (currentRoute == Screen.Main.route) Box(
                modifier = Modifier.padding(
                    end = 20.dp,
                    bottom = 30.dp
                )
            ) {
                FloatingActionButton(
                    onClick = {
                        if (selectedTab == 0) {
                            navController.navigate(Screen.Add.route)
                        } else {
                            Log.i("MainScreen", "Add todo clicked")
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        Icons.Filled.Add,
                        tint = MaterialTheme.colorScheme.background,
                        contentDescription = "Add",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { innerPadding ->
        MainNavHost(
            navController = navController,
            viewPagerConfig = ViewPagerConfig(
                selectedTab = selectedTab,
                pagerState = pagerState,
                onTabSelected = { newTab ->
                    selectedTab = newTab
                }
            ),
            modifier = Modifier.padding(innerPadding)
        )
    }
}