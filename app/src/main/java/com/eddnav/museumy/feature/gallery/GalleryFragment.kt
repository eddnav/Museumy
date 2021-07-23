package com.eddnav.museumy.feature.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.FragmentGalleryBinding
import com.eddnav.museumy.paging.GalleryItemPagingSource
import com.eddnav.museumy.repository.ArtworkRepository
import com.eddnav.museumy.util.SpacingItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private lateinit var binding: FragmentGalleryBinding
    private val adapter = GalleryItemAdapter()
    private val repository: ArtworkRepository by inject()
    private val source = GalleryItemPagingSource(repository)
    private val pager =
        Pager(pagingSourceFactory = { source }, config = PagingConfig(pageSize = 30))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGalleryBinding.bind(view)
        setupGalleryList()
    }

    private fun setupGalleryList() {
        with(binding.galleryList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@GalleryFragment.adapter
            addItemDecoration(SpacingItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_m)))
        }
        lifecycleScope.launch {
            pager.flow.collect {
                adapter.submitData(it)
            }
        }
    }
}