package com.example.data.di

import com.example.data.recipe.RecipeRepositoryImpl
import com.example.data.restaurant.RestaurantRepositoryImpl
import com.example.domain.repository.RecipeRepository
import com.example.domain.repository.RestaurantRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRecipeRepository(impl: RecipeRepositoryImpl): RecipeRepository

    @Binds
    abstract fun bindsRestaurantRepository(impl: RestaurantRepositoryImpl): RestaurantRepository
}