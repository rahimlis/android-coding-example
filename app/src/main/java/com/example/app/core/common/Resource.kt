package com.example.app.core.common

import androidx.annotation.StringRes


sealed class Resource<T>(
        val status: Status,
        val data: T? = null,
        @StringRes val message: Int? = null,
        val throwable: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(Status.SUCCESS, data)
    class Loading<T>(data: T? = null) : Resource<T>(Status.LOADING, data)
    class Error<T>(@StringRes message: Int, throwable: Throwable? = null) : Resource<T>
    (status = Status.ERROR, message = message, throwable = throwable)
}