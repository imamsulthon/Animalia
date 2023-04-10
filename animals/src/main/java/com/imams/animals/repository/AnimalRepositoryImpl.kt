package com.imams.animals.repository

import com.imams.animals.mapper.ResponseMapper.toModel
import com.imams.animals.model.Animal
import com.imams.animals.source.remote.api.AnimalApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val animalApi: AnimalApi,
) : AnimalRepository {

    override suspend fun getAnimals(name: String): List<Animal> {
        return try {
            with(Dispatchers.IO) {
                val list = animalApi.getAnimals(name)
                list.map { it.toModel() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf()
        }
    }

    override suspend fun getPictures() {
    }

    override suspend fun getFavorites(): Flow<Animal> {
        TODO("Not yet implemented")
    }

}