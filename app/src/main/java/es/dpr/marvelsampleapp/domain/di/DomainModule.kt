package es.dpr.marvelsampleapp.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCase
import es.dpr.marvelsampleapp.domain.character.GetCharacterUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindGetCharacterUseCase(
        getCharacterUseCaseImpl: GetCharacterUseCaseImpl
    ): GetCharacterUseCase
}