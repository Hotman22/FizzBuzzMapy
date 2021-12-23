package com.otmanihakim.fizzbuzzmappy.fizzbuzz.views.fizzbuzzform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.otmanihakim.fizzbuzzmappy.R
import com.otmanihakim.fizzbuzzmappy.databinding.FragmentFizzBuzzFormBinding
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.factory.FizzBuzzFormValidatorFactory
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.factory.FizzBuzzFormValidatorViewModelFactory
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.FizzBuzzFormValidatorViewModel
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormUiState

class FizzBuzzFormFragment : Fragment() {

    private val validateForm by lazy { FizzBuzzFormValidatorFactory.make() }
    private val validateFormViewModel: FizzBuzzFormValidatorViewModel by viewModels { FizzBuzzFormValidatorViewModelFactory(validateForm) }
    private var _binding: FragmentFizzBuzzFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFizzBuzzFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValidateBtnListener()
        initValidateFormUiObserver()
        initValidateFormEventObserver()
    }

    //region validate form
    private fun initValidateBtnListener() = binding.validateBtn.setOnClickListener { validateForm() }

    private fun validateForm() = validateFormViewModel.validate(
        FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel(
            binding.firstMultiple.text.toString(),
            binding.secondMultiple.text.toString(),
            binding.firstTextReplacement.text.toString(),
            binding.secondTextReplacement.text.toString(),
            binding.limitText.text.toString()
        )
    )

    private fun initValidateFormUiObserver() {
        validateFormViewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            when (state.data) {
                is FizzBuzzFormError.EmptyFormFound -> manageEmptyErrorState()
                is FizzBuzzFormError.ZeroFound -> manageZeroErrorState()
                is FizzBuzzFormUiState.Loading -> showLoader()
                else -> hideLoader()
            }
        }
    }

    private fun initValidateFormEventObserver() {
        validateFormViewModel.eventLiveData.observe(viewLifecycleOwner) { state ->
            state.getEventIfNotHandled()?.let {
                if (state.event is FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel) {
                    goToFizzBuzzResultView(state.event)
                }
            }
        }
    }

    private fun manageEmptyErrorState() {
        hideLoader()
        Toast.makeText(requireContext(), getString(R.string.fizz_buzz_form_empty_error), Toast.LENGTH_LONG).show()
    }

    private fun manageZeroErrorState() {
        Toast.makeText(requireContext(), getString(R.string.fizz_buzz_form_zero_error), Toast.LENGTH_LONG).show()
        hideLoader()
    }

    private fun goToFizzBuzzResultView(data: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel) {
        showLoader()
        findNavController().navigate(FizzBuzzFormFragmentDirections.goToFizzBuzzResult(data))
    }

    private fun showLoader() {
        binding.apply {
            validateBtnLoader.visibility = View.VISIBLE
            validateBtn.text = ""
            validateBtn.isEnabled = false
        }
    }

    private fun hideLoader() {
        binding.apply {
            validateBtnLoader.visibility = View.GONE
            validateBtn.text = getString(R.string.fizz_buzz_form_validate_btn)
            validateBtn.isEnabled = true
        }
    }
    //endregion validate form
}