package com.jishojose.newsfeed.repository

import com.jishojose.newsfeed.apiservice.NewsApiService
import com.jishojose.newsfeed.mapper.NewsArticleMapper
import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.model.NewsResponse
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(useCache: Boolean = true): List<NewsArticle>
}
class NewsRepositoryImpl @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsArticleMapper: NewsArticleMapper
) : NewsRepository {
    private val newsCache = AtomicReference<NewsResponse>()
    override suspend fun getNews(useCache: Boolean): List<NewsArticle> {
        val cachedResponse = if (useCache) newsCache.get() else null
        val response = cachedResponse ?: newsApiService.getNews("b56268b47fa3261c3511863a8f2fee74")

        if (useCache && cachedResponse == null) {
            newsCache.set(response)
        }

        return newsArticleMapper.convert(response)
    }

}
