package com.salt.apps.notes.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainScreenState(
    val getAllNotes: Set<Int> = emptySet(),
    val selectedNotes: Set<Int> = emptySet(),
    val isAppBarOpened: Boolean = false,
    val isSearchActive: Boolean = false,
)

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _screenState = mutableStateOf(MainScreenState())
    val screenState: State<MainScreenState> = _screenState

    fun toggleSelectedNoteCard(noteId: Int) {
        val selectedNotes = screenState.value.selectedNotes
        _screenState.value = screenState.value.copy(
            selectedNotes = if (selectedNotes.contains(noteId)) selectedNotes.minus(noteId)
            else selectedNotes.plus(noteId)
        )
        updateAppBarVisibility()
    }

    fun clearSelectedNotes() {
        _screenState.value = screenState.value.copy(
            selectedNotes = setOf(),
            isAppBarOpened = false,
        )
    }

    fun getAllSelectedNotes(notes: List<NoteEntity>) {
        val selectedNotes = notes.map { it.id }.toSet()
        _screenState.value = screenState.value.copy(
            getAllNotes = selectedNotes,
        )
    }

    fun toggleAllNotes() {
        val selectedNotes = screenState.value.selectedNotes
        val allNotes = screenState.value.getAllNotes
        val newSelectedNotes = if (selectedNotes.size == allNotes.size) {
            setOf()
        } else {
            allNotes
        }

        _screenState.value = screenState.value.copy(
            selectedNotes = newSelectedNotes
        )
    }

    private fun updateAppBarVisibility() {
        _screenState.value = screenState.value.copy(
            isAppBarOpened = true,
        )
    }

    fun toggleSearch() {
        _screenState.value = screenState.value.copy(
            isSearchActive = true
        )
    }

    fun clearSearchActive() {
        _screenState.value = screenState.value.copy(
            isSearchActive = false
        )
    }

    fun deleteSelectedNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            screenState.value.selectedNotes.forEach {
                repository.deleteNote(it)
            }
            clearSelectedNotes()
        }
    }
}