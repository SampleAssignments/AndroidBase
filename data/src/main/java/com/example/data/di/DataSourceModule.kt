package com.example.data.di

import com.example.data.recipe.RecipeDataSource
import com.example.data.recipe.RecipeLocalDataSource
import com.example.data.recipe.RecipeRemoteDataSource
import com.example.data.restaurant.RestaurantDataSource
import com.example.data.restaurant.RestaurantRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @ApiDataSource
    @Binds
    abstract fun bindsRemoteRecipeDataSource(impl: RecipeRemoteDataSource): RecipeDataSource

    @LocalDataSource
    @Binds
    abstract fun bindsLocalRecipeDataSource(impl: RecipeLocalDataSource): RecipeDataSource

    @ApiDataSource
    @Binds
    abstract fun bindsRemoteRestaurantDataSource(impl: RestaurantRemoteDataSource): RestaurantDataSource
}