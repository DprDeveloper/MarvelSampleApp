package es.dpr.marvelsampleapp.data.comic

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto
import es.dpr.marvelsampleapp.network.comic.ComicRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val network: ComicRemoteDataSource
): ComicRepository {
    override fun getComicByCharacter(characterId: Int): Flow<Response<ComicDto>> =
        flow {
            emit(network.getComicByCharacter(characterId))
        }.catch {
            emit(Response(error = true))
        }
}