package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.repositories.RCMealsRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MealRepository,
) : ViewModel() {

    private val mutableMealsLiveData = MutableLiveData<MealState>()
    val mealsLiveData: LiveData<MealState> = mutableMealsLiveData

    sealed class MealState {
        class Success(val data: List<Meal>) : MealState()
        class Error(val throwable: Throwable) : MealState()
        class Loading(val isLoading: Boolean) : MealState()
    }

    fun searchMeals(searchText: String) {
        viewModelScope.launch {
            try {

                updateState(MealState.Loading(true))
                val response = repository.searchMeals(searchText)
                if (response.isSuccessful) {
                    if (response.body() == null) {
                        Log.e("Search", "e.toString()")

                    }
                    val result = response.body()?.meals ?: emptyList<Meal>()

                    updateState(MealState.Success(result))
                } else {
                    // Handle error
                    Log.e("SearchViewModel", "Error when fetching meals: ${response.code()}")
                }

                updateState(MealState.Loading(false))
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e(
                    "SearchViewModel",
                    "Exception when loading meals for ${searchText}: ${e.message}"
                )

                updateState(MealState.Error(e))
            }

        }
    }



    private fun updateState(state: MealState) {
        mutableMealsLiveData.value = state
    }
}