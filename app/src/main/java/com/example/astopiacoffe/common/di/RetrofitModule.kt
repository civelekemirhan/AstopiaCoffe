package com.example.astopiacoffe.common.di

import com.example.astopiacoffe.common.util.Constant.BASE_URL
import com.example.astopiacoffe.data.CoffeApiService
import com.example.astopiacoffe.data.CoffeRepository
import com.example.astopiacoffe.data.CoffeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoffeApiService {
        return retrofit.create(CoffeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoffeRepository(apiService: CoffeApiService): CoffeRepository {
        return CoffeRepositoryImpl(apiService)
    }
}
