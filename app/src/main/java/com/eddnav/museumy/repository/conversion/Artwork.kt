package com.eddnav.museumy.repository.conversion

import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.data.remote.model.Artwork as RemoteArtwork

fun RemoteArtwork.toDomain(): Artwork =
    Artwork(
        identifier,
        title,
        image?.url,
        primaryAuthor
    )