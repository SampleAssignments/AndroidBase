package com.example.data.recipe

import com.example.data.CacheSource
import com.example.data.di.ApiDataSource
import com.example.data.di.LocalDataSource
import com.example.domain.model.RecipeResponse
import com.example.domain.repository.RecipeRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    @ApiDataSource private val remoteDataSource: RecipeDataSource,
    @LocalDataSource private val localDataSource: RecipeDataSource,
    private val cacheSource: CacheSource
) : RecipeRepository {

    companion object {
        private const val RECIPE_CACHE_KEY = "RecipeCacheKey"
    }

    override fun getRecipes(): Single<RecipeResponse> {
        return Maybe.create<RecipeResponse> { e ->
            val recipeResponse = cacheSource.retrieveFromCache<RecipeResponse>(RECIPE_CACHE_KEY)
            if (recipeResponse == null) e.onComplete() else e.onSuccess(recipeResponse)
        }.switchIfEmpty(remoteDataSource.getRecipes().doAfterSuccess {
            cacheSource.storeInCache(RECIPE_CACHE_KEY, it, 1)
        })
    }
}