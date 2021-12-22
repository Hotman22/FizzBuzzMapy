package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class FizzBuzzFormUiState {
    object Loading : FizzBuzzFormUiState()
    object IDLE : FizzBuzzFormUiState()
}

sealed class FizzBuzzFormError : FizzBuzzFormUiState() {
    object EmptyFormFound : FizzBuzzFormError()
    object ZeroFound : FizzBuzzFormError()
}

sealed class FizzBuzzFormEventState {
    @Parcelize
    data class FizzBuzzFormValidatorUiModel(
        var firstMultiple: String,
        var secondMultiple: String,
        var firstTextReplacement: String,
        var secondTextReplacement: String,
        var limit: String,
    ) : FizzBuzzFormEventState(), Parcelable
}