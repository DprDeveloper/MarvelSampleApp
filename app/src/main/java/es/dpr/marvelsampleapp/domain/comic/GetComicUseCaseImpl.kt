package es.dpr.marvelsampleapp.domain.comic

import es.dpr.marvelsampleapp.data.comic.ComicRepository
import es.dpr.marvelsampleapp.domain.mapper.comic.ComicMapper
import es.dpr.marvelsampleapp.domain.model.comic.ComicDomainModel
import es.dpr.marvelsampleapp.model.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetComicUseCaseImpl @Inject constructor(
    private val comicRepository: ComicRepository,
    private val comicMapper: ComicMapper,
): GetComicUseCase {
    override fun getComicByCharacter(characterId: Int): Flow<Result<ComicDomainModel>> {
        return comicRepository.getComicByCharacter(characterId)
            .map {
                comicMapper.toDomainModel(it)
            }
    }

}