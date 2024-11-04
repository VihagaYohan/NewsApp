package com.techtribeservices.jetnews.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techtribeservices.jetnews.data.model.News
import com.techtribeservices.jetnews.data.response.NewsResponse
import com.techtribeservices.jetnews.domain.usecase.GetNewsUseCase
import com.techtribeservices.jetnews.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase): ViewModel() {

        private val _state =  MutableStateFlow<State<NewsResponse >>(State.Loading)
        val state = _state as StateFlow<State<NewsResponse >>

        var job: Job? = null

        init {
            getNews()
        }

         fun getNews(text: String? = null, country:String? = null) {
             job?.cancel()
             job = viewModelScope.launch {
                _state.tryEmit(State.Loading)
                try {
                    val result = getNewsUseCase.invoke("en",text, country)
                    _state.tryEmit(State.Success(result))
                } catch (e: Exception) {
                    _state.tryEmit(State.Error(e.message.toString()))
                }
            }
        }
}