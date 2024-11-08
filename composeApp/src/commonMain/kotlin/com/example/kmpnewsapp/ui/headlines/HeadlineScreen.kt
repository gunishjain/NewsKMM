package com.example.kmpnewsapp.ui.headlines


import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kmpnewsapp.data.repository.OnlineNewsRepository
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.common.EmptyContent
import com.example.kmpnewsapp.ui.common.ShimmerEffect
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.ic_browse
import kmp_news_app.composeapp.generated.resources.no_news
import org.jetbrains.compose.resources.stringResource


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
                EmptyContent(
                    message = stringResource(Res.string.no_news),
                    icon = Res.drawable.ic_browse,
                    onRetryBtnClick = {
                        headlineViewModel.getHeadlines()
                    }
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
                    headlineViewModel.getHeadlines()
                }
            )
        }


    )


}