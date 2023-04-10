package com.imams.animalia.domain

import com.imams.animals.model.Animal
import com.imams.animals.repository.AnimalRepository
import javax.inject.Inject

class SelectedAnimalsUseCase @Inject constructor(
    private val repository: AnimalRepository,
) {

    suspend fun getFox(): List<Animal> = repository.getAnimals("fox")

    suspend fun getElephant(): List<Animal> = repository.getAnimals("elephant")

    suspend fun getLion(): List<Animal> = repository.getAnimals("lion")

    suspend fun getDog(): List<Animal> = repository.getAnimals("dog")

    suspend fun getShark(): List<Animal> = repository.getAnimals("shark")

    suspend fun getTurtle(): List<Animal> = repository.getAnimals("turtle")

    suspend fun getWhale(): List<Animal> = repository.getAnimals("whale")

    suspend fun getPenguin(): List<Animal> = repository.getAnimals("penguin")

}