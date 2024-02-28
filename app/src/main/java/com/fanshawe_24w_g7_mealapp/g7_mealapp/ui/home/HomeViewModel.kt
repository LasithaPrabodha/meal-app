package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Category
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.Meal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: MealService
) : ViewModel() {

    private val _mealOfTheDay = MutableLiveData<Meal>()
    val mealOfTheDay: LiveData<Meal> = _mealOfTheDay


    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    fun getMealOfTheDay() {
        viewModelScope.launch {
            if(_mealOfTheDay.value != null) return@launch

            try {
                val response = service.getRandomMeal()
                if (response.isSuccessful) {
                    _mealOfTheDay.value = response.body()?.meals?.first()
                } else {
                    // Handle error
                    Log.e("HomeViewModel", "Error when loading meal of the day: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e("HomeViewModel", "Exception when loading meal of the day: ${e.message}")
            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            try {
                val response = service.getCategories()
                if (response.isSuccessful) {
                    _categories.value = response.body()?.categories ?: emptyList<Category>()
                } else {
                    // Handle error
                    Log.e("HomeViewModel", "Error when loading categories: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e("HomeViewModel", "Exception when loading categories: ${e.message}")
            }
        }
    }

}