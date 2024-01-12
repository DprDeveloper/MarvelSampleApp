package es.dpr.marvelsampleapp.domain.mapper.character

import es.dpr.marvelsampleapp.domain.mapper.Mapper
import es.dpr.marvelsampleapp.domain.model.character.CharacterDomainModel
import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.domain.model.network.Response as ResDto
import es.dpr.marvelsampleapp.model.network.Response as ResDomainModel
import es.dpr.marvelsampleapp.model.character.CharacterDto
import es.dpr.marvelsampleapp.model.network.Data
import javax.inject.Inject

class CharacterMapper @Inject constructor(
) : Mapper<ResDto<CharacterDto>, ResDomainModel<CharacterDomainModel>> {

    override fun toDomainModel(value: Response<CharacterDto>): es.dpr.marvelsampleapp.model.network.Response<CharacterDomainModel> {
        return ResDomainModel(
            code = value.code,
            data = Data(
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
        )
    }

    override fun toDto(value: es.dpr.marvelsampleapp.model.network.Response<CharacterDomainModel>): Response<CharacterDto> {
        TODO("Not yet implemented")
    }

}