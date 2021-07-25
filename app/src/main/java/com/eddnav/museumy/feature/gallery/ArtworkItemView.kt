package com.eddnav.museumy.feature.gallery

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import coil.load
import com.eddnav.museumy.R
import com.eddnav.museumy.databinding.ViewArtworkItemBinding
import com.eddnav.museumy.domain.model.Artwork
import com.google.android.material.card.MaterialCardView

class ArtworkItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val onClickListener: (artwork: Artwork) -> Unit = {}
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
        with(binding) {
            val artwork = item.artwork
            title.text = artwork.title
            identifier.text = artwork.identifier
            image.load(artwork.imageUrl) {
                crossfade(true)
                fallback(R.drawable.ic_palette_48)
            }
        }
        setOnClickListener {
            onClickListener(item.artwork)
        }
    }
}