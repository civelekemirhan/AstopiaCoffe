package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeeModel
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(private val apiService: CoffeeApiService):CoffeeRepository {
    override suspend fun getAllCoffees(): List<CoffeeModel> {
       return apiService.getAllCoffees()
    }
}