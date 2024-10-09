package com.example.kmpnewsapp.utils

import com.example.kmpnewsapp.ui.navigation.BottomNavigationItem
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.bookmark
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.headlines
import kmp_news_app.composeapp.generated.resources.ic_bookmark_outlined
import kmp_news_app.composeapp.generated.resources.ic_headline
import kmp_news_app.composeapp.generated.resources.ic_search
import kmp_news_app.composeapp.generated.resources.light_mode
import kmp_news_app.composeapp.generated.resources.search
import kmp_news_app.composeapp.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource

enum class Theme(val title: StringResource) {
    SYSTEM_DEFAULT(Res.string.system_default),
    LIGHT_MODE(Res.string.light_mode),
    DARK_MODE(Res.string.dark_mode)
}

enum class Type {
    Mobile, Desktop
}

val navigationItemsLists = listOf(
    BottomNavigationItem(
        icon = Res.drawable.ic_headline,
        title = Res.string.headlines,
        route = "headlines",
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_search,
        title = Res.string.search,
        route = "search",
    ),
    BottomNavigationItem(
        icon = Res.drawable.ic_bookmark_outlined,
        title = Res.string.bookmark,
        route = "bookmark",
    ),
)