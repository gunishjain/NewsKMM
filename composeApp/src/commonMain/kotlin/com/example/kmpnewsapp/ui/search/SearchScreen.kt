package com.example.kmpnewsapp.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kmpnewsapp.data.repository.OnlineNewsRepository
import com.example.kmpnewsapp.theme.mediumPadding
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.common.EmptyContent
import com.example.kmpnewsapp.ui.common.ShimmerEffect
import com.example.kmpnewsapp.ui.headlines.HeadlineViewModel
import com.example.kmpnewsapp.ui.search.component.SearchBarScreen
import com.example.kmpnewsapp.utils.articles

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
                    ArticleListScreen(articles,navController)
                }
            },
            onError = {
                EmptyContent(it)
            }
        )


    }

}