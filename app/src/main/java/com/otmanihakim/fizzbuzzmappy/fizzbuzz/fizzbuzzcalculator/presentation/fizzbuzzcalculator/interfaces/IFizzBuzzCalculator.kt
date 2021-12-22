package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.interfaces

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome

interface IFizzBuzzCalculator {
    suspend fun calculate(fizzBuzzForm: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel): Outcome<FizzBuzzCalculEventState, FizzBuzzCalculError>
}