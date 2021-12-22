package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator.interfaces

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome

interface IFizzBuzzFormValidator {
    suspend fun validate(fizzBuzzForm: FizzBuzzFormEventState): Outcome<FizzBuzzFormEventState, FizzBuzzFormError>
}