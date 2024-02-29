package com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.CategoryResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetail
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealResponse
import retrofit2.Response

interface MealRepository {
    suspend fun getRandomMeal(): Response<MealResponse<Meal>>
    suspend fun getCategories(): Response<CategoryResponse>
    suspend fun getCategoryMeals(categoryName: String): Response<MealResponse<Meal>>
    suspend fun searchMeals(searchText: String): Response<MealResponse<Meal>>
    suspend fun getMealDetail(mealId: String): Response<MealResponse<MealDetail>>
}