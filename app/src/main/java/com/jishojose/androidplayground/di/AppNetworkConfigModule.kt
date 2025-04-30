package com.jishojose.androidplayground.di
import com.jishojose.androidplayground.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppNetworkConfigModule {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return if (BuildConfig.USE_MOCK) {
            "http://localhost:3001/"
        } else {
            "http://api.mediastack.com/"
        }
    }
}
