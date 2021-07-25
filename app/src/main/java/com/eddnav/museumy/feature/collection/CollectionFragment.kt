package com.eddnav.museumy.feature.collection

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.FragmentCollectionBinding
import com.eddnav.museumy.domain.model.CollectionEntry
import com.eddnav.museumy.util.LoadStateAdapter
import com.eddnav.museumy.util.SpacingItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionFragment : Fragment(R.layout.fragment_collection) {

    private lateinit var binding: FragmentCollectionBinding

    private val viewModel: CollectionViewModel by viewModel()
    private val adapter = CollectionItemAdapter(::navigateToArtwork)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCollectionBinding.bind(view)
        setupCollectionList()
        setupTryAgainButton()
    }

    private fun setupCollectionList() {
        with(adapter) {
            addLoadStateListener {
                binding.progressIndicator.isVisible = it.refresh is LoadState.Loading
                binding.errorContainer.isVisible = it.refresh is LoadState.Error
            }
        }
        val concatAdapter = adapter.withLoadStateFooter(
            footer = LoadStateAdapter(adapter::retry)
        )
        with(binding.collectionList) {
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
            viewModel.collectionItemPagingDataFlow.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun setupTryAgainButton() {
        binding.tryAgainButton.setOnClickListener {
            adapter.refresh()
        }
    }

    private fun navigateToArtwork(collectionEntry: CollectionEntry) {
        findNavController()
            .navigate(CollectionFragmentDirections.actionCollectionFragmentToArtworkFragment())
    }
}