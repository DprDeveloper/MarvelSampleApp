package es.dpr.marvelsampleapp.model.network

data class Data<SomeDomainModel>(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<SomeDomainModel>,
    val total: Int
)
