package es.dpr.marvelsampleapp.domain.model.network

data class Response<SomeDto>(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data<SomeDto>,
    val etag: String,
    val status: String
)
