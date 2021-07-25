package com.eddnav.museumy.repository.conversion

import com.eddnav.museumy.domain.model.CollectionEntry
import com.eddnav.museumy.data.remote.model.CollectionEntry as RemoteCollectionEntry

fun RemoteCollectionEntry.toDomain(): CollectionEntry = CollectionEntry(
    identifier,
    title,
    image?.url,
    primaryAuthor
)