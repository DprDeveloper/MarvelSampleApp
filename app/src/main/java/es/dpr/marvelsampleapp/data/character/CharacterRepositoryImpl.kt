package es.dpr.marvelsampleapp.data.character

import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterRemoteDataSource
): CharacterRepository {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getAllCharacter(offset,limit))
        }
    override fun getCharacterById(characterId: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getCharacterById(characterId))
        }
}