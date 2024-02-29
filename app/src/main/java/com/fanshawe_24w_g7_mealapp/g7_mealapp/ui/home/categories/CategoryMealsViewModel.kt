package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMealsViewModel @Inject constructor(
    private val mealsRepository: MealRepository,
) : ViewModel() {

    private val _categoryMeals = HashMap<String, List<Meal>?>()

    suspend fun loadItemInCategory(categoryName: String): List<Meal>? {
        _categoryMeals[categoryName]?.let {
            return _categoryMeals[categoryName]
        }
        try {
            val response = mealsRepository.getCategoryMeals(categoryName)
            return if (response.isSuccessful) {
                _categoryMeals[categoryName] = response.body()?.meals?.toList()
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