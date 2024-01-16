package es.dpr.marvelsampleapp.network.comic

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto
import es.dpr.marvelsampleapp.network.service.ComicService
import javax.inject.Inject

class ComicRemoteDataSourceImpl @Inject constructor(
    private val service: ComicService
): ComicRemoteDataSource {
    override suspend fun getComicByCharacter(characterId: Int): Response<ComicDto> =
        service.getComicByCharacter(characterId)

}