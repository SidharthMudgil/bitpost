package com.sidharth.bitpost.domain.usecase

import com.sidharth.bitpost.domain.model.ContentResult
import com.sidharth.bitpost.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateContentUseCaseImpl @Inject constructor(
    private val contentRepository: ContentRepository
) : GenerateContentUseCase {

    override suspend fun invoke(purpose: String): Flow<ContentResult> {
        return contentRepository.generateContent(purpose)
    }
}