package com.eddnav.museumy.domain.model

data class ArtObject(
    val objectNumber: String,
    val title: String,
    val imageUrl: String?,
    val primaryAuthor: String
)