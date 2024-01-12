package es.dpr.marvelsampleapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.data.character.CharacterRepository
import es.dpr.marvelsampleapp.data.character.CharacterRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface CharacterDataModule {
    @Binds
    fun bindsCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
}