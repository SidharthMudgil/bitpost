package com.sidharth.bitpost.domain.repository

import com.sidharth.bitpost.domain.model.ContentResult
import kotlinx.coroutines.flow.Flow

interface ContentRepository {
    suspend fun generateContent(purpose: String): Flow<ContentResult>
}