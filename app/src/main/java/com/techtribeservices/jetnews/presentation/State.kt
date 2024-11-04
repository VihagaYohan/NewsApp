package com.techtribeservices.jetnews.presentation

sealed class State<out T> {
    object Loading: State<Nothing>()
    class Error(val error: String): State<Nothing>()
    class Success<T>(data: T): State<T>()
}