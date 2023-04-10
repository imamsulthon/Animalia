package com.imams.animals.di.module

import com.imams.animals.di.Animalia
import com.imams.animals.repository.AnimalRepository
import com.imams.animals.repository.AnimalRepositoryImpl
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
    fun provideAnimalsRepository(animalApi: AnimalApi): AnimalRepository {
        return AnimalRepositoryImpl(animalApi)
    }

}