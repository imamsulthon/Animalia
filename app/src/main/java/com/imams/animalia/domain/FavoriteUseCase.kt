package com.imams.animalia.domain

import com.imams.animals.model.Animal
import com.imams.animals.repository.AnimalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val repository: AnimalRepository,
) {

    suspend fun isFavorite(name: String): Flow<Boolean> {
        return repository.isFavorite(name)
    }

    suspend fun saveAsFavorite(animal: Animal) {
        repository.saveAsFavorite(animal)
    }

    suspend fun removeFromFavorite(animal: Animal) {
        repository.removeFromFavorite(animal)
    }

    suspend fun getAllFavorites(): Flow<List<Animal>> = repository.getFavorites()

}