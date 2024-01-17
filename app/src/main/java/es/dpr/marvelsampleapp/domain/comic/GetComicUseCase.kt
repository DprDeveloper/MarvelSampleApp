package es.dpr.marvelsampleapp.domain.comic

import es.dpr.marvelsampleapp.domain.model.comic.ComicDomainModel
import es.dpr.marvelsampleapp.model.network.Result
import kotlinx.coroutines.flow.Flow

interface GetComicUseCase {
    fun getComicByCharacter(
        characterId: Int
    ): Flow<Result<ComicDomainModel>>
}