package com.sidharth.bitpost.data.remote

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("created") val created: Long,
    @SerializedName("data") val data: List<ImageData>
)

data class ImageData(
    @SerializedName("revised_prompt") val revisedPrompt: String,
    @SerializedName("url") val url: String
)