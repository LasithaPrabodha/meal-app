package com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance

import android.content.Context
import androidx.room.Room
import com.fanshawe_24w_g7_mealapp.g7_mealapp.persistance.dao.RecentlyCheckedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideRcMealDao(appDatabase: AppDatabase): RecentlyCheckedDao {
        return appDatabase.rcMealDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "meals.db"
        ).build()
    }
}