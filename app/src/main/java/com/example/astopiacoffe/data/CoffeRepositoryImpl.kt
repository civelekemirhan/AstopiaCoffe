package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeModel
import javax.inject.Inject

class CoffeRepositoryImpl @Inject constructor(private val apiService: CoffeApiService):CoffeRepository {
    override suspend fun getAllCoffes(): List<CoffeModel> {
       return apiService.getAllCoffes()
    }
}