package com.eddnav.museumy.feature.collection

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import com.eddnav.museumy.domain.model.CollectionEntry

class CollectionItemAdapter(
    private val onCollectionEntryItemClickListener: (collectionEntryItem: CollectionEntry) -> Unit
) :
    PagingDataAdapter<CollectionItem, CollectionItemAdapter.CollectionItemViewHolder>(Comparator) {

    override fun onBindViewHolder(holder: CollectionItemViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is AuthorNameItem -> (holder.itemView as AuthorNameItemView).setItem(item)
            is CollectionEntryItem -> (holder.itemView as CollectionEntryItemView).setItem(item)
            null -> throw IllegalStateException()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionItemViewHolder {
        val context = parent.context
        return CollectionItemViewHolder(
            when (viewType) {
                0 -> AuthorNameItemView(context)
                1 -> CollectionEntryItemView(context, onClickListener = onCollectionEntryItemClickListener)
                else -> throw IllegalStateException()
            }
        )
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is AuthorNameItem -> 0
        is CollectionEntryItem -> 1
        null -> throw IllegalStateException()
    }

    class CollectionItemViewHolder(view: View) : RecyclerView.ViewHolder(view)

    object Comparator : ItemCallback<CollectionItem>() {

        override fun areItemsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean {
            return when (oldItem) {
                is AuthorNameItem -> newItem is AuthorNameItem && oldItem.name == newItem.name
                is CollectionEntryItem -> newItem is CollectionEntryItem && oldItem.collectionEntry.identifier == newItem.collectionEntry.identifier
            }
        }

        override fun areContentsTheSame(oldItem: CollectionItem, newItem: CollectionItem): Boolean =
            oldItem == newItem
    }
}