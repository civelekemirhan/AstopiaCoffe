package com.example.astopiacoffe.feature.model

data class CoffeModel(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: List<String?>?,
    val title: String
)