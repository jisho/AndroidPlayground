package com.jishojose.design.system.components.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello!", modifier = modifier)
}
