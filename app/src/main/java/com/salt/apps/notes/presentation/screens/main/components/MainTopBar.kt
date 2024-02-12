package com.salt.apps.notes.presentation.screens.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.salt.apps.notes.R
import com.salt.apps.notes.presentation.navigation.screen.MainScreen
import com.salt.apps.notes.presentation.screens.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    scrollOnlyInTopBehavior: TopAppBarScrollBehavior,
    currentScreen: String?,
    onSettingsClicked: () -> Unit,
    mainViewModel: MainViewModel,
) {
    val systemUiController = rememberSystemUiController()
    val isSelected = mainViewModel.screenState.value.isAppBarOpened

    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background
    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
    }

    Column {
        AnimatedVisibility(visible = !mainViewModel.screenState.value.isSearchActive) {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    scrolledContainerColor = backgroundColor
                ),
                title = {
                    Text(
                        text = if (isSelected) {
                            if (mainViewModel.screenState.value.selectedNotes.isEmpty()) {
                                stringResource(R.string.title_notes)
                            } else {
                                "${mainViewModel.screenState.value.selectedNotes.size} items selected"
                            }
                        } else {
                            if (currentScreen == MainScreen.Note.route) stringResource(R.string.title_notes) else stringResource(
                                R.string.title_tasks
                            )
                        }
                    )
                },
                actions = {
                    if (isSelected) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomIconButton(
                                onClick = mainViewModel::deleteSelectedNotes,
                                icon = R.drawable.ic_trash
                            )
                            CustomIconButton(
                                onClick = mainViewModel::toggleAllNotes,
                                icon = R.drawable.ic_check_all
                            )
                        }
                    } else {
                        CustomIconButton(onClick = onSettingsClicked, icon = R.drawable.ic_settings)
                    }
                },
                scrollBehavior = scrollOnlyInTopBehavior,
                navigationIcon = {
                    if (isSelected) {
                        CustomIconButton(
                            onClick = mainViewModel::clearSelectedNotes,
                            icon = R.drawable.ic_close,
                            size = 16.dp
                        )
                    }
                },
            )
        }
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            title = {
                CustomSearchBar(mainViewModel)
            },
            scrollBehavior = scrollBehavior,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Composable
fun CustomIconButton(onClick: () -> Unit, icon: Int, size: Dp = 18.dp) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
}