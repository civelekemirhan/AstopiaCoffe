package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeModel
import retrofit2.http.GET

interface CoffeApiService {

    @GET("hot")
    suspend fun getAllCoffes(): List<CoffeModel>

}