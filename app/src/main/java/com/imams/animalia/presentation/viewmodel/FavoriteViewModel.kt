package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.imams.animalia.domain.FavoriteUseCase
import com.imams.animals.model.Animal
import com.imams.animals.model.GroupAnimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
): ViewModel() {

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>> = _animals

    fun getSelectedAnimals() {
        viewModelScope.launch {
            favoriteUseCase.getAllFavorites().collectLatest {
                _animals.postValue(it)
            }
        }
    }

}