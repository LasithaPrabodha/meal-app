package com.fanshawe_24w_g7_mealapp.g7_mealapp.models

data class MealResponse<T>(
    val meals: List<T>
)