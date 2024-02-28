package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rc_meal")
data class RecentlyCheckedMeal (
    @PrimaryKey val id: String,
    @ColumnInfo val meal: String?,
    @ColumnInfo val thumb: String?
)