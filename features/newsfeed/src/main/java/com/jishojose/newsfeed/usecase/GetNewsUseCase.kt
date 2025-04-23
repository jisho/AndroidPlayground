package com.jishojose.newsfeed.usecase

import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<NewsArticle> = repository.getNews()
}

