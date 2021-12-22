package com.otmanihakim.fizzbuzzmappy.utils

sealed class Outcome<out T : Any, out E : Any> {
    data class Success<out T : Any>(val data: T) : Outcome<T, Nothing>()
    data class Error<out E : Any>(val error: E) : Outcome<Nothing, E>()
}