package com.jishojose.androidplayground.androiddev.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
    val logs = coroutineViewModel.logs.toList()
    Column {
        Button(
            onClick = { coroutineViewModel.coroutineConsecutiveExecution() },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Consecutive execution or Sequential execution")
        }
        Button(
            onClick = { coroutineViewModel.coroutineParallelExecution() },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Parallel execution or Concurrent execution")
        }
        Button(
            onClick = { coroutineViewModel.coroutineLongRunningTask() },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Long running operation")
        }
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(logs) { logLine ->
                Text(text = logLine)
            }
        }

    }
}
