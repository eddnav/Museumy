package com.eddnav.museumy.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.eddnav.museumy.databinding.ViewLoadStateItemBinding

class LoadStateItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val retryClickListener: (() -> Unit)? = null
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewLoadStateItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        binding.tryAgainButton.setOnClickListener() {
            retryClickListener?.invoke()
        }
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
    }

    fun setLoadingState(loadingState: LoadState) {
        with(binding) {
            progressIndicator.isVisible = loadingState is LoadState.Loading
            errorContainer.isVisible = loadingState is LoadState.Error
        }
    }
}