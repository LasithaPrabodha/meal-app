package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetail
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.repositories.RCMealsRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepository
import com.fanshawe_24w_g7_mealapp.g7_mealapp.services.MealService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mealsRepository: MealRepository,
    private val rcMealsRepository: RCMealsRepository
) : ViewModel() {
    private var detailState: MutableLiveData<MealDetail?> = MutableLiveData<MealDetail?>()
    val onDetailState: MutableLiveData<MealDetail?> get() = detailState

    suspend fun insertRcMeal(meal: MealDetail) {
        val rcMeal = RecentlyCheckedMeal(
            idMeal = meal.idMeal,
            strMeal = meal.strMeal,
            strMealThumb = meal.strMealThumb,
            strArea = meal.strArea
        )

        rcMealsRepository.insert(rcMeal)
    }


    fun getDetailFromServer(mealId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mealsRepository.getMealDetail(mealId)
            if (response.isSuccessful) {
                val mealDetail = response.body()?.meals?.first()
                detailState.postValue(mealDetail)
            }
        }
    }

}