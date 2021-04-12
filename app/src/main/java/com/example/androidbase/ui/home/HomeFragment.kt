package com.example.androidbase.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.androidbase.R
import com.example.androidbase.databinding.FragmentHomeBinding
import com.example.androidbase.ui.BaseFragment
import com.example.domain.model.Result
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun subscribeToViewModel() {
        with(viewModel) {
            sampleRecipeLiveData.observe(viewLifecycleOwner) {
                Timber.d("got the recipe as $it")

                binding.textView.text = when (it) {
                    is Result.Error -> {
                        "Unable to load data"
                    }
                    Result.Loading -> {
                        "Loading data"
                    }
                    is Result.Success -> {
                        it.data.toString()
                    }
                }
            }
        }
    }
}