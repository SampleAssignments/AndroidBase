package com.example.domain.usecase

import com.example.domain.model.RecipeResponse
import com.example.domain.repository.RecipeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRecipes @Inject constructor(private val recipeRepository: RecipeRepository) {
    operator fun invoke(): Single<RecipeResponse> {
        return recipeRepository.getRecipes()
    }
}