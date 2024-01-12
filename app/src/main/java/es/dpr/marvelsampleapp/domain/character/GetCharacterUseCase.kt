package es.dpr.marvelsampleapp.domain.character

import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.model.network.Response
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<Response<CharacterDomainModel>>

    fun getCharacterById(
        characterId: Int
    ): Flow<Response<CharacterDomainModel>>
}