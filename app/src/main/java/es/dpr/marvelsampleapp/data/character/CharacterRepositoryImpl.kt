package es.dpr.marvelsampleapp.data.character

import android.util.Log
import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto
import es.dpr.marvelsampleapp.network.character.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val network: CharacterRemoteDataSource
): CharacterRepository {
    override fun getAllCharacter(offset: Int, limit: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getAllCharacter(offset,limit))
        }.catch {
            emit(Response(error = true))
        }
    override fun getCharacterById(characterId: Int): Flow<Response<CharacterDto>> =
        flow {
            emit(network.getCharacterById(characterId))
        }.catch {
            emit(Response(error = true))
        }

    override fun getCharacterByStartName(
        offset: Int,
        limit: Int,
        nameStartsWith: String
    ): Flow<Response<CharacterDto>> =
        flow {
            emit(
                network.getCharacterByStartName(
                offset = offset,
                limit = limit,
                nameStartsWith = nameStartsWith
                )
            )
        }.catch {
            emit(Response(error = true))
        }
}