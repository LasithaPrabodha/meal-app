package com.fanshawe_24w_g7_mealapp.g7_mealapp.util

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal

fun RecentlyCheckedMeal.toMeal(): Meal {
    return Meal(
        idMeal = this.idMeal,
        strMeal = this.strMeal,
        strMealThumb = this.strMealThumb,
        strArea = this.strArea,
        strInstructions = "",
        strSource = "",
        strYoutube = ""
    )
}