package com.eddnav.museumy.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eddnav.museumy.feature.gallery.GalleryItem
import com.eddnav.museumy.repository.ArtObjectRepository

class GalleryItemPagingSource(
    val artObjectRepository: ArtObjectRepository
) : PagingSource<Int, GalleryItem>() {

    private val authorIndex = setOf<String>()

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        TODO("Not yet implemented")
        val position = params.key ?: INITIAL_PAGE
    }

    companion object {
        const val INITIAL_PAGE = 1
    }
}