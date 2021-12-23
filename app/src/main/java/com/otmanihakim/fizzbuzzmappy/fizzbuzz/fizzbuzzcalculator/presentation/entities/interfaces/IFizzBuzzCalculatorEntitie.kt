package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces

interface IFizzBuzzCalculatorEntitie {
   suspend fun calculate(number: Long, firstMultiple: Int, secondMultiple: Int, firstText: String, secondText: String): String
}