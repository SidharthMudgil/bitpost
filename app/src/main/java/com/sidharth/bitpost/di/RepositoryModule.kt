package com.sidharth.bitpost.di

import com.sidharth.bitpost.data.remote.BitPostService
import com.sidharth.bitpost.data.repository.ContentRepositoryImpl
import com.sidharth.bitpost.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideContentRepository(bitPostService: BitPostService): ContentRepository {
        return ContentRepositoryImpl(bitPostService)
    }
}