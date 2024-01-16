package es.dpr.marvelsampleapp.data.comic

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    fun getComicByCharacter(
        characterId: Int
    ): Flow<Response<ComicDto>>
}