package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces.IFizzBuzzCalculatorEntitie
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.interfaces.IFizzBuzzCalculator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome

class FizzBuzzCalculator(private val fizzBuzzEntitiesCalculator: IFizzBuzzCalculatorEntitie) : IFizzBuzzCalculator {

    override suspend fun calculate(fizzBuzzForm: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel): Outcome<FizzBuzzCalculEventState, FizzBuzzCalculError> {
        val result = ArrayList<String>()
        val limit = fizzBuzzForm.limit.toInt()
        val firstMultiple = fizzBuzzForm.firstMultiple.toInt()
        val secondMultiple = fizzBuzzForm.secondMultiple.toInt()
        val firstText = fizzBuzzForm.firstTextReplacement
        val secondText = fizzBuzzForm.secondTextReplacement
        for (i in 1..limit) {
            val fizzBuzzResult = fizzBuzzEntitiesCalculator.calculate(i, firstMultiple, secondMultiple, firstText, secondText)
            result.add(fizzBuzzResult)
        }
        return Outcome.Success(FizzBuzzCalculEventState.FizzBuzzCalculUiModel(result))
    }
}