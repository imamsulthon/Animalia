package com.imams.animals.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteAnimalDao {

    @Query("SELECT * FROM favorite_animals")
    fun fetchAllAnimal(): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM favorite_animals WHERE name = :name")
    fun getByName(name: String) : Flow<List<AnimalEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_animals WHERE name = :name)")
    suspend fun isExist(name: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAsFavorite(animalEntity: AnimalEntity)

    @Delete
    suspend fun deleteFromFavorite(animalEntity: AnimalEntity)

}