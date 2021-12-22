package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces.IFizzBuzzCalculatorEntitie

class FizzBuzzCalculatorEntitie : IFizzBuzzCalculatorEntitie {

    override fun calculate(
        number: Int,
        firstMultiple: Int,
        secondMultiple: Int,
        firstText: String,
        secondText: String
    ): String {
        return when {
            number % firstMultiple == 0 && number % secondMultiple == 0 -> firstText + secondText
            number % firstMultiple == 0 -> firstText
            number % secondMultiple == 0 -> secondText
            else -> number.toString()
        }
    }
}