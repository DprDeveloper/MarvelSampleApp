package es.dpr.marvelsampleapp.network.service

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto
import retrofit2.http.GET
import retrofit2.http.Path
interface ComicService {
    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicByCharacter(
        @Path("characterId") characterId: Int,
    ): Response<ComicDto>
}