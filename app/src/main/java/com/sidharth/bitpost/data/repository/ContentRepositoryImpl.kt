package com.sidharth.bitpost.data.repository

import com.google.gson.Gson
import com.sidharth.bitpost.core.constants.Constants
import com.sidharth.bitpost.data.remote.BitPostService
import com.sidharth.bitpost.data.remote.ChatResponse
import com.sidharth.bitpost.data.remote.ImageResponse
import com.sidharth.bitpost.domain.model.Content
import com.sidharth.bitpost.domain.model.ContentResult
import com.sidharth.bitpost.domain.repository.ContentRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val bitPostService: BitPostService
) : ContentRepository {

    override suspend fun generateContent(purpose: String) = flow {
        val chatPrompt = ChatPrompt(
            model = Constants.CHAT_MODEL,
            messages = listOf(
                Message(
                    role = Constants.ROLE_SYSTEM,
                    content = Constants.PROMPT
                ),
                Message(
                    role = Constants.ROLE_USER,
                    content = purpose
                )
            )
        )

        val chatPromptResult = bitPostService.generateContentResult(chatPrompt)
        val chatResponseBody = chatPromptResult.body()?.string()

        if (chatPromptResult.isSuccessful && chatResponseBody.isNullOrBlank().not()) {
            val chatResponse = Gson().fromJson(chatResponseBody, ChatResponse::class.java)
            val content = chatResponse.choices[0].message.content
            val result = Gson().fromJson(content, ChatResult::class.java)

            emit(ContentResult.Loading(result.caption))

            val imagePrompt = ImagePrompt(
                model = Constants.IMAGE_MODEL,
                prompt = result.imageAlt,
                n = 1,
                size = Constants.SIZE_SQUARE_LARGE
            )

            val imagePromptResult = bitPostService.generateImage(imagePrompt)
            val imageResponseBody = imagePromptResult.body()?.string()

            if (imagePromptResult.isSuccessful && imageResponseBody.isNullOrBlank().not()) {
                val imageResponse = Gson().fromJson(imageResponseBody, ImageResponse::class.java)
                emit(
                    ContentResult.Success(
                        Content(
                            caption = result.caption,
                            image = imageResponse.data[0].url,
                        )
                    )
                )
            } else {
                emit(ContentResult.Error(imagePromptResult.errorBody()?.string()?.plus("\nimage error")))
            }

        } else {
            emit(ContentResult.Error(chatPromptResult.errorBody()?.string()?.plus("\nchat error")))
        }
    }
}