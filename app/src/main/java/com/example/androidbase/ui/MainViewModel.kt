package com.example.androidbase.ui

import com.example.androidbase.BaseViewModel
import com.example.domain.usecase.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getRecipes: GetRecipes) : BaseViewModel() {

    fun fetchSampleRecipes() {
        getRecipes()
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }
}