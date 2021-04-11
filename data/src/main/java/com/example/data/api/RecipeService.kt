package com.example.data.api

import com.example.data.models.Endpoints.GET_RECIPES
import com.example.data.models.Endpoints.Headers.CONTENT_TYPE_JSON
import com.example.domain.model.RecipeResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipeService {

    @GET(GET_RECIPES)
    @Headers(CONTENT_TYPE_JSON)
    fun getRecipes(): Single<RecipeResponse>
}