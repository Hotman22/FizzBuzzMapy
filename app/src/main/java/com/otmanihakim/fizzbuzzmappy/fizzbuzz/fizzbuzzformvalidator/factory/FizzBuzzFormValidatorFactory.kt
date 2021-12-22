package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator.FizzBuzzFormValidator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator.interfaces.IFizzBuzzFormValidator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.FizzBuzzFormValidatorViewModel

@Suppress("UNCHECKED_CAST")
class FizzBuzzFormValidatorViewModelFactory(private val fizzBuzzFormValidator: IFizzBuzzFormValidator) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = FizzBuzzFormValidatorViewModel(fizzBuzzFormValidator) as T
}

class FizzBuzzFormValidatorFactory {
    companion object {
        fun make(): IFizzBuzzFormValidator {
            return FizzBuzzFormValidator()
        }
    }
}