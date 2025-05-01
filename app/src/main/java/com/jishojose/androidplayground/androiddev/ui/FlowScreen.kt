package com.jishojose.androidplayground.androiddev.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jishojose.androidplayground.androiddev.viewmodel.CoroutineViewModel
import com.jishojose.androidplayground.androiddev.viewmodel.FlowViewModel

@Composable
fun FlowScreen(
    navController: NavHostController,
    modifier: Modifier,
    flowViewModel : FlowViewModel = hiltViewModel()
){
    val buttonClick = remember { mutableStateOf(false) }
    /*val flow = flowViewModel.flowCollectsWithinCompose
        .collectAsState(initial = "0")*/
    LaunchedEffect(buttonClick.value) {
        if (buttonClick.value) {
            flowViewModel.flowCollectsWithinCompose
                .collect {
                    flowViewModel.logs.add(it)
                }
        }
    }
    Column {
        Button(
            onClick = { flowViewModel.flowList() },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Flow - Collect within viewmodel")
        }
        Button(
            onClick = { buttonClick.value = !buttonClick.value },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Flow - Collect within compose")
        }
        LazyColumn {
            items(flowViewModel.logs) { log ->
                Text(text = log)
            }
        }
    }
}
