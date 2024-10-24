package com.example.kmpnewsapp.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmpnewsapp.data.model.Article
import com.example.kmpnewsapp.ui.MainScreen
import com.example.kmpnewsapp.ui.article_detail.ArticleDetailScreen
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.navigation.Graph
import com.example.kmpnewsapp.ui.navigation.NewsRouteScreen
import com.example.kmpnewsapp.ui.navigation.SettingRouteScreen
import com.example.kmpnewsapp.ui.settings.SettingScreen
import com.example.kmpnewsapp.ui.settings.SettingViewModel
import com.example.kmpnewsapp.utils.articles
import kotlinx.serialization.json.Json


@Composable
fun RootNavGraph(
    settingViewModel: SettingViewModel
) {

    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
        route = Graph.RootScreenGraph
    ) {

        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController)
        }

        composable(route = SettingRouteScreen.Setting.route) {
            SettingScreen(rootNavController,settingViewModel)
        }

        composable(route = NewsRouteScreen.NewsDetail.route) {
            rootNavController.previousBackStackEntry?.savedStateHandle?.get<String>("article")?.let {
                val article : Article = Json.decodeFromString(it)
                ArticleDetailScreen(rootNavController, article)
            }

        }


    }

}