package com.eddnav.museumy.feature.artwork

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.eddnav.museumy.R
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.util.Error
import com.eddnav.museumy.util.Loaded
import com.eddnav.museumy.util.Loading
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtworkFragment : Fragment(R.layout.fragment_artwork) {

    private val navArgs: ArtworkFragmentArgs by navArgs()
    private val viewModel: ArtworkViewModel by viewModel { parametersOf(navArgs.identifier) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateStateFlow.collect {
                when (it) {
                    Loading -> setupLoadingState()
                    is Error -> setupErrorState()
                    is Loaded -> setupLoadedState(it.value)
                }
            }
        }
    }

    private fun setupErrorState() {

    }

    private fun setupLoadingState() {

    }

    private fun setupLoadedState(artwork: Artwork) {

    }
}