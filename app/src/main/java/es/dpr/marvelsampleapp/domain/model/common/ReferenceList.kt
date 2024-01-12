package es.dpr.marvelsampleapp.domain.model.common

data class ReferenceList(
    val available: Int,
    val collectionURI: String,
    val items: List<Reference>?,
    val returned: Int
)

