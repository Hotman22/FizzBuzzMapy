package com.otmanihakim.fizzbuzzmappy.fizzbuzz.views.fizzbuzzresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.otmanihakim.fizzbuzzmappy.databinding.FragmentFizzBuzzResultBinding
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.factory.FizzBuzzCalculatorFactory
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.factory.FizzBuzzCalculatorViewModelFactory
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.FizzBuzzCalculatorViewModel
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculError
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculEventState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzcalculator.presentation.viewmodel.state.FizzBuzzCalculUiState
import com.otmanihakim.fizzbuzzmappy.fizzbuzz.fizzbuzzformvalidator.presentation.viewmodel.state.FizzBuzzFormEventState

class FizzBuzzResultFragment : Fragment() {

    private lateinit var fizzBuzzForm: FizzBuzzFormEventState.FizzBuzzFormValidatorUiModel
    private val fizzBuzzCalcul by lazy { FizzBuzzCalculatorFactory.make() }
    private val fizzBuzzCalculViewModel: FizzBuzzCalculatorViewModel by viewModels { FizzBuzzCalculatorViewModelFactory(fizzBuzzCalcul) }
    private var _binding: FragmentFizzBuzzResultBinding? = null
    private val binding get() = _binding!!
    private lateinit var fizzBuzzAdapter: FizzBuzzResultAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            val sageArgs = FizzBuzzResultFragmentArgs.fromBundle(bundle)
            fizzBuzzForm = sageArgs.fizzBuzzForm
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFizzBuzzResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFizzBuzzUiObserver()
        initFizzBuzzEventObserver()
        calculateFizzBuzz()
    }

    private fun calculateFizzBuzz() = fizzBuzzCalculViewModel.calculate(fizzBuzzForm)

    private fun initFizzBuzzUiObserver() {
        fizzBuzzCalculViewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            when (state.data) {
                is FizzBuzzCalculError.CalculError -> manageCalculErrorState()
                is FizzBuzzCalculUiState.Loading -> showLoader()
            }
        }
    }

    private fun initFizzBuzzEventObserver() {
        fizzBuzzCalculViewModel.eventLiveData.observe(viewLifecycleOwner) { state ->
            state.getEventIfNotHandled()?.let {
                if (state.event is FizzBuzzCalculEventState.FizzBuzzCalculUiModel) {
                    showFizzBuzzResult(state.event.results)
                }
            }
        }
    }

    private fun manageCalculErrorState() {
        hideLoader()
    }

    private fun showFizzBuzzResult(results: ArrayList<String>) {
        hideLoader()
        fizzBuzzAdapter = FizzBuzzResultAdapter(results)
        binding.fizzBuzzRv.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = fizzBuzzAdapter
            visibility = View.VISIBLE
        }
    }

    private fun showLoader() {
        binding.resultScreenLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.resultScreenLoader.visibility = View.GONE
    }

}