package com.fanshawe_24w_g7_mealapp.g7_mealapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetail
import com.fanshawe_24w_g7_mealapp.g7_mealapp.models.MealDetailResponse
import com.fanshawe_24w_g7_mealapp.g7_mealapp.repositories.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {
    private var detailState: MutableLiveData<MealDetail?> = MutableLiveData<MealDetail?>()
    val onDetailState: MutableLiveData<MealDetail?> get() = detailState

    fun getDetailFromServer(mealId: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMealDetail(mealId)
            if(response.isSuccessful){
                val mealDetail = response.body()?.let { mapToMealDetail(it) }
                detailState.postValue(mealDetail)
            }
        }
    }

    private fun mapToMealDetail(response: MealDetailResponse): MealDetail {
        val meals = response.meals
        val mealDetail = meals[0]
        val dateModified = mealDetail.dateModified ?: ""
        val strCreativeCommonsConfirmed = mealDetail.strCreativeCommonsConfirmed ?: ""
        val strDrinkAlternate = mealDetail.strDrinkAlternate ?: ""
        val strImageSource = mealDetail.strImageSource ?: ""
        val strTags = mealDetail.strTags ?: ""
        return MealDetail(
            dateModified,
            mealDetail.idMeal,
            mealDetail.strArea,
            mealDetail.strCategory,
            strCreativeCommonsConfirmed,
            strDrinkAlternate,
            strImageSource,
            mealDetail.strIngredient1,
            mealDetail.strIngredient10,
            mealDetail.strIngredient11,
            mealDetail.strIngredient12,
            mealDetail.strIngredient13,
            mealDetail.strIngredient14,
            mealDetail.strIngredient15,
            mealDetail.strIngredient16,
            mealDetail.strIngredient17,
            mealDetail.strIngredient18,
            mealDetail.strIngredient19,
            mealDetail.strIngredient2,
            mealDetail.strIngredient20,
            mealDetail.strIngredient3,
            mealDetail.strIngredient4,
            mealDetail.strIngredient5,
            mealDetail.strIngredient6,
            mealDetail.strIngredient7,
            mealDetail.strIngredient8,
            mealDetail.strIngredient9,
            mealDetail.strInstructions,
            mealDetail.strMeal,
            mealDetail.strMealThumb,
            mealDetail.strMeasure1,
            mealDetail.strMeasure10,
            mealDetail.strMeasure11,
            mealDetail.strMeasure12,
            mealDetail.strMeasure13,
            mealDetail.strMeasure14,
            mealDetail.strMeasure15,
            mealDetail.strMeasure16,
            mealDetail.strMeasure17,
            mealDetail.strMeasure18,
            mealDetail.strMeasure19,
            mealDetail.strMeasure2,
            mealDetail.strMeasure20,
            mealDetail.strMeasure3,
            mealDetail.strMeasure4,
            mealDetail.strMeasure5,
            mealDetail.strMeasure6,
            mealDetail.strMeasure7,
            mealDetail.strMeasure8,
            mealDetail.strMeasure9,
            mealDetail.strSource,
            strTags,
            mealDetail.strYoutube

        )

    }

}