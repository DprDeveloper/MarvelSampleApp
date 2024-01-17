package es.dpr.marvelsampleapp.domain.character

import es.dpr.marvelsampleapp.data.character.CharacterRepository
import es.dpr.marvelsampleapp.domain.mapper.character.CharacterMapper
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.model.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val characterMapper: CharacterMapper,
): GetCharacterUseCase {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<Result<CharacterDomainModel>> {
        return characterRepository.getAllCharacter(offset, limit)
            .map {
                characterMapper.toDomainModel(it)
            }

    }

    override fun getCharacterById(characterId: Int): Flow<Result<CharacterDomainModel>> {
        return characterRepository.getCharacterById(characterId)
            .map {
                characterMapper.toDomainModel(it)
            }
    }

    override fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<Result<CharacterDomainModel>> {
        return characterRepository.getCharacterByStartName(
            offset = offset,
            limit = limit,
            nameStartsWith = nameStartsWith
        ).map {
            characterMapper.toDomainModel(it)
        }
    }

}