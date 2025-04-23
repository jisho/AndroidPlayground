package com.jishojose.newsfeed.apiservice

import com.jishojose.newsfeed.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v1/news")
    suspend fun getNews(
        @Query("access_key") accessKey: String,
        @Query("languages") language: String = "en"
    ): NewsResponse
}

