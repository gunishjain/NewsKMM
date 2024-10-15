package com.example.kmpnewsapp.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SettingScreen(
    rootNavController: NavHostController
) {

    Button(onClick = {
        rootNavController.navigateUp()
    }) {
        Text("Back")
    }
    Box() {
        Text("Setting", fontSize = 32.sp, modifier = Modifier.align(Alignment.Center))
    }

}