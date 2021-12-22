package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.otmanihakim.fizzbuzzmappy.TestCoroutineRule
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.interfaces.IFizzBuzzCalculatorEntitie
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.FizzBuzzCalculator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.interfaces.IFizzBuzzCalculator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.FizzBuzzCalculatorViewModel
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class FizzBuzzCalculatorTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

     /**
     * As a user
     * I want to see the result of fizz buzz form
     * In order to know
     */

     @Test
     fun `Given a user When he want to see the result of fizz buzz operation Then whe show gim`() = runBlockingTest {
         val fizzBuzzForm = FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel(
             "3",
             "5",
             "fizz",
             "buzz",
             "100",
         )
         val firstMultiple = fizzBuzzForm.firstMultiple.toInt()
         val secondMultiple = fizzBuzzForm.secondMultiple.toInt()
         val firstText = fizzBuzzForm.firstTextReplacement
         val secondText = fizzBuzzForm.secondTextReplacement
         val entitie: IFizzBuzzCalculatorEntitie = mock()
         val interactor: IFizzBuzzCalculator = FizzBuzzCalculator(entitie)
         val viewModel = FizzBuzzCalculatorViewModel(interactor)
         Mockito.`when`(entitie.calculate(1, firstMultiple, secondMultiple, firstText, secondText)).thenAnswer { "fizz" }

         viewModel.calculate(fizzBuzzForm)

         Assert.assertTrue(viewModel.eventLiveData.value!!.event is FizzBuzzCalculEventState.FizzBuzzCalculUiModel)
         Assert.assertTrue((viewModel.eventLiveData.value!!.event as FizzBuzzCalculEventState.FizzBuzzCalculUiModel).results[0] == "fizz")
     }
}
