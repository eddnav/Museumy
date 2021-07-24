package com.eddnav.museumy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.eddnav.museumy.data.remote.RijksDataService
import com.eddnav.museumy.repository.paging.ArtworkPagingSource

class ArtworkRepository(private val rijksDataService: RijksDataService) {

   /**
    * This value is not used, [ArtworkPagingSource] ignores
    * [androidx.paging.PagingSource.LoadParams.loadSize] due to the nature of the API's
    * pagination mechanism.
    */
   private val pagingConfig = PagingConfig(pageSize = 10)

   fun getArtworkPager() = Pager(
      config = pagingConfig,
      pagingSourceFactory = { ArtworkPagingSource(rijksDataService) }
   )
}