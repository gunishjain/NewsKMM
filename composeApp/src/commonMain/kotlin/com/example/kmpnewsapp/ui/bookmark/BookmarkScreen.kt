package com.example.kmpnewsapp.ui.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.common.EmptyContent
import com.example.kmpnewsapp.ui.common.ShimmerEffect
import com.example.kmpnewsapp.ui.headlines.HeadlineViewModel
import com.example.kmpnewsapp.utils.articles

@Composable
fun BookMarkScreen(navController: NavController) {

    val bookmarkViewModel : BookmarkViewModel = viewModel { BookmarkViewModel() }
    val uiState by bookmarkViewModel.newsStateFlow.collectAsState()

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