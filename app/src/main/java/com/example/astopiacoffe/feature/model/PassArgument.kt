package com.example.astopiacoffe.feature.model



data class PassArgument (
    val title:String="",
    val image:String="",
    val description:String="",
    val ingredients:List<String?>? = emptyList()
)