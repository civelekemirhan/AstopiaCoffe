package com.example.astopiacoffe.feature.ui.mainflow.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astopiacoffe.data.CoffeeRepository
import com.example.astopiacoffe.feature.model.CoffeeModel
import com.example.astopiacoffe.feature.model.PassArgument
import com.example.astopiacoffe.feature.ui.mainflow.screen.main.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CoffeeRepository) : ViewModel() {

    private val _passArgumentState :MutableStateFlow<PassArgument>  =  MutableStateFlow(PassArgument())
    val passArgumentState = _passArgumentState

    private val _searchBarText: MutableStateFlow<String> = MutableStateFlow("")
    val searchBarText = _searchBarText


    private val _allCoffeeList = MutableStateFlow<List<CoffeeModel?>>(emptyList())


    val filteredCoffeeList = combine(_allCoffeeList, _searchBarText) { coffeeList, searchText ->
        if (searchText.isEmpty()) {
            coffeeList
        } else {
            coffeeList.filter { coffee ->
                coffee?.title?.contains(searchText, ignoreCase = true) ?: false
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    suspend fun loadData() {
        _allCoffeeList.value = repository.getAllCoffees()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SetSearchBarText -> {
                viewModelScope.launch {
                    _searchBarText.value = event.searchText
                }
            }

            is MainEvent.SetFilterByTitleName -> {
                viewModelScope.launch {
                    _allCoffeeList.value = _allCoffeeList.value.filter { item ->
                        item?.let {
                            it.title == event.searchBarText
                        } == true
                    }
                }
            }

            is MainEvent.SetPassArgument -> {
                viewModelScope.launch {
                    Log.d("MainViewModel", event.passArgument.title+event.passArgument.image)
                    _passArgumentState.value = event.passArgument
                }
            }
        }
    }

}


