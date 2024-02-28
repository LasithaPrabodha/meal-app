package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentlyCheckedDao {
    @Query("SELECT * FROM rc_meal")
    fun getAll(): Flow<List<RecentlyCheckedMeal>>

    @Insert
    fun addMeal(meal: RecentlyCheckedMeal)

    @Delete
    fun delete(meal: RecentlyCheckedMeal)
}