package com.jishojose.core.android.di

import android.content.Context
import android.net.ConnectivityManager
import com.jishojose.core.android.connectivity.NetworkStatusRepository
import com.jishojose.core.android.connectivity.NetworkStatusRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkStatusModule {

    @Provides
    fun providesConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    fun providesNetworkStatusRepository(connectivityManager: ConnectivityManager): NetworkStatusRepository =
        NetworkStatusRepositoryImpl(connectivityManager)
}
