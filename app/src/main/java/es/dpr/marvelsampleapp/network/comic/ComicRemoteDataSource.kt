package es.dpr.marvelsampleapp.network.comic

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto

interface ComicRemoteDataSource {
    suspend fun getComicByCharacter(
        characterId: Int
    ): Response<ComicDto>
}