package com.jishojose.newsfeed.di

import com.jishojose.newsfeed.apiservice.NewsApiService
import com.jishojose.newsfeed.repository.NewsRepository
import com.jishojose.newsfeed.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {
    @Provides
    fun provideNewsApiService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl("http://api.mediastack.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Provides
    fun provideNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository = impl
}
