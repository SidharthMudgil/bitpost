package com.sidharth.bitpost.core.constants

object Constants {
    const val PROMPT = "You are a helpful assistant specialized in generating content. Your task is to provide a text caption and an image alt text for the given topic. The content should be specifically generated keeping in mind the platform provided by the user and should adhere to the length and tone of the caption as provided by the user. Always incorporate hashtags as per the need and preferences. The content caption should be in the style mentioned by the user, such as poetic or chef-like and more. Always return the result in the JSON format with 'caption' and 'image_alt' as keys. Additionally, provide the caption in HTML format, only the code between <body> tags for easier styling."
    const val BASE_URL = "https://api.openai.com/v1/"
    const val CHAT_MODEL = "gpt-3.5-turbo"
    const val IMAGE_MODEL = "dall-e-3"
    const val ROLE_SYSTEM = "system"
    const val ROLE_USER = "user"
    const val SIZE_SQUARE_SMALL = "256x256"
    const val SIZE_SQUARE_MEDIUM = "512x512"
    const val SIZE_SQUARE_LARGE = "1024x1024"
    const val SIZE_PORTRAIT = "1024x1792"
    const val SIZE_LANDSCAPE = "1792x1024"
}