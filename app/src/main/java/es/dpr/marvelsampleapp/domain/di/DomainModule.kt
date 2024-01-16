package es.dpr.marvelsampleapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCase
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCaseImpl
import es.dpr.marvelsampleapp.domain.comic.GetComicUseCase
import es.dpr.marvelsampleapp.domain.comic.GetComicUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindGetCharacterUseCase(
        getCharacterUseCaseImpl: GetCharacterUseCaseImpl
    ): GetCharacterUseCase

    @Binds
    fun bindGetComicUseCase(
        getComicUseCaseImpl: GetComicUseCaseImpl
    ): GetComicUseCase
}