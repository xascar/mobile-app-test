package dev.xascar.mobileapptest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.xascar.mobileapptest.repository.PlatonRepository
import dev.xascar.mobileapptest.repository.PlatonRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesRepository(
        characterRepository: PlatonRepositoryImpl
    ): PlatonRepository
}