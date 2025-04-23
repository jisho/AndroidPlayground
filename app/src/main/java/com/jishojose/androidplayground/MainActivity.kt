package com.jishojose.androidplayground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jishojose.androidplayground.ui.theme.AndroidPlaygroundTheme
import com.jishojose.core.android.base.BaseActivity
import com.jishojose.design.system.components.text.Greeting
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        AndroidPlaygroundTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
               AppNavHost(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPlaygroundTheme {
        Greeting("Android")
    }
}
