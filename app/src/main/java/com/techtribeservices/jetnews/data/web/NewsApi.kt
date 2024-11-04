package com.techtribeservices.jetnews.data.web

import com.techtribeservices.jetnews.data.response.NewsResponse
import com.techtribeservices.jetnews.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("search-news")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("language") language: String,
        @Query("text") text: String? ,
        @Query("news-sources") newsSources: String? = "https://www.bbc.co.uk",
        @Query("apikey") apikey: String = API_KEY
    ): Response<NewsResponse>
}