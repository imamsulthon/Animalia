package com.imams.animalia.domain

import com.imams.animals.model.GroupAnimal
import com.imams.animals.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SelectedAnimalsSourceFlow @Inject constructor(
    private val repository: AnimalRepository
) {

    fun getAllAnimals(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Elephant", repository.getAnimals("elephant")))
        emit(GroupAnimal("Fox", repository.getAnimals("fox")))
        emit(GroupAnimal("Lion", repository.getAnimals("lion")))
        emit(GroupAnimal("Dog", repository.getAnimals("dog")))
        emit(GroupAnimal("Shark", repository.getAnimals("shark")))
        emit(GroupAnimal("Turtle", repository.getAnimals("turtle")))
        emit(GroupAnimal("Whale", repository.getAnimals("whale")))
    }.flowOn(Dispatchers.IO)

    fun getFox(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Fox", repository.getAnimals("fox")))
    }.flowOn(Dispatchers.IO)

    fun getElephant(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Elephant", repository.getAnimals("elephant")))
    }.flowOn(Dispatchers.IO)

    fun getLion(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Lion", repository.getAnimals("lion")))
    }.flowOn(Dispatchers.IO)

    fun getDog(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Dog", repository.getAnimals("dog")))
    }.flowOn(Dispatchers.IO)

    fun getShark(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Shark", repository.getAnimals("shark")))
    }.flowOn(Dispatchers.IO)

    fun getTurtle(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Turtle", repository.getAnimals("turtle")))
    }.flowOn(Dispatchers.IO)

    fun getWhale(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Whale", repository.getAnimals("whale")))
    }.flowOn(Dispatchers.IO)

    fun getPenguin(): Flow<GroupAnimal> = flow {
        emit(GroupAnimal("Penguin", repository.getAnimals("penguin")))
    }.flowOn(Dispatchers.IO)

}