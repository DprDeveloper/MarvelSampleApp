package es.dpr.marvelsampleapp.domain.model.common

data class Thumbnail(
    val extension: String,
    val path: String
)
fun Thumbnail.imageUrl(): String{
    return this.path+"."+this.extension
}
