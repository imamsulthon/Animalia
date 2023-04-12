package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.imams.animalia.domain.MainAnimalUseCase
import com.imams.animalia.domain.SelectedAnimalsUseCase
import com.imams.animals.model.Animal
import com.imams.animals.model.GroupAnimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val selectedAnimalsUseCase: SelectedAnimalsUseCase,
    private val mainAnimalUseCase: MainAnimalUseCase,
): ViewModel() {

    private val _animals = MutableLiveData<PagingData<GroupAnimal>>()
    val animals: LiveData<PagingData<GroupAnimal>> = _animals

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _animals2 = MutableLiveData<PagingData<GroupAnimal>>()
    val animals2: LiveData<PagingData<GroupAnimal>> = _animals2

    fun getAnimals(name: String? = "") {
        if (name.isNullOrEmpty()) {
            getSelectedAnimals()
            return
        }
       viewModelScope.launch {
           val search = mainAnimalUseCase.getAnimal(name)
           val result = listOf(GroupAnimal(name, search))
           _animals.postValue(PagingData.from(result))
       }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getSelectedAnimals() {
        viewModelScope.launch {
            val elephant = async { selectedAnimalsUseCase.getElephant() }
            val lions = async { selectedAnimalsUseCase.getLion() }
            val foxes = async { selectedAnimalsUseCase.getFox() }
            val dogs = async { selectedAnimalsUseCase.getDog() }
            val sharks = async { selectedAnimalsUseCase.getShark() }
            val turtles = async { selectedAnimalsUseCase.getTurtle() }
            val whales = async { selectedAnimalsUseCase.getWhale() }
            val penguin = async { selectedAnimalsUseCase.getPenguin() }

            val animals: List<Animal> = awaitAll(elephant,lions, foxes, dogs, sharks, whales, turtles, penguin).flatten()

            val groups = listOf(
                GroupAnimal("Elephant", elephant.getCompleted()),
                GroupAnimal("lions", lions.getCompleted()),
                GroupAnimal("Foxes", foxes.await()),
                GroupAnimal("Dogs", dogs.await()),
                GroupAnimal("Sharks", sharks.await()),
                GroupAnimal("Turtles", turtles.await()),
            )

            val pagingAnimals = PagingData.from(groups)
            _animals2.postValue(pagingAnimals)

            printLog("joint $animals $this")
        }
    }

    private fun printLog(msg: String) {
        println("MainVM: $msg")
    }

}