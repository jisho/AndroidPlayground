package com.jishojose.androidplayground.androiddev.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jishojose.androidplayground.androiddev.viewmodel.CoroutineViewModel

@Composable
fun CoroutineScreen(
    navController: NavHostController,
    modifier: Modifier,
    coroutineViewModel : CoroutineViewModel = hiltViewModel()
){
    Column {
        Text(
            text = "Coroutines Dev experiment",
            modifier = Modifier.padding(16.dp)
        )

    }

}
