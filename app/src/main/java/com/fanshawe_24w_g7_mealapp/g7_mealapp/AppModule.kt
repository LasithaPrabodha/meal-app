package com.fanshawe_24w_g7_mealapp.g7_mealapp

import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepositoryImpl
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRepository(apiService: MealService): MealRepository {
        return MealRepositoryImpl(apiService)
    }

}