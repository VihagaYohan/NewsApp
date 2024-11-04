package com.techtribeservices.jetnews.domain.repository

import com.techtribeservices.jetnews.data.response.NewsResponse

interface NewsRepository  {

    suspend fun getNews(
        language: String,
        text: String?,
        country: String?
    ): NewsResponse
}