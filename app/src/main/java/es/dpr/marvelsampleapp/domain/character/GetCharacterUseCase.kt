package es.dpr.marvelsampleapp.domain.character

import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.model.network.Result
import kotlinx.coroutines.flow.Flow

interface GetCharacterUseCase {
    fun getAllCharacter(
        offset: Int,
        limit: Int,
    ): Flow<Result<CharacterDomainModel>>

    fun getCharacterById(
        characterId: Int
    ): Flow<Result<CharacterDomainModel>>

    fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String,
    ): Flow<Result<CharacterDomainModel>>
}