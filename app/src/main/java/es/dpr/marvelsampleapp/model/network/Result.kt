package es.dpr.marvelsampleapp.model.network

data class Result<SomeDomainModel>(
    val code: Int,
    val error: Boolean = false,
    val data: Data<SomeDomainModel>?,
)
