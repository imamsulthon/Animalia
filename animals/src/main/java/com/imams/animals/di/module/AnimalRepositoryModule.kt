package com.imams.animals.di.module

import android.app.Application
import androidx.room.Room
import com.imams.animals.di.Animalia
import com.imams.animals.repository.AnimalRepository
import com.imams.animals.repository.AnimalRepositoryImpl
import com.imams.animals.source.local.AppDatabase
import com.imams.animals.source.local.FavoriteAnimalDao
import com.imams.animals.source.remote.api.AnimalApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AnimalRepositoryModule {

    @Provides
    @Singleton
    fun provideAnimalsApiService(@Animalia retrofit: Retrofit): AnimalApi {
        return retrofit.create(AnimalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimalsRepository(animalApi: AnimalApi, animalDao: FavoriteAnimalDao): AnimalRepository {
        return AnimalRepositoryImpl(animalApi, animalDao)
    }
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "animal_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserFavoriteDao(database: AppDatabase): FavoriteAnimalDao {
        return database.animalFavoriteDao()
    }

}