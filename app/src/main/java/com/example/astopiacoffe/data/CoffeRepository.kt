package com.example.astopiacoffe.data

import com.example.astopiacoffe.feature.model.CoffeModel

interface CoffeRepository {

    suspend fun getAllCoffes() : List<CoffeModel>

}