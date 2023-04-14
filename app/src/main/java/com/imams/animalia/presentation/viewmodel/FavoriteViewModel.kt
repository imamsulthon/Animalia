package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.imams.animalia.domain.FavoriteUseCase
import com.imams.animals.model.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {

    suspend fun getSelectedAnimals(): LiveData<List<Animal>> {
        return favoriteUseCase.getAllFavorites().asLiveData()
    }

}