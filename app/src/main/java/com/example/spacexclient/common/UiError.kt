package com.example.spacexclient.common

import androidx.annotation.StringRes
import com.example.spacexclient.R

sealed class UiError(
    @StringRes val headerMessageRes: Int = R.string.unknown_error_message_title,
    @StringRes val messageRes: Int = R.string.unknown_error_message,
) {
    object UnknownException :
        UiError()

    companion object {
        fun common(exception: Throwable): UiError = UnknownException
    }
}
