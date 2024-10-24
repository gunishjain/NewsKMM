package com.example.kmpnewsapp.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpnewsapp.data.model.Article
import com.example.kmpnewsapp.utils.Resource
import com.example.kmpnewsapp.utils.articles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Loading)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow


    init {
        getHeadlines()
    }

    private fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
//                val articleList = articles
                val articleList = emptyList<Article>()
                _newsStateFlow.emit(Resource.Success(articleList))

            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }

        }
    }

}