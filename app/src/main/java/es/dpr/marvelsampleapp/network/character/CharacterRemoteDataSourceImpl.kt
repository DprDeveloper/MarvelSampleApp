package es.dpr.marvelsampleapp.network.character

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto
import es.dpr.marvelsampleapp.network.service.CharacterService
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val service: CharacterService
) : CharacterRemoteDataSource {
    override suspend fun getAllCharacter(offset: Int, limit: Int): Response<CharacterDto> =
        service.getAllCharacters(offset,limit)

    override suspend fun getCharacterById(characterId: Int): Response<CharacterDto> =
        service.getCharacterById(characterId)

    override suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Response<CharacterDto> =
        service.getCharacterByStartName(
            offset = offset,
            limit = limit,
            nameStartsWith = nameStartsWith
        )

}