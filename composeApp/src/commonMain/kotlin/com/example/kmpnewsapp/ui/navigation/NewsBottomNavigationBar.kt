package com.example.kmpnewsapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NewsBottomNavigationBar(
    btNavItems: List<BottomNavigationItem>,
    currentRoute: String?,
    onItemClick: (BottomNavigationItem) -> Unit
) {
   NavigationBar(
       modifier = Modifier.fillMaxWidth()
   ) {
       btNavItems.forEach { btItem->
           NavigationBarItem(
               selected = currentRoute == btItem.route,
               onClick = { onItemClick(btItem) },
               icon = { Icon(painterResource(btItem.icon),contentDescription = stringResource(btItem.title)) },
               label = {
                    Text(
                        text = stringResource(btItem.title),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                    )
               }
           )

       }
   }

}