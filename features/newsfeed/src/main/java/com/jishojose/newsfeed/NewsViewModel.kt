package com.jishojose.newsfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jishojose.core.android.util.StringResource
import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    stringResource: StringResource,
) : ViewModel() {

    private val _news = MutableStateFlow(listOf<NewsArticle>())
    val news: StateFlow<List<NewsArticle>> = _news
    private val message = stringResource.get(R.string.app_name)

    init {
        viewModelScope.launch {
            try {
                val articles = getNewsUseCase()
                _news.value = articles
            } catch (e: Exception) {
                // Handle error case here
                _news.value = emptyList()
            }
        }
    }
}
