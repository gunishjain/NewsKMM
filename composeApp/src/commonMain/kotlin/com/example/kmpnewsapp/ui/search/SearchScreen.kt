package com.example.kmpnewsapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kmpnewsapp.data.repository.OnlineNewsRepository
import com.example.kmpnewsapp.theme.mediumPadding
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.common.EmptyContent
import com.example.kmpnewsapp.ui.common.ShimmerEffect
import com.example.kmpnewsapp.ui.search.component.SearchBarScreen

@Composable
fun SearchScreen(navController: NavController) {

    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }

    val searchViewModel : SearchViewModel = viewModel { SearchViewModel(OnlineNewsRepository()) }
    val uiState by searchViewModel.newsStateFlow.collectAsState()


    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding)
    ) {
        SearchBarScreen(
            text = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            onSearch = {query->
                if(query.trim().isNotEmpty()){
                    println(query)
                    searchViewModel.searchNews(query)

                }
            }
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent("Type to Search")
            },

            onLoading =  {
                ShimmerEffect()
            },
            onSuccess = {articleList->
                if(articleList.isEmpty()){
                    EmptyContent("No News")
                } else {
                    ArticleListScreen(articleList,navController)
                }
            },
            onError = {
                EmptyContent(it)
            }
        )


    }

}