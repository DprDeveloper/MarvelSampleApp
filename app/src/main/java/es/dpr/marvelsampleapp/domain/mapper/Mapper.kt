package es.dpr.marvelsampleapp.domain.mapper

interface Mapper<ResponseSomeDto, ResponseSomeDomainModel> {

    fun toDomainModel(value: ResponseSomeDto): ResponseSomeDomainModel
}