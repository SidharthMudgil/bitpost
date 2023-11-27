package com.sidharth.bitpost.data.repository

data class ChatPrompt(
    val model: String,
    val messages: List<Message>
)

data class Message(
    val role: String,
    val content: String
)

data class ChatResult(
    val caption: String,
    val hashtags: List<String>,
    val imageAlt: String
)