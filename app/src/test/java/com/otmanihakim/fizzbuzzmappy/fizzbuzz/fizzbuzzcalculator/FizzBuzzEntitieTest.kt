package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.FizzBuzzCalculatorEntitie
import junit.framework.TestCase.assertTrue
import org.junit.Test

class FizzBuzzEntitieTest {

    @Test
    fun `Given a number is a multiple of the first multiple then i get the first text`() {
        val number = 9
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == firstText)
    }

    @Test
    fun `Given a number is a multiple of the second multiple then i get the second text`() {
        val number = 10
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == secondText)
    }

    @Test
    fun `Given a number is a multiple of the first and the multiple then i get the first and the second text`() {
        val number = 15
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == firstText+secondText)
    }
}