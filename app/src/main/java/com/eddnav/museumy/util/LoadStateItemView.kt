package com.eddnav.museumy.util

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.eddnav.museumy.databinding.ViewLoadStateItemBinding

class LoadStateItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewLoadStateItemBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT,
        )
        gravity = Gravity.CENTER
    }

    fun setLoadingState(loadingState: LoadState) {
        isVisible = loadingState is LoadState.Loading
    }
}