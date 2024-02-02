package com.salt.apps.notes.presentation.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salt.apps.notes.data.local.entity.NoteEntity
import com.salt.apps.notes.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _currentTime: MutableStateFlow<String> = MutableStateFlow(getCurrentTime())
    val currentTime: StateFlow<String> = _currentTime.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000)
                _currentTime.value = getCurrentTime()
            }
        }
    }

    private fun getCurrentTime(): String {
        val currentTime = SimpleDateFormat("DD MMMM - HH:mm", Locale.getDefault())
        return currentTime.format(Date())
    }

    fun insertNote(id: Int, title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(
                noteEntity = NoteEntity(
                    id = id,
                    title = title,
                    description = description,
                    date = currentTime.value
                )
            )
        }
    }
}