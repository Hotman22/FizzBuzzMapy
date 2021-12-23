package com.otmanihakim.fizzbuzzmappy.utils

enum class ViewState {
    LOADING,
    IDLE,
    ERROR
}

data class UiScreen<out T>(val state: ViewState, val data: T?) {
    companion object {
        fun <T> loading(data: T? = null): UiScreen<T> = UiScreen<T>(state = ViewState.LOADING, data = data)
        fun <T> success(data: T): UiScreen<T> = UiScreen<T>(state = ViewState.IDLE, data = data)
        fun <T> error(data: T? = null): UiScreen<T> = UiScreen<T>(state = ViewState.ERROR, data = data)
    }
}

data class UiEvent<out T>(val event: T) {
    var hasBeenHandled = false
        private set

    fun getEventIfNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        event
    }

    companion object {
        fun <T> create(event: T) = UiEvent(event)
    }
}