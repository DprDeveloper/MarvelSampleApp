package es.dpr.marvelsampleapp.domain.model.network

data class Data<SomeDto>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<SomeDto>,
    val total: Int
)
