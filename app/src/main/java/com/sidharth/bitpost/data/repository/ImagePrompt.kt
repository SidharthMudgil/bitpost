package com.sidharth.bitpost.data.repository

data class ImagePrompt(
    val model: String,
    val prompt: String,
    val n: Int,
    val size: String
)