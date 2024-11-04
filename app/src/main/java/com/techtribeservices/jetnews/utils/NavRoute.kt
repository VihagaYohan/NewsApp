package com.techtribeservices.jetnews.utils

import com.google.gson.Gson
import com.techtribeservices.jetnews.data.model.News
import java.net.URLDecoder
import java.net.URLEncoder

object NavRoute {

    fun createNewsDetailsRoute(news: News): String {
        val encodedImage = URLEncoder.encode(news.image, "UTF-8")
        val encodedUrl = URLEncoder.encode(news.url, "UTF-8")
        val tempNews = news.copy(image = encodedImage, url = encodedUrl)
        val gson = Gson().toJson(tempNews)
        return "/details/news=${gson}"
    }

    fun getNewsFromRoute(json:String): News {
        val news = Gson().fromJson(json, News::class.java)
        val decodedImage = URLDecoder.decode(news.image, "UTF-8")
        val decodedUrl = URLDecoder.decode(news.url, "UTF-8")
        return news.copy(image = decodedImage, url = decodedUrl)
    }
}