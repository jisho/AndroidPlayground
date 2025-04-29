package com.jishojose.newsfeed

import android.util.Log
import com.jishojose.core.android.util.StringResource
import com.jishojose.newsfeed.model.NewsArticle
import com.jishojose.newsfeed.usecase.GetNewsUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest : BehaviorSpec({

    // Create a test dispatcher
    val testDispatcher = StandardTestDispatcher()

    // Mock setup
    val mockGetNewsUseCase = mockk<GetNewsUseCase>()
    val mockStringResource = mockk<StringResource>()

    val dummyNewsArticle = NewsArticle(
        author = "John Doe",
        title = "Breaking News: Kotlin Simplifies Android Development",
        description = "Learn how Kotlin is revolutionizing Android app development with concise syntax and powerful features.",
        url = "https://example.com/kotlin-news",
        source = "Example News",
        image = "https://example.com/images/kotlin-news.jpg",
        category = "Technology",
        language = "en",
        country = "us",
        published_at = "2025-04-28T10:00:00Z"
    )

    // Test data
    val testArticles = listOf(
        dummyNewsArticle
    )

    beforeSpec {
        // Set up Main dispatcher
        Dispatchers.setMain(testDispatcher)

        mockkStatic(Log::class)
        every { Log.i(any(), any()) } returns 0

        // Mock configurations
        coEvery { mockGetNewsUseCase() } returns testArticles
        every { mockStringResource.get(any()) } returns "Test App Name"
    }

    afterSpec {
        // Clean up Main dispatcher
        Dispatchers.resetMain()
    }

    given("NewsViewModel") {
        `when`("initialized") {
            then("should load news articles") {
                // Create ViewModel
                val viewModel = NewsViewModel(mockGetNewsUseCase, mockStringResource)

                // Advance time to execute coroutines
                testDispatcher.scheduler.advanceUntilIdle()

                // Verify
                viewModel.news.value shouldBe testArticles
            }
        }

        `when`("there's an error loading news") {
            then("should handle error gracefully") {
                // Configure error case
                coEvery { mockGetNewsUseCase() } throws RuntimeException("Network error")

                // Create ViewModel
                val viewModel = NewsViewModel(mockGetNewsUseCase, mockStringResource)

                // Advance time
                testDispatcher.scheduler.advanceUntilIdle()

                // Verify
                viewModel.news.value shouldBe emptyList()
            }
        }
    }
})
