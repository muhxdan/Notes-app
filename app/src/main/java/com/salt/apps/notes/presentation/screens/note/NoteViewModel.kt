package com.salt.apps.notes.presentation.screens.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.data.repository.Repository
import com.salt.apps.notes.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _notesList: MutableStateFlow<State<List<NoteEntity>>> =
        MutableStateFlow(State.Loading)
    val notesList: StateFlow<State<List<NoteEntity>>> get() = _notesList.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes()
                .catch { _notesList.value = State.Error(it.message.toString()) }
                .collect {
                    _notesList.value = State.Success(it)
                }
        }
    }
}