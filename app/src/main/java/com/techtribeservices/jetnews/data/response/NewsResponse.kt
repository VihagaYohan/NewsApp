package com.techtribeservices.jetnews.data.response

import com.techtribeservices.jetnews.data.model.News

data class NewsResponse(val news: List<News>?,
                        val number: Int = 0,
                        val offset: Int = 0,
                        val available: Int = 0)