package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeeModel

interface CoffeeRepository {

    suspend fun getAllCoffees() : List<CoffeeModel>

}