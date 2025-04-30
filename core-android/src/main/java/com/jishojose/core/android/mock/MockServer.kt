package com.jishojose.core.android.mock

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.MockWebServer
import java.net.InetAddress

class MockServer(private val context: Context) {
    private val scope = CoroutineScope(Dispatchers.IO + Job())
    private val server: MockWebServer = MockWebServer()

    fun start() {
        scope.launch {
            server.dispatcher = MockApiDispatcher(context)
            server.start(InetAddress.getLocalHost(), 3001)
            println("Server running on: ${server.url("/")}")
        }
    }

    fun stop() {
        scope.launch {
            server.shutdown()
        }
    }
}
