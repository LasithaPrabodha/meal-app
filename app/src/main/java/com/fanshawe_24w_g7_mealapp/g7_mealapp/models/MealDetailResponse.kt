package com.fanshawe_24w_g7_mealapp.g7_mealapp.models


import com.google.gson.annotations.SerializedName

data class MealDetailResponse(
    @SerializedName("meals")
    val meals: List<MealDetail>
)