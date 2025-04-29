package com.jishojose.androidplayground.androiddev.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jishojose.androidplayground.ui.theme.AndroidPlaygroundTheme

@Composable
fun AndroidDevScreen(
    navController: NavHostController?,
    modifier: Modifier){
    val contentsList = listOf("Coroutines","Flow")
    LazyColumn {
        items(contentsList.size){
            ItemsRow(contentsList[it], navController)
        }
    }

}

@Composable
private fun ItemsRow(
    content: String,
    navController: NavHostController? = null
) {
    Box(modifier = Modifier
        .clickable {  navController?.navigate(content) }) {
        Text(
            text = content,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val contentsList = listOf("Coroutines","Flow")
    AndroidPlaygroundTheme {
        LazyColumn {
            items(contentsList.size){
                ItemsRow(
                    contentsList[it],
                )
            }
        }
    }
}
