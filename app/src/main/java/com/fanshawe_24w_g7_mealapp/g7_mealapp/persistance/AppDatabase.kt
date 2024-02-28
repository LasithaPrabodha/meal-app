package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.dao.RecentlyCheckedDao
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal

@Database(entities = [RecentlyCheckedMeal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rcMealDao(): RecentlyCheckedDao
}