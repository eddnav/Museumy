package com.eddnav.museumy.usecase

import androidx.paging.*
import com.eddnav.museumy.feature.collection.CollectionEntryItem
import com.eddnav.museumy.feature.collection.AuthorNameItem
import com.eddnav.museumy.feature.collection.CollectionItem
import com.eddnav.museumy.repository.CollectionEntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCollectionItemPagingDataFlow(private val collectionEntryRepository: CollectionEntryRepository) {

    operator fun invoke(): Flow<PagingData<CollectionItem>> =
        collectionEntryRepository.getCollectionEntryPager()
            .flow
            .map {
                val modifiedPagingData: PagingData<CollectionItem> = it.map { entry ->
                    CollectionEntryItem(entry)
                }
                modifiedPagingData.insertSeparators { previousItem: CollectionItem?,
                                                      nextItem: CollectionItem? ->
                    val previousCollectionItem =
                        previousItem as? CollectionEntryItem
                    val nextCollectionItem = nextItem as? CollectionEntryItem
                    val previousAuthor = previousCollectionItem?.collectionEntry?.primaryAuthor
                    val nextAuthor =
                        nextCollectionItem?.collectionEntry?.primaryAuthor ?: return@insertSeparators null
                    return@insertSeparators if (previousAuthor != nextAuthor) {
                        AuthorNameItem(nextAuthor)
                    } else {
                        null
                    }
                }
            }
}