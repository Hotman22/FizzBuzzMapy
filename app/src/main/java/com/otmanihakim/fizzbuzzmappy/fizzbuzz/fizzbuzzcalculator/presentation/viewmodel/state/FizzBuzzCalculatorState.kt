package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class FizzBuzzCalculUiState {
    object Loading : FizzBuzzCalculUiState()
}

sealed class FizzBuzzCalculError : FizzBuzzCalculUiState() {
    object CalculError : FizzBuzzCalculError()
}

sealed class FizzBuzzCalculEventState {
    @Parcelize
    data class FizzBuzzCalculUiModel(
        var results: ArrayList<String>
    ) : FizzBuzzCalculEventState(), Parcelable
}