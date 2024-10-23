package com.example.kmpnewsapp


import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpnewsapp.ui.navigation.graphs.RootNavGraph
import com.example.kmpnewsapp.theme.NewsAppTheme
import com.example.kmpnewsapp.ui.settings.SettingViewModel
import com.example.kmpnewsapp.utils.AppPreferences
import com.example.kmpnewsapp.utils.dataStorePreference
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val appPreferences = remember {
        AppPreferences(dataStorePreference())
    }

    val settingViewModel = viewModel {
        SettingViewModel(appPreferences)
    }

    val currentTheme by settingViewModel.currentTheme.collectAsState()


    NewsAppTheme(currentTheme) {
        RootNavGraph(settingViewModel)
    }
}