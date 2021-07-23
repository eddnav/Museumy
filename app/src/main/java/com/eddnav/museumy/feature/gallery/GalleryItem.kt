package com.eddnav.museumy.feature.gallery

import com.eddnav.museumy.domain.model.ArtObject

sealed class GalleryItem

class AuthorNameItem(val name: String): GalleryItem()
class ArtworkItem(val artwork: ArtObject): GalleryItem()