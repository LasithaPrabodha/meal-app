package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryMealsViewModel @Inject constructor(
    private val service: MealService
) : ViewModel() {

    private val _categoryMeals = HashMap<String, Array<Meal>?>()

    suspend fun loadItemInCategory(categoryName: String): Array<Meal>? {
        Log.e("VM", _categoryMeals[categoryName].toString())
        _categoryMeals[categoryName]?.let {
            return _categoryMeals[categoryName]
        }
        Log.e("VM", _categoryMeals[categoryName].toString())
        try {
            val response = service.getCategoryMeals(categoryName)
            return if (response.isSuccessful) {
                _categoryMeals[categoryName] = response.body()?.meals?.toTypedArray()
                _categoryMeals[categoryName]
            } else {
                // Handle error
                Log.e(
                    "CategoryItemsViewModel",
                    "Error when loading meals for ${categoryName}: ${response.code()}"
                )
                null
            }
        } catch (e: Exception) {
            // Handle network or other exceptions
            Log.e(
                "CategoryItemsViewModel",
                "Exception when loading meals for ${categoryName}: ${e.message}"
            )

            return null
        }
    }
}