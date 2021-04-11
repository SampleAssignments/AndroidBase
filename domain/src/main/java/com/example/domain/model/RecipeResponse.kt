package com.example.domain.model

data class RecipeResponse(
    val number: Int,
    val offset: Int,
    val results: List<Recipe>,
    val totalResults: Int
)