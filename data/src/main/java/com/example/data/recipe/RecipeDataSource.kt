package com.example.data.recipe

import com.example.data.api.RecipeService
import com.example.domain.model.RecipeResponse
import io.reactivex.rxjava3.core.Single
import io.realm.RealmConfiguration
import javax.inject.Inject

interface RecipeDataSource {
    fun getRecipes(): Single<RecipeResponse>
}

class RecipeRemoteDataSource @Inject constructor(
    private val recipeService: RecipeService
) : RecipeDataSource {

    override fun getRecipes(): Single<RecipeResponse> {
        return recipeService.getRecipes()
    }
}

class RecipeLocalDataSource @Inject constructor(
    realmConfig: RealmConfiguration
) : RecipeDataSource {

    override fun getRecipes(): Single<RecipeResponse> {
        return Single.error(NotImplementedError())
    }
}