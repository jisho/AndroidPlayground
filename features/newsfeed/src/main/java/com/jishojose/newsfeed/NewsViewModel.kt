package com.jishojose.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _news = MutableStateFlow(listOf<NewsArticle>())
    val news: StateFlow<List<NewsArticle>> = _news

    init {
        viewModelScope.launch {
            _news.value = getNewsUseCase()
        }
    }
}
