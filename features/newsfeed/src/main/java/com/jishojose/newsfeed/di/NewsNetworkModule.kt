package com.jishojose.newsfeed.di

import com.jishojose.newsfeed.apiservice.NewsApiService
import com.jishojose.newsfeed.repository.NewsRepository
import com.jishojose.newsfeed.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {

    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    fun provideNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository = impl
}
