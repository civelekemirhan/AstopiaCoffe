package com.example.astopiacoffe.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "astopia_coffe_onboarding_preferences")

class DataStoreImpl @Inject constructor(private val dataStore: DataStore<Preferences>):DataStoreRepository {

    private val ON_BOARDING_KEY = booleanPreferencesKey("on_boarding_completed")

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit {
            it[ON_BOARDING_KEY] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map {
            it[ON_BOARDING_KEY] ?: false
        }
    }
}