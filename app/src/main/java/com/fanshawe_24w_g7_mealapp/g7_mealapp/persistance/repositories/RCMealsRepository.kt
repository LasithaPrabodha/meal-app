package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.repositories

import androidx.annotation.WorkerThread
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.dao.RecentlyCheckedDao
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.entities.RecentlyCheckedMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RCMealsRepository @Inject constructor(private val rcDao: RecentlyCheckedDao) {

    @WorkerThread
    suspend fun getList() = withContext(Dispatchers.IO) {
        rcDao.getAll()
    }


    @WorkerThread
    suspend fun insert(rcMeal: RecentlyCheckedMeal) = withContext(Dispatchers.IO) {
        rcDao.addMeal(rcMeal)
    }

    @WorkerThread
    suspend fun deleteAll() = withContext(Dispatchers.IO){
        rcDao.deleteAll()
    }
}