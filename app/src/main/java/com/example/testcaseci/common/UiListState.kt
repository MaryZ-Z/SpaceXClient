package com.example.testcaseci.common

sealed interface UiListState<out T> {
    object Loading : UiListState<Nothing>

    data class Success<T>(val data: List<T>) : UiListState<T>

    data class Error(val uiError: UiError) : UiListState<Nothing>
}