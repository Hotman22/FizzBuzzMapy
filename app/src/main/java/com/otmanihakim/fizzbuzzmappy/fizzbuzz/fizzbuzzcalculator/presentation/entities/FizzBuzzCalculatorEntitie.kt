package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces.IFizzBuzzCalculatorEntitie

class FizzBuzzCalculatorEntitie : IFizzBuzzCalculatorEntitie {

    override fun calculate(
        number: Long,
        firstMultiple: Int,
        secondMultiple: Int,
        firstText: String,
        secondText: String
    ): String {
        return when {
            number % firstMultiple == 0L && number % secondMultiple == 0L -> firstText + secondText
            number % firstMultiple == 0L -> firstText
            number % secondMultiple == 0L -> secondText
            else -> number.toString()
        }
    }
}