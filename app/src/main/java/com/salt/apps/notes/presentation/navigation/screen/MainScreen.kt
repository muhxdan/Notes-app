package com.salt.apps.notes.presentation.navigation.screen

import com.salt.apps.notes.R

sealed class MainScreen(
    val route: String,
    val label: String,
    val selectedIconLight: Int,
    val selectedIconDark: Int,
    val unselectedIconLight: Int,
    val unselectedIconDark: Int
) {
    data object Note : MainScreen(
        route = "note_route",
        label = "Note",
        selectedIconLight = R.drawable.ic_light_filled_note,
        selectedIconDark = R.drawable.ic_dark_filled_note,
        unselectedIconLight = R.drawable.ic_light_unselected_note,
        unselectedIconDark = R.drawable.ic_dark_unselected_note,
    )

    data object Task : MainScreen(
        route = "task_route",
        label = "Task",
        selectedIconLight = R.drawable.ic_light_filled_todo,
        selectedIconDark = R.drawable.ic_dark_filled_todo,
        unselectedIconLight = R.drawable.ic_light_unselected_todo,
        unselectedIconDark = R.drawable.ic_dark_unselected_todo
    )

    companion object {
        fun getMainScreen(): List<MainScreen> {
            return listOf(Note, Task)
        }
    }
}