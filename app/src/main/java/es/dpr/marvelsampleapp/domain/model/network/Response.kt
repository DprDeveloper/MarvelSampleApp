package es.dpr.marvelsampleapp.domain.model.network

data class Response<SomeDto>(
    val attributionHTML: String = "",
    val attributionText: String = "",
    val code: Int = 200,
    val copyright: String = "",
    val data: Data<SomeDto>? = null,
    val etag: String = "",
    val status: String = "",
    val error: Boolean = false
)
