package com.eddnav.museumy.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.repository.conversion.toDomain

class ArtworkPagingSource(
    private val rijksDataService: RijksDataService
) : PagingSource<Int, Artwork>() {

    override fun getRefreshKey(state: PagingState<Int, Artwork>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artwork> {
        try {
            val page = params.key ?: INITIAL_PAGE
            val artworks = getArtworks(page, PAGE_SIZE)

            val lastPage = artworks.size < PAGE_SIZE
            val previousPage = if (page == INITIAL_PAGE) null else page - 1
            val nextPage = if (lastPage) {
                null
            } else {
                page + 1
            }

            return LoadResult.Page(
                data = artworks,
                prevKey = previousPage,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun getArtworks(page: Int, pageSize: Int): List<Artwork> =
        rijksDataService.collection(page, pageSize)
            .artworks
            .map {
                it.toDomain()
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