package com.eddnav.museumy.repository.conversion

import com.eddnav.museumy.domain.model.ArtObject
import com.eddnav.museumy.data.remote.model.ArtObject as RemoteArtObject

fun RemoteArtObject.toDomain(): ArtObject = ArtObject(
    objectNumber,
    title,
    image?.url,
    primaryAuthor
)