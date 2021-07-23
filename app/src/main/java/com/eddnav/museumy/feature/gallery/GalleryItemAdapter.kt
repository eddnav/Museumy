package com.eddnav.museumy.feature.gallery

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

class GalleryItemAdapter :
    PagingDataAdapter<GalleryItem, GalleryItemAdapter.GalleryItemViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
       // 0 ->
        1 -> GalleryItemViewHolder(ArtworkItemView(parent.context))
        else -> throw IllegalStateException()
    }

    override fun getItemViewType(position: Int) = when(getItem(position)) {
        is AuthorNameItem -> 0
        is ArtworkItem -> 1
        null -> throw IllegalStateException()
    }

    class GalleryItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object Comparator : ItemCallback<GalleryItem>() {

        override fun areItemsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean {
            return when (oldItem) {
                is AuthorNameItem -> newItem is AuthorNameItem && oldItem.name == newItem.name
                is ArtworkItem -> newItem is ArtworkItem && oldItem.artwork.identifier == newItem.artwork.identifier
            }
        }

        override fun areContentsTheSame(oldItem: GalleryItem, newItem: GalleryItem): Boolean =
            oldItem == newItem
    }
}