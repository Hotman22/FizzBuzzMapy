package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.otmanihakim.fizzbuzzmappy.TestCoroutineRule
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.FizzBuzzCalculatorEntitie
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class FizzBuzzEntitieTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `Given a number is a multiple of the first multiple then i get the first text`() = runBlockingTest {
        val number = 9L
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == firstText)
    }

    @Test
    fun `Given a number is a multiple of the second multiple then i get the second text`() = runBlockingTest {
        val number = 10L
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == secondText)
    }

    @Test
    fun `Given a number is a multiple of the first and the multiple then i get the first and the second text`() = runBlockingTest {
        val number = 15L
        val firstMultiple = 3
        val secondMultiple = 5
        val firstText = "fizz"
        val secondText = "buzz"
        val result = FizzBuzzCalculatorEntitie().calculate(number, firstMultiple, secondMultiple, firstText, secondText)

        assertTrue(result == firstText+secondText)
    }
}