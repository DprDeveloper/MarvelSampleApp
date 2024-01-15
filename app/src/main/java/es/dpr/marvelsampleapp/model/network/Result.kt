package es.dpr.marvelsampleapp.model.network

data class Result<SomeDomainModel>(
    val code: Int,
    val data: Data<SomeDomainModel>,
)
