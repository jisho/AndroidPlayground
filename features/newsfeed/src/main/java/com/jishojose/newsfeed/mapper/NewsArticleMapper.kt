package com.jishojose.newsfeed.mapper

import com.jishojose.newsfeed.model.NewsResponse
import javax.inject.Inject

interface NewsArticleMapper {
    fun convert(newsArticleResponse: NewsResponse?): List<com.jishojose.newsfeed.model.NewsArticle>
}

class NewsArticleMapperImpl @Inject constructor() : NewsArticleMapper {
    override fun convert(newsArticleResponse: NewsResponse?): List<com.jishojose.newsfeed.model.NewsArticle> {
        return newsArticleResponse?.data ?: emptyList()
    }
}
