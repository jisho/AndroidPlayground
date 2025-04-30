package com.jishojose.androidplayground

import com.jishojose.core.android.mock.MockServer


@Suppress("unused")
open class PlaygroundMockedApplication : PlaygroundApplication() {

    override fun onCreate() {
        super.onCreate()
        MockServer(applicationContext).start()
    }
}
