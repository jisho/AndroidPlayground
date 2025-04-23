package com.jishojose.core.android.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable

abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge experience
        enableEdgeToEdge()
        // Set the Composable content
        setContent {
           Content()
        }
    }

    // Subclasses override this to provide screen-specific UI
    @Composable
    abstract fun Content()
}
