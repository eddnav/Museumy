package com.eddnav.museumy.domain.model

data class Artwork(
    val identifier: String,
    val title: String,
    val imageUrl: String?,
    val primaryAuthor: String
)