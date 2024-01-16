package es.dpr.marvelsampleapp.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSource
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSourceImpl
import es.dpr.marvelsampleapp.network.comic.ComicRemoteDataSource
import es.dpr.marvelsampleapp.network.comic.ComicRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsCharacterRemoteDatasource(
        characterRemoteDataSourceImpl: CharacterRemoteDataSourceImpl
    ): CharacterRemoteDataSource

    @Binds
    fun bindsComicRemoteDatasource(
        comicrRemoteDataSourceImpl: ComicRemoteDataSourceImpl
    ): ComicRemoteDataSource

}