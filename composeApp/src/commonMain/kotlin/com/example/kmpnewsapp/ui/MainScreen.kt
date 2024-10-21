package com.example.kmpnewsapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.kmpnewsapp.ui.navigation.graphs.MainNavGraph
import com.example.kmpnewsapp.ui.navigation.NewsBottomNavigationBar
import com.example.kmpnewsapp.ui.navigation.SettingRouteScreen
import com.example.kmpnewsapp.utils.navigationItemsLists
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.setting
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    rootNavController: NavHostController
) {

    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()

    val currentRoute by remember (navBackStackEntry) { mutableStateOf(
        navBackStackEntry?.destination?.route
    ) }

    val topBarTitle by remember(currentRoute) {
        derivedStateOf {
            if(currentRoute!=null){
                navigationItemsLists[navigationItemsLists.indexOfFirst {
                    it.route == currentRoute
                }].title
            } else {
                navigationItemsLists[0].title
            }
        }
    }



    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(topBarTitle),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        rootNavController.navigate(SettingRouteScreen.Setting.route)
                    }
                ){
                    Icon(imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(Res.string.setting)
                    )
                }
            }
        )
    }, bottomBar = {

        NewsBottomNavigationBar(
            btNavItems = navigationItemsLists,
            currentRoute = currentRoute,
            onItemClick = {btmNavigationItem->
                homeNavController.navigate(btmNavigationItem.route) {
                    homeNavController.graph.startDestinationRoute?.let {
                        popUpTo(it) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

    } ) {
        MainNavGraph(rootNavController,homeNavController,it)
    }

}