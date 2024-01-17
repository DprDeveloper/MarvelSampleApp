package es.dpr.marvelsampleapp.model.character

import es.dpr.marvelsampleapp.domain.model.common.ReferenceList
import es.dpr.marvelsampleapp.domain.model.common.Thumbnail
import es.dpr.marvelsampleapp.domain.model.common.Url

data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: ReferenceList,
    val events: ReferenceList,
    val series: ReferenceList,
    val stories: ReferenceList,
    val urls: List<Url>,
    val modified: String,
    val resourceURI: String
)
