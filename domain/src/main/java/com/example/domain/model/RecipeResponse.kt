package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeResponse(
    val number: Int,
    val offset: Int,
    val results: List<Recipe>,
    val totalResults: Int
) : Parcelable