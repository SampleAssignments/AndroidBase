package com.example.androidbase.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import com.example.androidbase.BaseViewModel
import com.example.domain.model.RecipeResponse
import com.example.domain.model.Result
import com.example.domain.usecase.GetRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Function
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipes: GetRecipes
) : BaseViewModel() {

    val sampleRecipeLiveData = LiveDataReactiveStreams.fromPublisher(recipeFlowable)

    private val recipeFlowable: Flowable<Result<RecipeResponse>>
        get() = getRecipes()
            .map(Function<RecipeResponse, Result<RecipeResponse>> {
                Result.Success(it)
            })
            .onErrorReturn {
                Result.Error(it)
            }
            .toFlowable()
            .startWith(Single.just(Result.Loading))
}