package com.jishojose.newsfeed.di

import com.jishojose.newsfeed.mapper.NewsArticleMapper
import com.jishojose.newsfeed.mapper.NewsArticleMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsMapperModule {

    @Binds
    @Singleton
    abstract fun bindNewsArticleMapper(
        impl:NewsArticleMapperImpl
    ): NewsArticleMapper
}
