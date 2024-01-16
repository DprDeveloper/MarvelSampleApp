package es.dpr.marvelsampleapp.domain.mapper.comic

import es.dpr.marvelsampleapp.domain.mapper.Mapper
import es.dpr.marvelsampleapp.domain.model.comic.ComicDomainModel
import es.dpr.marvelsampleapp.domain.model.network.Response
import es.dpr.marvelsampleapp.model.comic.ComicDto
import es.dpr.marvelsampleapp.model.network.Data
import es.dpr.marvelsampleapp.model.network.Result
import java.text.SimpleDateFormat
import javax.inject.Inject

class ComicMapper @Inject constructor(
) : Mapper<Response<ComicDto>, Result<ComicDomainModel>> {
    override fun toDomainModel(value: Response<ComicDto>): Result<ComicDomainModel> {
        return Result(
            code = value.code,
            data = Data(
                count = value.data.count,
                limit = value.data.limit,
                offset = value.data.offset,
                results = value.data.results.map {
                    ComicDomainModel(
                        id = it.id ,
                        title = it.title,
                        image = it.thumbnail ,
                        date = SimpleDateFormat("dd/MM/yyyy")
                            .format(it.dates.first().date)
                    )
                },
                total = value.data.total
            )
        )
    }

}