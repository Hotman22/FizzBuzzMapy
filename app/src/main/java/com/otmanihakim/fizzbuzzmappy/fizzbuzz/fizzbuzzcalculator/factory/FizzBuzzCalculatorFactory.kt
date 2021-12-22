package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.factory

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.entities.FizzBuzzCalculatorEntitie
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.FizzBuzzCalculatorViewModel
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.FizzBuzzCalculator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.interfaces.IFizzBuzzCalculator

@Suppress("UNCHECKED_CAST")
class FizzBuzzCalculatorViewModelFactory(private val fizzBuzzCalculator: IFizzBuzzCalculator) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FizzBuzzCalculatorViewModel(fizzBuzzCalculator) as T
}

class FizzBuzzCalculatorFactory {
    companion object {
        fun make(): IFizzBuzzCalculator {
            val entitie = FizzBuzzCalculatorEntitie()
            return FizzBuzzCalculator(entitie)
        }
    }
}