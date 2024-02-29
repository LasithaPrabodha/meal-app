package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rc_meal")
data class RecentlyCheckedMeal(
    @PrimaryKey @ColumnInfo(name = "id") val idMeal: String,
    @ColumnInfo(name = "meal") val strMeal: String,
    @ColumnInfo(name = "thumb") val strMealThumb: String,
    @ColumnInfo(name = "category") val strArea: String?
)