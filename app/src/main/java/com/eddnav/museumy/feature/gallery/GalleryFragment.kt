package com.eddnav.museumy.feature.gallery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.FragmentGalleryBinding
import com.eddnav.museumy.util.LoadStateAdapter
import com.eddnav.museumy.util.SpacingItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding

    private val viewModel: GalleryViewModel by viewModel()
    private val adapter = GalleryItemAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)
        setupGalleryList()
        setupTryAgainButton()
    }

    private fun setupGalleryList() {
        with(adapter) {
            addLoadStateListener {
                binding.progressIndicator.isVisible = it.refresh is LoadState.Loading
                binding.errorContainer.isVisible = it.refresh is LoadState.Error
            }
        }
        val concatAdapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter(adapter::retry)
        )
        with(binding.galleryList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = concatAdapter
            addItemDecoration(
                SpacingItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.spacing_m),
                    resources.getDimensionPixelSize(R.dimen.spacing_xs)
                )
            )
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.galleryItemPagingDataFlow.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun setupTryAgainButton() {
        binding.tryAgainButton.setOnClickListener {
            adapter.refresh()
        }
    }
}