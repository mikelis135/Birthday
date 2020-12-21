package com.birthday.di

import android.content.Context
import com.birthday.app.AppConstants
import com.birthday.data.local.DefaultBirthdayLocal
import com.birthday.data.remote.ApiService
import com.birthday.data.remote.DefaultNetworkRepository
import com.birthday.database.dao.BirthdayDAO
import com.birthday.repository.DefaultMainRepository
import com.birthday.repository.MainRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesNetworkRepository(apiService: ApiService): DefaultNetworkRepository {
        return DefaultNetworkRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        defaultNetworkRepository: DefaultNetworkRepository,
        defaultChannelCategoryLocal: DefaultBirthdayLocal
    ): MainRepository {
        return DefaultMainRepository(
            defaultNetworkRepository,
            defaultChannelCategoryLocal
        )
    }

    @Provides
    fun providesDefaultBirthdayLocal(
        birthdayDAO: BirthdayDAO,
        coroutineDispatcher: CoroutineDispatcher
    ): DefaultBirthdayLocal {
        return DefaultBirthdayLocal(birthdayDAO, coroutineDispatcher)
    }

    @Singleton
    @Provides
    fun providesDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesPlainOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(logging)

            }
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}