package com.example.data.recipe

import com.example.data.di.ApiDataSource
import com.example.data.di.LocalDataSource
import com.example.domain.model.RecipeResponse
import com.example.domain.repository.RecipeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    @ApiDataSource private val remoteDataSource: RecipeDataSource,
    @LocalDataSource private val localDataSource: RecipeDataSource
) : RecipeRepository {

    override fun getRecipes(): Single<RecipeResponse> {
        return remoteDataSource.getRecipes()
    }
}