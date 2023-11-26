package com.sidharth.bitpost.di

import com.sidharth.bitpost.domain.repository.ContentRepository
import com.sidharth.bitpost.domain.usecase.GenerateContentUseCase
import com.sidharth.bitpost.domain.usecase.GenerateContentUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGenerateContentUseCase(
        contentRepository: ContentRepository
    ): GenerateContentUseCase {
        return GenerateContentUseCaseImpl(contentRepository)
    }
}