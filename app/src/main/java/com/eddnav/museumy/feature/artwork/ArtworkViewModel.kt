package com.eddnav.museumy.feature.artwork

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.usecase.GetArtwork
import com.eddnav.museumy.util.Error
import com.eddnav.museumy.util.Loaded
import com.eddnav.museumy.util.Loading
import com.eddnav.museumy.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtworkViewModel(
    private val identifier: String,
    private val getArtwork: GetArtwork
) : ViewModel() {

    private val mutableUiStateStateFlow = MutableStateFlow<UiState<Artwork>>(Loading)
    val uiStateStateFlow: StateFlow<UiState<Artwork>> = mutableUiStateStateFlow

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            try {
                mutableUiStateStateFlow.value = Loading
                mutableUiStateStateFlow.value = Loaded(getArtwork(identifier))
            } catch (e: Exception) {
                mutableUiStateStateFlow.value = Error(e)
            }
        }
    }
}