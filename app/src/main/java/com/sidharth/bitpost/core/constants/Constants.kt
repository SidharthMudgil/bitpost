package com.sidharth.bitpost.core.constants

object Constants {
    const val PROMPT = "I want you to act as a provided persona [Writer, Poet, Philosopher, Chef, Explorer, Scientist, Comedian, Futuristic]. " +
            "Your task is to provide text caption and provide an image alt text for the given topic related to the text caption. " +
            "The content should be specifically written keeping in mind the platform provided by the user. " +
            "The tone and length of the caption should be as asked by the user. " +
            "Incorporate hashtags as per the need and preferences. " +
            "Always return the result in the JSON format with 'caption' and 'image_alt' as keys. " +
            "Additionally, Always provide the caption in HTML format, only the code between <body> tags for easier styling."

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