package com.jishojose.newsfeed.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val pagination: Pagination,
    val data: List<NewsArticle>
) {
    companion object {
        val empty = NewsResponse(
            pagination = Pagination(limit = 0, offset = 0, count = 0, total = 0),
            data = emptyList()
        )
    }
}

@JsonClass(generateAdapter = true)
data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val total: Int
){
    companion object {
        val empty = Pagination(limit = 0, offset = 0, count = 0, total = 0)
    }
}

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
){
    companion object{
        val empty = NewsArticle(
            author = "",
            title = "",
            description = "",
            url = "",
            source = "",
            image = "",
            category = "",
            language = "",
            country = "",
            published_at = "",
        )

    }
}
