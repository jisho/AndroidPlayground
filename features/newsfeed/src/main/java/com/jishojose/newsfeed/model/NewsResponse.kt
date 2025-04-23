package com.jishojose.newsfeed.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val pagination: Pagination,
    val data: List<NewsArticle>
)

@JsonClass(generateAdapter = true)
data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class NewsArticle(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val source: String?,
    val image: String?,
    val category: String?,
    val language: String?,
    val country: String?,
    val published_at: String?
)
