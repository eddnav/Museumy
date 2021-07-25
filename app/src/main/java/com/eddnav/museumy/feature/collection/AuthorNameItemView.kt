package com.eddnav.museumy.feature.collection

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.eddnav.museumy.databinding.ViewAuthorNameItemBinding

class AuthorNameItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewAuthorNameItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }

    fun setItem(item: AuthorNameItem) {
        binding.title.text = item.name
    }
}