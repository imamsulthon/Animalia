package com.imams.animals.repository

import com.imams.animals.mapper.EntityMapper.toEntity
import com.imams.animals.mapper.EntityMapper.toModel
import com.imams.animals.mapper.ResponseMapper.toModel
import com.imams.animals.model.Animal
import com.imams.animals.source.local.FavoriteAnimalDao
import com.imams.animals.source.remote.api.AnimalApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val animalApi: AnimalApi,
    private val animalDao: FavoriteAnimalDao,
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

    override suspend fun getFavorites(): Flow<List<Animal>> {
        return animalDao.fetchAllAnimal().map { it.map { o -> o.toModel() } }
    }

    override suspend fun isFavorite(name: String): Flow<Boolean> {
        return flowOf(animalDao.isExist(name)).flowOn(Dispatchers.IO)
    }

    override suspend fun saveAsFavorite(animal: Animal) {
        animalDao.addAsFavorite(animal.toEntity())
    }

    override suspend fun removeFromFavorite(animal: Animal) {
        animalDao.deleteFromFavorite(animal.toEntity())
    }

}