package com.salt.apps.notes.presentation.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.salt.apps.notes.presentation.navigation.screen.MainScreen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    screens: List<MainScreen>,
) {
    NavigationBar(
        modifier = Modifier.padding(horizontal = 30.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        tonalElevation = 0.dp,
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        screens.forEach { screen ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(screen)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navigateToTopLevelDestination(navController, screen)
                },
                icon = {
                    val resource = when {

                        isSystemInDarkTheme() -> {
                            if (selected) {
                                screen.selectedIconDark
                            } else {
                                screen.unselectedIconDark
                            }
                        }

                        else -> {
                            if (selected) {
                                screen.selectedIconLight
                            } else {
                                screen.unselectedIconLight
                            }
                        }
                    }

                    Image(
                        painter = painterResource(id = resource),
                        contentDescription = "Tab Icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .size(22.dp),
                    )
                },
                label = { Text(screen.label) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = if (isSystemInDarkTheme()) Color(0xff686868) else Color(
                        0xFF8F8F8F
                    ),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(mainScreen: MainScreen) =
    this?.hierarchy?.any {
        it.route?.contains(mainScreen.route, true) ?: false
    } ?: false

private fun navigateToTopLevelDestination(
    navController: NavHostController, mainScreen: MainScreen
) {
    val topLevelNavOptions = navOptions {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (mainScreen) {
        MainScreen.Note -> navController.navigate(mainScreen.route, topLevelNavOptions)
        MainScreen.Task -> navController.navigate(mainScreen.route, topLevelNavOptions)
    }
}