package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imams.animalia.domain.MainAnimalUseCase
import com.imams.animals.model.Animal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val mainAnimalUseCase: MainAnimalUseCase
): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _animals = MutableLiveData<List<Animal>>()
    val animals: LiveData<List<Animal>> = _animals

    fun getAnimals(name: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            val result = mainAnimalUseCase.getAnimal(name)
            _animals.postValue(result)
            _loading.postValue(false)
        }
    }

}