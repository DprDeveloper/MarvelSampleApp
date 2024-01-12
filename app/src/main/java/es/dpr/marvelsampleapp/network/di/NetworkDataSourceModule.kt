package es.dpr.marvelsampleapp.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSource
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsCharacterRemoteDatasource(
        characterRemoteDataSourceImpl: CharacterRemoteDataSourceImpl
    ): CharacterRemoteDataSource

}