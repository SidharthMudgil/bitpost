package com.sidharth.bitpost.data.remote

import com.sidharth.bitpost.data.repository.ChatPrompt
import com.sidharth.bitpost.data.repository.ImagePrompt
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface BitPostService {

    @POST("chat/completions")
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    suspend fun generateContentResult(@Body prompt: ChatPrompt): Response<ResponseBody>

    @POST("images/generations")
    @Headers("Authorization: Bearer ${BuildConfig.API_KEY}")
    suspend fun generateImage(@Body prompt: ImagePrompt): Response<ResponseBody>
}