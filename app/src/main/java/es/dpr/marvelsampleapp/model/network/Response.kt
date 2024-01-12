package es.dpr.marvelsampleapp.model.network

data class Response<SomeDomainModel>(
    val code: Int,
    val data: Data<SomeDomainModel>,
)
