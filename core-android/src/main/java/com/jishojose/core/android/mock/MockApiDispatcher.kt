package com.jishojose.core.android.mock

import android.content.Context
import com.jishojose.core.android.extenstions.readFileFromAsset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockApiDispatcher(private val context: Context) : Dispatcher() {
    private val experienceApiTestProxy get() = ExperienceApiTestProxy.instance

    override fun dispatch(request: RecordedRequest): MockResponse = when {
        request.startsWith("/v1/news?access_key=") ->
            responseOf("news_response.json")

        else -> MockResponse().setResponseCode(404)
    }

    private fun RecordedRequest.startsWith(prefix: String): Boolean =
        path.orEmpty().startsWith(prefix)

    private fun RecordedRequest.contains(prefix: String): Boolean =
        path.orEmpty().contains(prefix)

    private fun responseOf(filename: String, responseCode: Int = 200) = MockResponse()
        .setResponseCode(responseCode)
        .setBody(
            context.readFileFromAsset(filename)
        )
}
