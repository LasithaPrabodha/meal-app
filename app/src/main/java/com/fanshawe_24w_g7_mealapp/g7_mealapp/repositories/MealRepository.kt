package com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.CategoryResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetailResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealResponse
import retrofit2.Response

interface MealRepository {
    suspend fun getRandomMeal(): Response<MealResponse>
    suspend fun getCategories(): Response<CategoryResponse>
    suspend fun getCategoryMeals(categoryName: String): Response<MealResponse>

    suspend fun getMealDetail(mealId: String): Response<MealDetailResponse>
}