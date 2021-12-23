package com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.fizzbuzzformvalidator.interfaces.IFizzBuzzFormValidator
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormUiState
import com.otmanihakim.fizzbuzzmappy.utils.Outcome
import com.otmanihakim.fizzbuzzmappy.utils.UiEvent
import com.otmanihakim.fizzbuzzmappy.utils.UiScreen
import kotlinx.coroutines.launch

class FizzBuzzFormValidatorViewModel(private var fizzbuzzformvalidator: IFizzBuzzFormValidator) : ViewModel() {

      private val uiMutableLiveData = MutableLiveData<UiScreen<FizzBuzzFormUiState>>()
      val uiLiveData: LiveData<UiScreen<FizzBuzzFormUiState>>
        get() = uiMutableLiveData
    private val eventMutableLiveData = MutableLiveData<UiEvent<FizzBuzzFormEventState>>()
    val eventLiveData: LiveData<UiEvent<FizzBuzzFormEventState>>
        get() = eventMutableLiveData

      fun validate(fizzBuzzForm: FizzBuzzFormEventState){
          handleLoadingState()
          viewModelScope.launch {
              when (val formResult = fizzbuzzformvalidator.validate(fizzBuzzForm)) {
                  is Outcome.Success -> handleFormSuccess(formResult.data)
                  is Outcome.Error -> handleFormError(formResult.error)
              }
          }
       }

    private fun handleLoadingState() {
        uiMutableLiveData.postValue(UiScreen.success(FizzBuzzFormUiState.Loading))
    }

    private fun handleFormSuccess(data: FizzBuzzFormEventState) {
        eventMutableLiveData.postValue(UiEvent.create(data))
        uiMutableLiveData.postValue(UiScreen.success(FizzBuzzFormUiState.IDLE))
    }

    private fun handleFormError(error: FizzBuzzFormError) {
        uiMutableLiveData.postValue(UiScreen.error(error))
    }
}