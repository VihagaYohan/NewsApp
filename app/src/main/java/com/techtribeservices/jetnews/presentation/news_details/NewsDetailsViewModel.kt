package com.techtribeservices.jetnews.presentation.news_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtribeservices.jetnews.data.database.NewsDatabase
import com.techtribeservices.jetnews.data.model.News
import com.techtribeservices.jetnews.data.response.NewsResponse
import com.techtribeservices.jetnews.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(database: NewsDatabase): ViewModel() {

    private val _state = MutableStateFlow<State<Boolean>>(State.Loading)
    val state = _state as StateFlow<State<Boolean>>

    private val newsDao = database.newsDao()

    fun getnews() = newsDao.getNews()

    fun deleteNews() {

    }

    fun addNews(news: News) {
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
                newsDao.addNews(news)
                _state.tryEmit(State.Success(true))
            }catch(e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }
}