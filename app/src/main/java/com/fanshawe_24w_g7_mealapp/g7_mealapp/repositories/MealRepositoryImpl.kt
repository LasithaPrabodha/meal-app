package com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.CategoryResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import retrofit2.Response

class MealRepositoryImpl(private val service: MealService): MealRepository {
    @JvmSuppressWildcards
    override suspend fun getRandomMeal(): Response<MealResponse> {
        return service.getRandomMeal()
    }

    @JvmSuppressWildcards
    override suspend fun getCategories(): Response<CategoryResponse> {
        return service.getCategories()
    }


    @JvmSuppressWildcards
    override suspend fun getCategoryMeals(categoryName: String): Response<MealResponse> {
        return service.getCategoryMeals(categoryName)
    }


}
