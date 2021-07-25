package com.eddnav.museumy.feature.collection

import com.eddnav.museumy.domain.model.CollectionEntry

sealed class CollectionItem

data class AuthorNameItem(val name: String): CollectionItem()
data class CollectionEntryItem(val collectionEntry: CollectionEntry): CollectionItem()