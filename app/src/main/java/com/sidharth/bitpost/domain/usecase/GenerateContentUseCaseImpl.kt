package com.sidharth.bitpost.domain.usecase

import com.sidharth.bitpost.domain.repository.ContentRepository
import com.sidharth.bitpost.domain.model.ContentResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateContentUseCaseImpl @Inject constructor(
    private val contentRepository: ContentRepository
) : GenerateContentUseCase {

    override suspend fun invoke(purpose: String): Flow<ContentResult> {
        TODO()
//        return promptRepository.generateResult(
//            Prompt(
//                model = Constants.MODEL,
//                messages = listOf(
//                    Message(
//                        role = "system",
//                        content = command
//                    ),
//                    Message(
//                        role = "user",
//                        content = message
//                    ),
//                )
//            )
//        )
    }
}