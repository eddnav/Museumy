package com.eddnav.museumy.domain.model

data class CollectionEntry(
    val identifier: String,
    val title: String,
    val imageUrl: String?,
    val primaryAuthor: String
)