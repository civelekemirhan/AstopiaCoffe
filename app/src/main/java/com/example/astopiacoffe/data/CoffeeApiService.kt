package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeeModel
import retrofit2.http.GET

interface CoffeeApiService {

    @GET("hot")
    suspend fun getAllCoffees(): List<CoffeeModel>

}