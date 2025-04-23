package com.jishojose.androidplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jishojose.newsfeed.ui.NewsFeedScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
) {
    NavHost(navController, startDestination = "newsfeed") {
        composable("newsfeed") {
            NewsFeedScreen() // This will automatically get the HiltViewModel scoped to this screen
        }
    }
}
