package com.imams.animals.repository

import com.imams.animals.model.Animal
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {

    suspend fun getAnimals(name: String): List<Animal>

    suspend fun getFavorites(): Flow<List<Animal>>

    suspend fun isFavorite(name: String): Flow<Boolean>
    suspend fun saveAsFavorite(animal: Animal)

    suspend fun removeFromFavorite(animal: Animal)

}