package com.eddnav.museumy.feature.artwork

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.FragmentArtworkBinding
import com.eddnav.museumy.domain.model.Artwork
import com.eddnav.museumy.util.Error
import com.eddnav.museumy.util.Loaded
import com.eddnav.museumy.util.Loading
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ArtworkFragment : Fragment(R.layout.fragment_artwork) {

    private lateinit var binding: FragmentArtworkBinding

    private val navArgs: ArtworkFragmentArgs by navArgs()
    private val viewModel: ArtworkViewModel by viewModel { parametersOf(navArgs.identifier) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtworkBinding.bind(view)
        setupUiStateCollection()
        setupTryAgainButton()
    }

    private fun setupUiStateCollection() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiStateStateFlow.collect {
                when (it) {
                    Loading -> showLoadingState()
                    is Error -> showErrorState()
                    is Loaded -> showLoadedState(it.value)
                }
            }
        }
    }

    private fun setupTryAgainButton() {
        binding.tryAgainButton.setOnClickListener {
            viewModel.refresh()
        }
    }

    private fun showErrorState() {
        binding.errorContainer.isVisible = true
        binding.progressIndicator.isVisible = false
        binding.loadedStateViews.isVisible = false
    }

    private fun showLoadingState() {
        binding.progressIndicator.isVisible = true
        binding.errorContainer.isVisible = false
        binding.loadedStateViews.isVisible = false
    }

    private fun showLoadedState(artwork: Artwork) {
        binding.loadedStateViews.isVisible = true
        binding.progressIndicator.isVisible = false
        binding.errorContainer.isVisible = false
        binding.image.load(artwork.imageUrl) {
            crossfade(true)
            fallback(R.drawable.ic_palette_48)
        }
        binding.title.text = artwork.title
        binding.subtitle.text = getString(R.string.artwork_subtitle, artwork.physicalMedium, artwork.subtitle)
        binding.description.text = artwork.description
    }
}