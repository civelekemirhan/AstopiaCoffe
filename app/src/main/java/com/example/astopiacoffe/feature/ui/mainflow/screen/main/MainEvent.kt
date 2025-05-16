package com.example.astopiacoffe.feature.ui.mainflow.screen.main

import com.example.astopiacoffe.feature.model.CoffeModel

sealed class MainEvent {
    data class SetSearchBarText(val searchText:String):MainEvent()
    data class SetFilterByTitleName(val searchBarText: String):MainEvent()
}