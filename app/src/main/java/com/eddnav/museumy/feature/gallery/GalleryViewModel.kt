package com.eddnav.museumy.feature.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.eddnav.museumy.usecase.GetGalleryItemPagingDataFlow

class GalleryViewModel(
    getGalleryItemPagingDataFlow: GetGalleryItemPagingDataFlow
) : ViewModel() {

    val galleryItemPagingDataFlow = getGalleryItemPagingDataFlow()
        .cachedIn(viewModelScope)
}