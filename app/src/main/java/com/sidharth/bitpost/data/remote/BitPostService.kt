package com.sidharth.bitpost.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BitPostService {

    @POST("chat/completions")
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    suspend fun generateContentResult(@Body prompt: ChatPrompt): Response<ChatResponse>

    @POST("images/generations")
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    suspend fun generateImage(@Body prompt: ImagePrompt): Response<ImageResponse>
}