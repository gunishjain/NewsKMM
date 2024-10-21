package com.example.kmpnewsapp


import androidx.compose.runtime.*
import com.example.kmpnewsapp.ui.navigation.graphs.RootNavGraph
import com.example.kmpnewsapp.theme.NewsAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    NewsAppTheme(null,true) {
        RootNavGraph()
    }
}