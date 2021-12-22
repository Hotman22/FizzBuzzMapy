package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.fizzbuzzcalculator.interfaces.IFizzBuzzCalculator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculUiState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome
import com.otmanihakim.fizzbuzzmappy.utils.UiEvent
import com.otmanihakim.fizzbuzzmappy.utils.UiScreen
import kotlinx.coroutines.launch

class FizzBuzzCalculatorViewModel(private var fizzbuzzcalculator: IFizzBuzzCalculator) :
    ViewModel() {

    private val uiMutableLiveData = MutableLiveData<UiScreen<FizzBuzzCalculUiState>>(UiScreen.loading())
    val uiLiveData: LiveData<UiScreen<FizzBuzzCalculUiState>>
        get() = uiMutableLiveData
    private val eventMutableLiveData = MutableLiveData<UiEvent<FizzBuzzCalculEventState>>()
    val eventLiveData: LiveData<UiEvent<FizzBuzzCalculEventState>>
        get() = eventMutableLiveData

    fun calculate(fizzBuzzForm: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel) {
        handleLoadingState()
        viewModelScope.launch {
            when (val calculResult = fizzbuzzcalculator.calculate(fizzBuzzForm)) {
                is Outcome.Success -> handleCalculateSuccess(calculResult.data)
                is Outcome.Error -> handleCalculateError(calculResult.error)
            }
        }
    }

    private fun handleLoadingState() {
        uiMutableLiveData.postValue(UiScreen.success(FizzBuzzCalculUiState.Loading))
    }

    private fun handleCalculateSuccess(data: FizzBuzzCalculEventState) {
        eventMutableLiveData.postValue(UiEvent.create((data)))
    }

    private fun handleCalculateError(error: FizzBuzzCalculError) {
        uiMutableLiveData.postValue(UiScreen.error(error))
    }
}

