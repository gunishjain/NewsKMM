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
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.no_news
import kmp_news_app.composeapp.generated.resources.type_to_search
import org.jetbrains.compose.resources.stringResource

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
                    searchViewModel.searchNews(query)
                }
            }
        )
        uiState.DisplayResult(
            onIdle = {
                EmptyContent(
                    message = stringResource(Res.string.type_to_search),
                    icon = Res.drawable.ic_browse,
                    isOnRetryBtnVisible = false
                )
            },

            onLoading =  {
                ShimmerEffect()
            },
            onSuccess = {articleList->
                if(articleList.isEmpty()){
                    EmptyContent(
                        message = stringResource(Res.string.no_news),
                        icon = Res.drawable.ic_browse,
                    )
                } else {
                    ArticleListScreen(articleList,navController)
                }
            },
            onError = {
                EmptyContent(
                    message = it,
                    icon = Res.drawable.ic_browse,
                    onRetryBtnClick = {
                        if(searchQuery.trim().isNotEmpty()){
                            searchViewModel.searchNews(searchQuery)
                        }
                    }
                )
            }
        )


    }

}