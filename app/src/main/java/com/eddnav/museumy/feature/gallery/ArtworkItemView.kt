package com.eddnav.museumy.feature.gallery

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.eddnav.museumy.databinding.ViewArtworkItemBinding

class ArtworkItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewArtworkItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setItem(item: ArtworkItem) {
        binding.title.text = item.artwork.title
    }
}