package es.dpr.marvelsampleapp.domain.model.comic

import es.dpr.marvelsampleapp.domain.model.common.Thumbnail

data class ComicDomainModel(
    val id: Int,
    val title: String,
    val image: Thumbnail,
    val date: String,
)
