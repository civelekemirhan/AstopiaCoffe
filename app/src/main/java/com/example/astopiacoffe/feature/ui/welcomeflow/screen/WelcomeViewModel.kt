package com.example.astopiacoffe.feature.ui.welcomeflow.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astopiacoffe.data.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {

    private val _isOnBoardingCompleted = MutableStateFlow(false)
    val isOnBoardingCompleted = _isOnBoardingCompleted

    init {
        viewModelScope.launch {
            useCases.readOnBoardingUseCase.invoke().collectLatest { it->
                _isOnBoardingCompleted.value = it
            }
        }

    }

    fun onCompleted(completed: Boolean){
        viewModelScope.launch {
            useCases.saveOnBoardingUseCase.invoke(completed)
        }
    }


}