package com.example.androidbase.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.example.androidbase.BaseViewModel
import com.example.domain.model.RecipeResponse
import com.example.domain.usecase.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipes: GetRecipes
) : BaseViewModel() {

    init {
        fetchSampleRecipes()
    }

    private val mutableRecipesLiveData = MutableLiveData<RecipeResponse>()
    val recipesLiveData: LiveData<RecipeResponse> = mutableRecipesLiveData

    private fun fetchSampleRecipes() {
        getRecipes()
            .subscribeBy(
                onSuccess = {
                    mutableRecipesLiveData.postValue(it)
                },
                onError = {
                    Timber.e("unable to fetch data")
                }
            )
            .addTo(compositeDisposable)
    }

    val sampleRecipeLiveData = LiveDataReactiveStreams.fromPublisher(getRecipes().toFlowable())
}