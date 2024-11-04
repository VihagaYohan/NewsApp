package com.techtribeservices.jetnews.data.Repository

import com.techtribeservices.jetnews.data.response.NewsResponse
import com.techtribeservices.jetnews.data.web.NewsApi
import com.techtribeservices.jetnews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api: NewsApi): NewsRepository {

    override suspend fun getNews(language: String, text: String?, country: String?): NewsResponse {
//        val response = text?.let { api.getNews(language, it, country) } ?: api.getNews(language, "", country)
//        return response.body()!!
        if(text != null) {
            val response = api.getNews(language, text, country)
            return response.body()!!
        }
    }
}