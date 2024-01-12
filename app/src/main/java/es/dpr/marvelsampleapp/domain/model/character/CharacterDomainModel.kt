package es.dpr.marvelsampleapp.domain.model.character

import es.dpr.marvelsampleapp.domain.model.common.ReferenceList
import es.dpr.marvelsampleapp.domain.model.common.Thumbnail

data class CharacterDomainModel(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ReferenceList,
)
