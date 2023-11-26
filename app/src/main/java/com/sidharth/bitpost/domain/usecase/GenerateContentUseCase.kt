package com.sidharth.bitpost.domain.usecase

import com.sidharth.bitpost.domain.model.ContentResult
import kotlinx.coroutines.flow.Flow

interface GenerateContentUseCase {
    suspend fun invoke(purpose: String): Flow<ContentResult>
}