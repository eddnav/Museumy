package com.eddnav.museumy.feature.gallery

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.eddnav.museumy.databinding.ViewArtworkItemBinding
import com.google.android.material.card.MaterialCardView

class ArtworkItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding = ViewArtworkItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
    }

    fun setItem(item: ArtworkItem) {
        binding.title.text = item.artwork.title
        binding.identifier.text = item.artwork.identifier
    }
}