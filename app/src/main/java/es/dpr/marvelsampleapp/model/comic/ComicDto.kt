package es.dpr.marvelsampleapp.model.comic

import es.dpr.marvelsampleapp.domain.model.common.Thumbnail
import java.util.Date

data class ComicDto(
    val id: Int,
    val digitalId: Int,
    val title: String,
    val issueNumber: Double,
    val variantDescription: String,
    val description: String,
    val modified: Date,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Int,
    val dates: List<ComicDate>,
    val thumbnail: Thumbnail,
)

data class ComicDate(
    val type: String,
    val date: Date,
)