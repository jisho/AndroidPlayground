package com.jishojose.core.android.extenstions

import android.content.Context

fun Context.readFileFromAsset(filename: String): String {
    return this.assets.open(filename).bufferedReader().use { it.readText() }
}
