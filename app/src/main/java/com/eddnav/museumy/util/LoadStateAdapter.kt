package com.eddnav.museumy.util

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.paging.LoadStateAdapter as PagingLoadStateAdapter

class LoadStateAdapter(
    private val retry: () -> Unit
) : PagingLoadStateAdapter<LoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoadStateViewHolder(
        LoadStateItemView(parent.context) {
            retry()
        }
    )

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.view.setLoadingState(loadState)
    }

    class LoadStateViewHolder(val view: LoadStateItemView) : RecyclerView.ViewHolder(view)
}
