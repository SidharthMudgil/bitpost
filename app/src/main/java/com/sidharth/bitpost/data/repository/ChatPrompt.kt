package com.sidharth.bitpost.data.repository

import com.google.gson.annotations.SerializedName

data class ChatPrompt(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResult(
    @SerializedName("caption") val caption: String,
    @SerializedName("image_alt") val imageAlt: String
)