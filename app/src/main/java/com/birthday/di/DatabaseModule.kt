package com.birthday.di

import android.content.Context
import androidx.room.Room
import com.birthday.app.AppConstants
import com.birthday.database.AppDatabase
import com.birthday.database.dao.BirthdayDAO
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesBirthdayDAO(appDatabase: AppDatabase): BirthdayDAO {
        return appDatabase.birthdayDAO()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun providesDatabase(): AppDatabase {

        var appInstance: AppDatabase? = null

        val tempInstance = appInstance
        if (tempInstance != null) {
            return tempInstance
        }

        synchronized(this) {
            val instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
            appInstance = instance
            return instance
        }

    }

}