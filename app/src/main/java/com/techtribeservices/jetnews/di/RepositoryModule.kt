package com.techtribeservices.jetnews.di

import com.techtribeservices.jetnews.data.Repository.NewsRepositoryImpl
import com.techtribeservices.jetnews.data.web.NewsApi
import com.techtribeservices.jetnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }
}