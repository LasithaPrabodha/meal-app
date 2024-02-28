package com.fanshawe_24w_g7_mealapp.g7_mealapp.services

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.CategoryResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetailResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getCategoryMeals(@Query("c") categoryName: String): Response<MealResponse>

    @GET("search.php")
    suspend fun searchMeals(@Query("s") meal: String): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getMealDetail(@Query("i") mealId: String): Response<MealDetailResponse>
}