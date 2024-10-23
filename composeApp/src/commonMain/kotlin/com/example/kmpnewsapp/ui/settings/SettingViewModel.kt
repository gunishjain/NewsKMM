package com.example.kmpnewsapp.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpnewsapp.utils.AppPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SettingViewModel(
    private val appPreferences: AppPreferences,
) : ViewModel() {

    private val _currentTheme: MutableStateFlow<String?> = MutableStateFlow(null)
    val currentTheme = _currentTheme.asStateFlow()


    init {
        currentThemeGet()
    }

    private fun currentThemeGet() = runBlocking {
        _currentTheme.value = appPreferences.getTheme()
    }

    fun changeThemeMode(value: String) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeTheme(value)
        _currentTheme.value = value
    }
}