package com.jishojose.androidplayground

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jishojose.androidplayground.androiddev.ui.AndroidDevScreen
import com.jishojose.androidplayground.androiddev.ui.CoroutineScreen
import com.jishojose.androidplayground.ui.HomeScreen
import com.jishojose.newsfeed.ui.NewsFeedScreen

@Composable
fun AppNavHost(modifier: Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // For Material3
            NavigationBar {
                Screen.allTabScreens.forEach { screen ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { screen.icon?.let { Icon(it, contentDescription = screen.label) } },
                        label = { Text(screen.label) }
                    )
                }
            }

        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        navController = navController,
                        modifier = Modifier)
                }
                composable(Screen.AndroidDev.route) {
                    AndroidDevScreen(
                        navController = navController,
                        modifier = Modifier)
                }
                composable(Screen.NewsFeed.route) {
                    NewsFeedScreen()
                }

                composable(Screen.Coroutines.route) {
                    CoroutineScreen(
                        navController = navController,
                        modifier = Modifier
                    )
                }
            }
        }
    )
}

