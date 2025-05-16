package com.example.astopiacoffe.feature.model

data class CoffeeModel(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: List<String?>?,
    val title: String
)