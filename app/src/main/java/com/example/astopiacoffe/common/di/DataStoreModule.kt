package com.example.astopiacoffe.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.astopiacoffe.data.datastore.DataStoreImpl
import com.example.astopiacoffe.data.datastore.DataStoreRepository
import com.example.astopiacoffe.data.datastore.dataStore
import com.example.astopiacoffe.data.usecase.ReadOnBoardingUseCase
import com.example.astopiacoffe.data.usecase.SaveOnBoardingUseCase
import com.example.astopiacoffe.data.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context:Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreImpl(dataStore)
    }

    @Singleton
    @Provides
    fun provideUseCase(repository: DataStoreRepository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository)
        )
    }


}