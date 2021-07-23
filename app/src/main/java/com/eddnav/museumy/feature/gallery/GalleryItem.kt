package com.eddnav.museumy.feature.gallery

import com.eddnav.museumy.domain.model.Artwork

sealed class GalleryItem

data class AuthorNameItem(val name: String): GalleryItem()
data class ArtworkItem(val artwork: Artwork): GalleryItem()