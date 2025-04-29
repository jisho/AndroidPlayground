package com.jishojose.androidplayground

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jishojose.androidplayground.ui.theme.AndroidPlaygroundTheme
import com.jishojose.core.android.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Composable
    override fun Content() {
        AndroidPlaygroundTheme {
            AppNavHost(modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPlaygroundTheme {
        AppNavHost(modifier = Modifier)
    }
}
