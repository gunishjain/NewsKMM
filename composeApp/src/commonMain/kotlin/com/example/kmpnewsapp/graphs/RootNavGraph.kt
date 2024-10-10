package com.example.kmpnewsapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kmpnewsapp.ui.MainScreen
import com.example.kmpnewsapp.ui.navigation.Graph


@Composable
fun RootNavGraph() {

    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController,
        startDestination = Graph.MainScreenGraph,
        route = Graph.RootScreenGraph
    ) {

        composable(route = Graph.MainScreenGraph) {
            MainScreen(rootNavController)

        }

    }

}