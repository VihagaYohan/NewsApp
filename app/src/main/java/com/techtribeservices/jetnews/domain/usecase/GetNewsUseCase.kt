package com.techtribeservices.jetnews.domain.usecase

import com.techtribeservices.jetnews.data.response.NewsResponse
import com.techtribeservices.jetnews.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository) {

    suspend operator fun invoke(
        language: String,
        text: String?,
        country: String?
    ): NewsResponse {
        val response = newsRepository.getNews(language, text, country)
        if(response.body() == null) {
            if(response.code == 404) {
                throw Exception("no news found")
            } else if(response.code == 500) {
                throw Exception("server error")
            } else if (response.code == 401) {
                throw Exception("unauthorized")
            } else if (response.code == 400) {
                throw Exception("bad request")
            } else
                throw Exception("no news found")
        }
        return newsRepository.getNews(language, text, country).body()!!
    }
}