package com.jishojose.newsfeed.mapper

import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.model.NewsResponse
import com.jishojose.newsfeed.model.Pagination
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NewsArticleMapperImplTest {

    private val mapper = NewsArticleMapperImpl()

    @Test
    fun `convert should return list of NewsArticle when NewsResponse has data`() {

        val dummyNewsArticle = NewsArticle(
            author = "John Doe",
            title = "Breaking News: Kotlin Simplifies Android Development",
            description = "Learn how Kotlin is revolutionizing Android app development with concise syntax and powerful features.",
            url = "https://example.com/kotlin-news",
            source = "Example News",
            image = "https://example.com/images/kotlin-news.jpg",
            category = "Technology",
            language = "en",
            country = "us",
            published_at = "2025-04-28T10:00:00Z"
        )
        // Arrange
        val expectedArticles = listOf(
            dummyNewsArticle,
            dummyNewsArticle,
        )
        val newsResponse = NewsResponse(
            pagination = Pagination(limit = 10, offset = 0, count = 2, total = 2),
            data = expectedArticles)

        // Act
        val result = mapper.convert(newsResponse)

        // Assert
        assertEquals(expectedArticles, result)
    }

    @Test
    fun `convert should return empty list when NewsResponse is null`() {
        // Act
        val result = mapper.convert(null)

        // Assert
        assertTrue(result.isEmpty())
    }

    @Test
    fun `convert should return empty list when NewsResponse data is null`() {
        // Arrange
        val newsResponse = NewsResponse.empty

        // Act
        val result = mapper.convert(newsResponse)

        // Assert
        assertTrue(result.isEmpty())
    }
}
