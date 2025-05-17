package com.example.astopiacoffe.data.usecase

import com.example.astopiacoffe.data.datastore.DataStoreRepository
import javax.inject.Inject


class SaveOnBoardingUseCase @Inject constructor(private val repository: DataStoreRepository) {


    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)

    }

}