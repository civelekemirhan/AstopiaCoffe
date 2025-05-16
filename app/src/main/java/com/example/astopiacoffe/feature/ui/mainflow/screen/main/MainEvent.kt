package com.example.astopiacoffe.feature.ui.mainflow.screen.main

import com.example.astopiacoffe.feature.model.PassArgument

sealed class MainEvent {
    data class SetSearchBarText(val searchText:String):MainEvent()
    data class SetFilterByTitleName(val searchBarText: String):MainEvent()
    data class SetPassArgument(val passArgument:PassArgument):MainEvent()
}