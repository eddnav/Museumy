package com.eddnav.museumy.usecase

import androidx.paging.*
import com.eddnav.museumy.feature.gallery.ArtworkItem
import com.eddnav.museumy.feature.gallery.AuthorNameItem
import com.eddnav.museumy.feature.gallery.GalleryItem
import com.eddnav.museumy.repository.ArtworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGalleryItemPagingDataFlow(private val artworkRepository: ArtworkRepository) {

    operator fun invoke(): Flow<PagingData<GalleryItem>> =
        artworkRepository.getArtworkPager()
            .flow
            .map {
                val modifiedPagingData: PagingData<GalleryItem> = it.map { artwork ->
                    ArtworkItem(artwork)
                }
                modifiedPagingData.insertSeparators { previousItem: GalleryItem?,
                                                      nextItem: GalleryItem? ->
                    val previousArtworkItem =
                        previousItem as? ArtworkItem
                    val nextArtworkItem = nextItem as? ArtworkItem
                    val previousAuthor = previousArtworkItem?.artwork?.primaryAuthor
                    val nextAuthor =
                        nextArtworkItem?.artwork?.primaryAuthor ?: return@insertSeparators null
                    return@insertSeparators if (previousAuthor != nextAuthor) {
                        AuthorNameItem(nextAuthor)
                    } else {
                        null
                    }
                }
            }
}