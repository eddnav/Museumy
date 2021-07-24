package com.eddnav.museumy.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.feature.gallery.ArtworkItem
import com.eddnav.museumy.feature.gallery.AuthorNameItem
import com.eddnav.museumy.feature.gallery.GalleryItem
import com.eddnav.museumy.repository.ArtworkRepository

class GalleryItemPagingSource(
    private val artworkRepository: ArtworkRepository
) : PagingSource<Int, GalleryItem>() {

    private var previousAuthor: String? = null

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val page = params.key ?: INITIAL_PAGE
        val loadSize = params.loadSize
        val artworks = artworkRepository.getArtworks(page, loadSize)

        val lastPage = artworks.size < loadSize
        val nextPage = if (lastPage) {  null } else { page + 1 }

        return LoadResult.Page(
            data = createNewData(artworks, page),
            prevKey = null,
            nextKey = nextPage
        )
    }

    private fun createNewData(artworks: List<Artwork>, page: Int): List<GalleryItem> =
        with(mutableListOf<GalleryItem>()) {
            add(AuthorNameItem("PAGE $page"))
            artworks.forEach {
                val isFromNewAuthor = it.primaryAuthor != previousAuthor
                if (isFromNewAuthor) {
                    previousAuthor = it.primaryAuthor
                    add(AuthorNameItem(it.primaryAuthor))
                    add(ArtworkItem(it))
                } else {
                    add(ArtworkItem(it))
                }
            }
            this
        }

    companion object {
        private const val INITIAL_PAGE = 0
    }
}