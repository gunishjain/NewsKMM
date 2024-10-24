package com.example.kmpnewsapp.ui.headlines


import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kmpnewsapp.data.repository.OnlineNewsRepository
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.common.EmptyContent
import com.example.kmpnewsapp.ui.common.ShimmerEffect
import com.example.kmpnewsapp.utils.articles


@Composable
fun HeadlineScreen(navController: NavController) {

    val headlineViewModel : HeadlineViewModel = viewModel { HeadlineViewModel(OnlineNewsRepository())}
    val uiState by headlineViewModel.newsStateFlow.collectAsState()

    uiState.DisplayResult(

        onIdle = {

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