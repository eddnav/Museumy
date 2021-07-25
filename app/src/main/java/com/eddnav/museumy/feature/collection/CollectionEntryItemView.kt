package com.eddnav.museumy.feature.collection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import coil.load
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.ViewCollectionEntryItemBinding
import com.eddnav.museumy.domain.model.CollectionEntry
import com.google.android.material.card.MaterialCardView

class CollectionEntryItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val onClickListener: (collectionEntry: CollectionEntry) -> Unit = {}
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding = ViewCollectionEntryItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

    }

    fun setItem(item: CollectionEntryItem) {
        with(binding) {
            val entry = item.collectionEntry
            title.text = entry.title
            identifier.text = entry.identifier
            image.load(entry.imageUrl) {
                crossfade(true)
                fallback(R.drawable.ic_palette_48)
            }
        }
        setOnClickListener {
            onClickListener(item.collectionEntry)
        }
    }
}