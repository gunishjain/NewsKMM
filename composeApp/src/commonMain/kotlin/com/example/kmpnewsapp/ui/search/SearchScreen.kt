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
import com.example.kmpnewsapp.theme.mediumPadding
import com.example.kmpnewsapp.ui.common.ArticleListScreen
import com.example.kmpnewsapp.ui.search.component.SearchBarScreen
import com.example.kmpnewsapp.utils.articles

@Composable
fun SearchScreen() {

    var searchQuery by rememberSaveable() {
        mutableStateOf("")
    }

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
                }
            }
        )
        ArticleListScreen(articles)
    }

}