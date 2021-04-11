package com.example.domain.repository

import com.example.domain.model.RecipeResponse
import io.reactivex.rxjava3.core.Single

interface RecipeRepository {

    fun getRecipes(): Single<RecipeResponse>
}