package com.sidharth.bitpost.core.constants

object Constants {
    const val PROMPT = "You are a helpful assistant specialized in generating content. Your task is to provide a text caption and an image alt text for the given topic. The content should be specifically generated keeping in mind the platform provided by the user and should adhere to the length and tone of the caption as provided by the user. If the user mentions any hashtags or writing style, incorporate those as well. Always return the result in the JSON format with 'caption', 'hashtags', and 'image_alt' as keys."
    const val BASE_URL = "https://api.openai.com/v1/"
    const val CHAT_MODEL = "gpt-3.5-turbo"
    const val IMAGE_MODEL = "dall-e-3"
    const val ROLE_SYSTEM = "system"
    const val ROLE_USER = "user"
    const val SIZE_SQUARE = "1080x1080"
    const val SIZE_PORTRAIT = "1080x1920"
    const val SIZE_LANDSCAPE = "1920x1080"
}