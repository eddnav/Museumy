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
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val page = params.key ?: INITIAL_PAGE
        val artworks = artworkRepository.getArtworks(page, PAGE_SIZE)

        val lastPage = artworks.size < PAGE_SIZE
        val previousPage = if (page == INITIAL_PAGE) null else page - 1
        val nextPage = if (lastPage) {
            null
        } else {
            page + 1
        }

        return LoadResult.Page(
            data = createNewData(artworks, page),
            prevKey = previousPage,
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

        // API docs say the pages are 0-indexed but page 0 and 1 for the same page size
        // return the same items.
        private const val INITIAL_PAGE = 1

        /**
         *  No offset based pagination means that we cannot make requests of varying sizes,
         *   otherwise we run the risk of having duplicate items, thus we ignore
         *   [PagingSource.LoadParams.loadSize]
         */
        private const val PAGE_SIZE = 10
    }
}