package es.dpr.marvelsampleapp.network.character

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto

interface CharacterRemoteDataSource {
    suspend fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Response<CharacterDto>

    suspend fun getCharacterById(
        characterId: Int,
    ): Response<CharacterDto>

    suspend fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): Response<CharacterDto>
}