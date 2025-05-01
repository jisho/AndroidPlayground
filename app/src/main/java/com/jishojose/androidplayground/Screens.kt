package com.jishojose.androidplayground

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector? = null) {
    data object NewsFeed : Screen("newsfeed", "News", Icons.Default.List)
    data object AndroidDev : Screen("AndroidDev", "AndroidDev", Icons.Default.Settings)
    data object Home : Screen("Home", "Home", Icons.Default.Home)
    data object Coroutines : Screen("Coroutines", "Coroutines" )
    data object Flow : Screen("Flow", "Flow" )
    companion object {
        val allTabScreens = listOf(Home, AndroidDev)
    }
}

