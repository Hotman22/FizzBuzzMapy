package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces

interface IFizzBuzzCalculatorEntitie {
    fun calculate(number: Int, firstMultiple: Int, secondMultiple: Int, firstText: String, secondText: String): String
}