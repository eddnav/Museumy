package com.eddnav.museumy.util

sealed class UiState<out T : Any>
data class Loaded<out T : Any>(val value: T) : UiState<T>()
data class Error(val exception: Exception) : UiState<Nothing>()
object Loading : UiState<Nothing>()
