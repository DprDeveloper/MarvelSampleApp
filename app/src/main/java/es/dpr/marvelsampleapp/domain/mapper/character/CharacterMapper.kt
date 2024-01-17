package es.dpr.marvelsampleapp.domain.mapper.character

import es.dpr.marvelsampleapp.domain.mapper.Mapper
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.character.CharacterDto
import es.dpr.marvelsampleapp.model.network.Data
import es.dpr.marvelsampleapp.model.network.Result
import javax.inject.Inject

class CharacterMapper @Inject constructor(
) : Mapper<Response<CharacterDto>, Result<CharacterDomainModel>> {

    override fun toDomainModel(value: Response<CharacterDto>): Result<CharacterDomainModel> {
        return Result(
            code = value.code,
            error = value.data?.let { false }?: true,
            data = value.data?.let {
                Data(
                    count = value.data.count,
                    limit = value.data.limit,
                    offset = value.data.offset,
                    results = value.data.results.map {
                        CharacterDomainModel(
                            id = it.id ,
                            name = it.name ,
                            description = it.description ,
                            thumbnail = it.thumbnail ,
                            comics = it.comics ,
                        )
                    },
                    total = value.data.total
                )
            }?:null
        )
    }
}