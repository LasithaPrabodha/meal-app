package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home.categories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryMealsViewModel @Inject constructor(
    private val service: MealService
) : ViewModel() {

    private val mutableCategoryMealsLiveData =
        MutableLiveData<CategoryMealsViewModel.CategoryState>()
    val categoryMealsLiveData: LiveData<CategoryMealsViewModel.CategoryState> =
        mutableCategoryMealsLiveData

    sealed class CategoryState {
        class Success(val data: List<Meal>) : CategoryState()
        class Error(val throwable: Throwable) : CategoryState()
        class Loading(val isLoading: Boolean) : CategoryState()
    }


    fun loadItemInCategory(categoryName: String) {
        viewModelScope.launch {
            updateState(CategoryState.Loading(true))
            try {

                val response = service.getCategoryMeals(categoryName)
                if (response.isSuccessful) {
                    val result = response.body()?.meals ?: emptyList()

                    updateState(CategoryState.Success(result))
                } else {
                    // Handle error
                    Log.e(
                        "CategoryItemsViewModel",
                        "Error when fetching meals for ${categoryName}: ${response.code()}"
                    )
                }

                updateState(CategoryState.Loading(false))
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e(
                    "CategoryItemsViewModel",
                    "Exception when loading meals for ${categoryName}: ${e.message}"
                )
                updateState(CategoryState.Error(e))
            }
        }

    }

    private fun updateState(state: CategoryState) {
        mutableCategoryMealsLiveData.value = state
    }
}