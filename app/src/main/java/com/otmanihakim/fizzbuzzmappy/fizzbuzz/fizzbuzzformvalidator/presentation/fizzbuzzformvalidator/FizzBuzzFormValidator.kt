package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator

import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator.interfaces.IFizzBuzzFormValidator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome

class FizzBuzzFormValidator : IFizzBuzzFormValidator {

    override suspend fun validate(fizzBuzzForm: FizzBuzzFormEventState): Outcome<FizzBuzzFormEventState, FizzBuzzFormError> {
        val uiModel = fizzBuzzForm as? FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel
        uiModel?.let { form ->
            if (manageEmptyFormCase(form)) return Outcome.Error(FizzBuzzFormError.EmptyFormFound)
            if (manageZeroMultipleCase(form)) return Outcome.Error(FizzBuzzFormError.ZeroFound)
            return Outcome.Success(uiModel)
        }
        return Outcome.Error(FizzBuzzFormError.EmptyFormFound)
    }

    private fun manageEmptyFormCase(form: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel): Boolean =
        form.firstMultiple.isBlank() || form.secondMultiple.isBlank() || form.firstTextReplacement.isBlank()
                || form.secondTextReplacement.isBlank() || form.limit.isBlank()

    private fun manageZeroMultipleCase(form: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel): Boolean =
        form.firstMultiple.toInt() == 0 || form.secondMultiple.toInt() == 0
}