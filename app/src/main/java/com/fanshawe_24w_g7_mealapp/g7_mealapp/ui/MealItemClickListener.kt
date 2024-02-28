package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui

import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal


interface MealItemClickListener {

    fun onItemClick(meal: Meal)

    fun onFavouriteClick(meal: Meal)
}
