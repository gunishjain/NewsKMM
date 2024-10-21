package com.example.kmpnewsapp.ui.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kmpnewsapp.ui.bookmark.BookMarkScreen
import com.example.kmpnewsapp.ui.headlines.HeadlineScreen
import com.example.kmpnewsapp.ui.navigation.Graph
import com.example.kmpnewsapp.ui.navigation.MainRouteScreen
import com.example.kmpnewsapp.ui.search.SearchScreen

@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    homeNavController: NavHostController,
    paddingValues: PaddingValues
) {

    NavHost(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        navController = homeNavController,
        route = Graph.MainScreenGraph,
        startDestination = MainRouteScreen.Headline.route
    ) {
        composable(route = MainRouteScreen.Headline.route) {
            HeadlineScreen(rootNavController)
        }

        composable(route = MainRouteScreen.Search.route) {
            SearchScreen(rootNavController)
        }

        composable(route = MainRouteScreen.Bookmark.route) {
            BookMarkScreen(rootNavController)
        }
    }




}