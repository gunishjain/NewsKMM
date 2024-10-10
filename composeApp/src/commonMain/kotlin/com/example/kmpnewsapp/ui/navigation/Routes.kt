package com.example.kmpnewsapp.ui.navigation

object Graph {
    const val RootScreenGraph = "rootScreenGraph"
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainRouteScreen(var route:String) {

    object Headline: MainRouteScreen("headline")
    object Search: MainRouteScreen("search")
    object Bookmark: MainRouteScreen("bookmark")
}