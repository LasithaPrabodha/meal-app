package com.fanshawe_24w_g7_mealapp.g7_mealapp.models

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String,
    val strYoutube: String? = "",
    val strSource: String? =""
)
