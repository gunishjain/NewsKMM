package com.example.kmpnewsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpnewsapp.data.model.Article
import com.example.kmpnewsapp.data.model.ErrorResponse
import com.example.kmpnewsapp.data.model.NewsResponse
import com.example.kmpnewsapp.data.repository.OnlineNewsRepository
import com.example.kmpnewsapp.utils.Resource
import com.example.kmpnewsapp.utils.articles
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val onlineNewsRepository : OnlineNewsRepository
) : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow


     fun searchNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = onlineNewsRepository.searchNews(query)
                if (httpResponse.status.value in 200..299) {
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                } else {
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }

}