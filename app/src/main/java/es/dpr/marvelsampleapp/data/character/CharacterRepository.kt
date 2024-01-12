package es.dpr.marvelsampleapp.data.character

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<Response<CharacterDto>>

    fun getCharacterById(
        characterId: Int,
    ): Flow<Response<CharacterDto>>
}