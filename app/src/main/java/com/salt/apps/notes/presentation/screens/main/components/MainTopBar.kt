package com.salt.apps.notes.presentation.screens.main.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    var isSelected by remember { mutableStateOf(false) }

    LaunchedEffect(mainViewModel.screenState.value.selectedNotes) {
        isSelected = mainViewModel.screenState.value.selectedNotes.isNotEmpty()
    }

    val backgroundColor by animateColorAsState(
        if (mainViewModel.screenState.value.selectedNotes.isNotEmpty()) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background,
        animationSpec = tween(200), label = "",
    )

    SideEffect {
        systemUiController.setStatusBarColor(backgroundColor)
    }

    Column {
        MediumTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = backgroundColor,
            ),
            title = {
                Text(
                    if (isSelected) {
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
                    IconButton(onClick = mainViewModel::selectAllNotes) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_check_all),
                            contentDescription = null
                        )
                    }
                } else {
                    IconButton(onClick = onSettingsClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                }
            }, scrollBehavior = scrollOnlyInTopBehavior,
            navigationIcon = {
                if (isSelected) {
                    IconButton(
                        onClick = mainViewModel::clearSelectedNote,
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                            modifier = Modifier.size(17.dp)
                        )
                    }
                }
            }
        )
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title = { Text(text = "Search bar") },
            scrollBehavior = scrollBehavior,
        )
    }
}
