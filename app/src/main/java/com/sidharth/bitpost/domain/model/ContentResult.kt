package com.sidharth.bitpost.domain.model

sealed class ContentResult {
    data class Success(val content: Content) : ContentResult()
    data class Loading(val data: String) : ContentResult()
    data object Error : ContentResult()
}