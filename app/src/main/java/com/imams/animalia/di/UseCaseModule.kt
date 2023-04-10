package com.imams.animalia.di

import com.imams.animalia.domain.MainAnimalUseCase
import com.imams.animalia.domain.SelectedAnimalsUseCase
import com.imams.animals.repository.AnimalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideSelectedAnimalUseCase(repository: AnimalRepository) = SelectedAnimalsUseCase(repository)

    @Provides
    @ViewModelScoped
    fun provideMainAnimalUseCase(repository: AnimalRepository) = MainAnimalUseCase(repository)

}