package com.imams.animalia.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.imams.animalia.domain.MainAnimalUseCase
import com.imams.animalia.domain.SelectedAnimalsSourceFlow
import com.imams.animalia.domain.SelectedAnimalsUseCase
import com.imams.animals.model.GroupAnimal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listAnimalsUseCase: SelectedAnimalsUseCase,
    private val flowUseCase: SelectedAnimalsSourceFlow,
    private val mainAnimalUseCase: MainAnimalUseCase,
) : ViewModel() {

    private val _animalsSearchResult = MutableLiveData<PagingData<GroupAnimal>>()
    val animalsSearchResult: LiveData<PagingData<GroupAnimal>> = _animalsSearchResult

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _animals2 = MutableLiveData<PagingData<GroupAnimal>>()
    val animals2: LiveData<PagingData<GroupAnimal>> = _animals2


    fun getAnimals() {
        getAnimalsAsAsync()
//        getAnimalsAsSingleFlow()
//        getAnimalsAsCollectedFlow()
    }

    fun getAnimals(name: String?) {
        if (name.isNullOrEmpty()) {
            return
        }
        viewModelScope.launch {
            _loading.postValue(true)
            val search = mainAnimalUseCase.getAnimal(name)
            val result = listOf(GroupAnimal(name, search))
            _animalsSearchResult.postValue(PagingData.from(result))
            _loading.postValue(false)
        }
    }

    private fun getAnimalsAsAsync() {
        viewModelScope.launch {
            _loading.postValue(true)
            val elephant = async { listAnimalsUseCase.getElephant() }
            val lions = async { listAnimalsUseCase.getLion() }
            val foxes = async { listAnimalsUseCase.getFox() }
            val dogs = async { listAnimalsUseCase.getDog() }
            val sharks = async { listAnimalsUseCase.getShark() }
            val turtles = async { listAnimalsUseCase.getTurtle() }
            val whales = async { listAnimalsUseCase.getWhale() }
            val penguin = async { listAnimalsUseCase.getPenguin() }

            val groups = listOf(
                GroupAnimal("Elephant", elephant.await()),
                GroupAnimal("Lion", lions.await()),
                GroupAnimal("Fox", foxes.await()),
                GroupAnimal("Dog", dogs.await()),
                GroupAnimal("Shark", sharks.await()),
                GroupAnimal("Turtle", turtles.await()),
                GroupAnimal("Whale", whales.await()),
                GroupAnimal("Penguin", penguin.await()),
            )

            val pagingAnimals = PagingData.from(groups)
            _animals2.postValue(pagingAnimals)
            _loading.postValue(false)

        }
    }

    private fun getAnimalsAsSingleFlow() {
        viewModelScope.launch {
            _loading.postValue(false)
            val groups = mutableListOf<GroupAnimal>()
            flowUseCase.getAllAnimals().collect {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
        }
    }

    private fun getAnimalsAsCollectedFlow() {
        viewModelScope.launch {
            _loading.postValue(false)
            val groups = mutableListOf<GroupAnimal>()
            flowUseCase.getElephant().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
            flowUseCase.getLion().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
            flowUseCase.getFox().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
            flowUseCase.getDog().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
            flowUseCase.getShark().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
            flowUseCase.getTurtle().collectLatest {
                groups.add(it)
                _animals2.postValue(PagingData.from(groups))
            }
        }
    }

    private fun printLog(msg: String) {
        println("MainVM: $msg")
    }

}