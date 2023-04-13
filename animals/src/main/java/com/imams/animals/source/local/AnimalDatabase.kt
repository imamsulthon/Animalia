package com.imams.animals.source.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AnimalEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AnimaliaDatabase: RoomDatabase() {

    abstract fun animalFavoriteDao(): FavoriteAnimalDao

}