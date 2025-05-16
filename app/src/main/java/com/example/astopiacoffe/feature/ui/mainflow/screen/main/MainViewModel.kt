package com.example.astopiacoffe.feature.ui.mainflow.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astopiacoffe.data.CoffeRepository
import com.example.astopiacoffe.feature.model.CoffeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CoffeRepository):ViewModel() {


    private val _searchBarText:MutableStateFlow<String> = MutableStateFlow("")
    val searchBarText = _searchBarText


    private val _allCoffeeList = MutableStateFlow<List<CoffeModel?>>(emptyList())

    // Combined flow for filtered coffee list based on search text
    val filteredCoffeeList = combine(_allCoffeeList, _searchBarText) { coffeeList, searchText ->
        if (searchText.isEmpty()) {
            coffeeList
        } else {
            coffeeList.filter { coffee ->
                coffee?.title?.contains(searchText, ignoreCase = true) ?: false
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())



    init {
        viewModelScope.launch {
            _allCoffeeList.value = repository.getAllCoffes()
        }
    }


    fun onEvent(event:MainEvent){
        when(event){
            is MainEvent.SetSearchBarText -> {
                viewModelScope.launch {
                    _searchBarText.value=event.searchText
                }
            }

            is MainEvent.SetFilterByTitleName -> {
                viewModelScope.launch {
                    _allCoffeeList.value = _allCoffeeList.value.filter {item->
                        item?.let {
                            it.title==event.searchBarText
                        }==true
                    }
                }
            }
        }
    }

}

