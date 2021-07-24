package com.eddnav.museumy.feature.gallery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.FragmentGalleryBinding
import com.eddnav.museumy.paging.GalleryItemPagingSource
import com.eddnav.museumy.repository.ArtworkRepository
import com.eddnav.museumy.util.LoadStateAdapter
import com.eddnav.museumy.util.SpacingItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding
    
    private val adapter = GalleryItemAdapter()
    private val repository: ArtworkRepository by inject()

    /**
     * This value is not used, GallerItemPagingSource ignores
     * [androidx.paging.PagingSource.LoadParams.loadSize] due to the nature of the API's
     * pagination mechanism.
     */
    private val pagingConfig = PagingConfig(pageSize = 10)
    private val pager =
        Pager(
            pagingSourceFactory = { GalleryItemPagingSource(repository) },
            config = pagingConfig
        )

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
        lifecycleScope.launch {
            pager.flow.collect {
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