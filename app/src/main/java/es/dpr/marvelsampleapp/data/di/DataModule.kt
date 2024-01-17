package es.dpr.marvelsampleapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.data.character.CharacterRepository
import es.dpr.marvelsampleapp.data.character.CharacterRepositoryImpl
import es.dpr.marvelsampleapp.data.comic.ComicRepository
import es.dpr.marvelsampleapp.data.comic.ComicRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
    @Binds
    fun bindsComicRepository(
        comicRepositoryImpl: ComicRepositoryImpl
    ): ComicRepository
}