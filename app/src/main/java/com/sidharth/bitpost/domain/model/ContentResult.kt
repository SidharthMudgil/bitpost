package com.sidharth.bitpost.domain.model

sealed class ContentResult {
    data class Success(val content: Content) : ContentResult()
    data object Loading : ContentResult()
    data object Error : ContentResult()
}