package com.salt.apps.notes.presentation.screens.add

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.salt.apps.notes.presentation.components.TextField
import com.salt.apps.notes.presentation.components.TopAppBar

@Composable
fun AddScreen(
    navController: NavHostController,
    addViewModel: AddViewModel = hiltViewModel(),
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val currentTime by addViewModel.currentTime.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = {
                navController.navigateUp()
            },
            isActionsActive = title.trim().isNotEmpty() || description.trim().isNotEmpty(),
            isActionsClick = {
                addViewModel.insertNote(
                    title = title,
                    description = description
                )
                navController.navigateUp()
            }
        )
        TextField(title = title, placeHolder = "Title", onValueChange = { title = it })
        Text(
            text = currentTime,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (isSystemInDarkTheme()) Color(0xff686868) else Color(0xFF8F8F8F)
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            title = description,
            placeHolder = "Description",
            onValueChange = { description = it })
    }
}

