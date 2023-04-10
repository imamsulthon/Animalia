package com.imams.animalia.domain

import com.imams.animals.model.Animal
import com.imams.animals.repository.AnimalRepository
import javax.inject.Inject

class MainAnimalUseCase @Inject constructor(
    private val animalRepository: AnimalRepository,
) {

    suspend fun getAnimal(name: String): List<Animal> {
        return animalRepository.getAnimals(name)
    }

}