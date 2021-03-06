package com.example.androidbase.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

interface ViewModelContract {
    // this method will be called in onViewCreated, where you can observe to all viewModel liveData objects.
    fun subscribeToViewModel()
}

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId),
    ViewModelContract {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()
    }
}