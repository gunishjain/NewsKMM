package com.example.kmpnewsapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.kmpnewsapp.theme.mediumPadding
import com.example.kmpnewsapp.theme.xLargePadding
import com.example.kmpnewsapp.utils.Type
import com.example.kmpnewsapp.utils.articles
import com.example.kmpnewsapp.utils.getType
import com.example.kmpnewsapp.utils.randomUUIDStr

@Composable
fun ArticleListScreen() {
    val isDesktop  = remember {
        getType() == Type.Desktop
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(if(isDesktop) 3 else 1),
        verticalArrangement = Arrangement.spacedBy(xLargePadding),
        horizontalArrangement = Arrangement.spacedBy(mediumPadding),
        contentPadding = PaddingValues(mediumPadding),
    ) {
        items(articles, key = {
            it.publishedAt + randomUUIDStr()
        }) { item ->
            ArticleItem(article = item, onClick = {

            })
        }
    }

}