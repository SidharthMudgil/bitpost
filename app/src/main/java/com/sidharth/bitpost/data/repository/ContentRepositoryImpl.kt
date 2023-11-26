package com.sidharth.bitpost.data.repository

import com.google.gson.Gson
import com.sidharth.bitpost.domain.repository.ContentRepository
import com.sidharth.bitpost.data.remote.ChomuResponse
import com.sidharth.bitpost.data.remote.BitPostService
import com.sidharth.chomu.domain.model.Prompt
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val bitPostService: BitPostService
) : ContentRepository {

    override suspend fun generateResult(prompt: Prompt) = flow {
        emit(PromptResult.Loading)
        val promptResult = bitPostService.getPromptResult(prompt)
        val response = promptResult.body()?.string()

        if (promptResult.isSuccessful) {
            if (response.isNullOrBlank().not()) {
                val res = Gson().fromJson(response, ChomuResponse::class.java)
                emit(PromptResult.Success(res.choices[0].message.content))
            } else {
                emit(PromptResult.Error)
            }
        } else {
            emit(PromptResult.Error)
        }
    }
}