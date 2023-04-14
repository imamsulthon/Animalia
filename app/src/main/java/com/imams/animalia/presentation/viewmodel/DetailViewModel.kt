package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imams.animalia.domain.FavoriteUseCase
import com.imams.animals.model.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase,
): ViewModel() {

    lateinit var animal: Animal
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun initialize(animal: Animal) {
        this.animal = animal
        isFavorite(animal.name)
    }

    private fun isFavorite(name: String) {
        viewModelScope.launch {
            favoriteUseCase.isFavorite(name).collectLatest {
                _isFavorite.postValue(it)
            }
        }
    }

    fun setFavorite(value: Boolean, animal: Animal?) {
        if (animal == null) return
        viewModelScope.launch {
            if (value) {
                favoriteUseCase.saveAsFavorite(animal)
            } else {
                favoriteUseCase.removeFromFavorite(animal)
            }
            isFavorite(animal.name)
        }
    }

    private fun printLog(msg: String) {
        println("DetailVM: $msg")
    }

}