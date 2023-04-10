package com.imams.animals.repository

import com.imams.animals.model.Animal
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {

    suspend fun getAnimals(name: String): List<Animal>

    suspend fun getPictures()

    suspend fun getFavorites(): Flow<Animal>

}