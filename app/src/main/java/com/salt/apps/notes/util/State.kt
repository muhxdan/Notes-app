package com.salt.apps.notes.util

sealed class State<out T> {
    data object Loading : State<Nothing>()
    data class Success<out T>(val data: T? = null) : State<T>()
    data class Error<out T>(val message: String) : State<T>()
}
