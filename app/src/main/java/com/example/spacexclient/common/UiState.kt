package com.example.spacexclient.common

sealed interface UiState<out T> {
    object Loading : UiState<Nothing>

    data class Success<T>(val data: T) : UiState<T>

    data class Error(val uiError: UiError) : UiState<Nothing>
}