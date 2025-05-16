package com.example.astopiacoffe.common.di

import com.example.astopiacoffe.common.util.Constant.BASE_URL
import com.example.astopiacoffe.data.CoffeeApiService
import com.example.astopiacoffe.data.CoffeeRepository
import com.example.astopiacoffe.data.CoffeeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoffeeApiService {
        return retrofit.create(CoffeeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoffeRepository(apiService: CoffeeApiService): CoffeeRepository {
        return CoffeeRepositoryImpl(apiService)
    }
}
